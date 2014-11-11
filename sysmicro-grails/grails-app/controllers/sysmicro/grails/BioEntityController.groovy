package sysmicro.grails

import org.apache.commons.logging.LogFactory
import grails.converters.deep.JSON
import uk.ac.ebi.sysmicro.owl.OWLCPOOntology
import uk.ac.ebi.sysmicro.owl.OntologyTree
import groovy.json.JsonBuilder
import uk.ac.ebi.sysmicro.owl.TreeNode

class BioEntityController {

    def scaffold = false
    def authenticationService
    private static final log = LogFactory.getLog(this)
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def ontologyService;

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {


        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def c = BioEntity.createCriteria()
        def entityList = c.list (params) {
            if ( params.query ) {
                ilike("phenotype", "%${params.query}%")
            }
        }

        withFormat {
            html {
                return [entityInstanceList: entityList, entityInstanceTotal: entityList.totalCount, query: params.query]
            }
            json {
                response.contentType = "application/json"
                render BioEntity.list() as JSON
            }
        }
    }

    def create = {
        def entityInstanceList = new BioEntity()
        entityInstanceList.properties = params
//        if (authenticationService.isLoggedIn(request)) {
//            def filteredEntityAnnotation = EntityAnnotation.findAll('from EntityAnnotation as e where e.user = ? and e.bioEntity = ?', [user, entityInstanceList] )
//            return [entityInstanceList: entityInstanceList, filteredEntityAnnotation:filteredEntityAnnotation]
//        }
        return [entityInstanceList: entityInstanceList]

    }

    def save = {
        def entityInstanceList = new BioEntity(params)
        entityInstanceList = bindBioEntity(entityInstanceList, params)

        if (entityInstanceList.hasErrors()) {
            render(view: "create", model: [entityInstanceList: entityInstanceList])
        }
        else if (entityInstanceList.save(flush: true)) {
            ontologyService.addPhenotype(entityInstanceList);
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'entityInstanceList.id', default: 'BioEntity'), entityInstanceList.id])}"
            redirect(action: "show", id: entityInstanceList.id)
        }
        else {
            render(view: "create", model: [entityInstanceList: entityInstanceList])
        }
    }

    def ontology = {

        withFormat {
            html {
                OntologyTree ontologyTree =ontologyService.getOntologyTree();
                [ontologyNode: ontologyTree.getRoot()]
            }
            json {
                OntologyTree t = ontologyService.getOntologyTree();
                render ontologyService.getOntologyTree() as JSON;
            }
            rdf {
                response.setHeader("Content-type", "application/rdf+xml");
                ontologyService.asRDFXML(response.outputStream);
            }
        }
    }

    def downloadOntology = {

        response.setContentType("application/rdf+xml")
        response.setHeader("Content-disposition", "attachment; filename=cpo.owl")
        ontologyService.asRDFXML(response.outputStream);
        return true;

    }

    def BioEntity bindBioEntity(bioEntityInstance, params) {

        def count = 0
        if (params.annoCount) {
            count = params.annoCount.toInteger();
        }

        if (authenticationService.isLoggedIn(request))
        {

            User loggedInUser = (User) authenticationService.getUserPrincipal(true);

            for (int i=0; i< count; i++) {

                def id = params.getAt('annotation-' +i +'.id')

                // get Entity term
                def entityUri = params.getAt('annotation-'+i+'-entity-uri')
                def entityLabel = params.getAt('annotation-' +i +'-entity-label')
                def entityOntologyId = params.getAt('annotation-'+i+'-entity-ontology-id')
                Term entity = getTerm(entityUri, entityLabel, entityOntologyId);
                if (!entity) {
                    bioEntityInstance.errors.rejectValue("entityAnnotation", "You must supply a valid entity term")
                    return bioEntityInstance
                }

                // get the quality term
                def qualityUri = params.getAt('annotation-'+i+'-quality-uri');

                def qualityLabel = params.getAt('annotation-' +i +'-quality-label');
                def qualityOntologyId = params.getAt('annotation-'+i+'-quality-ontology-id')

                Term quality = getTerm(qualityUri, qualityLabel, qualityOntologyId);
                if (!quality) {

                    bioEntityInstance.errors.rejectValue("entityAnnotation", "You must supply a valid quality term")
                    return bioEntityInstance
                }

                // get modifier term

                def modifierUri = params.getAt('annotation-'+i+'-modifier-uri');
                def modifierLabel = params.getAt('annotation-' +i +'-modifier-label');
                def modifierOntologyId = params.getAt('annotation-'+i+'-modifier-ontology-id')

                Term modifier = getTerm(modifierUri, modifierLabel, modifierOntologyId);

                def accuracy = Integer.parseInt(params.getAt('annotation-' +i +'.accuracy').toString())
//                def value = params.getAt('annotation-' +i +'.value')
                def toBeDeleted = params.getAt('annotation-' +i +'.deleted')

                def annotation
                if (id) {
                    annotation = EntityAnnotation.findById(Long.parseLong(id.toString()))
                }
                else {
                    annotation = new EntityAnnotation(entity: entity, quality: quality, modifier:modifier, accuracy: accuracy, user: loggedInUser);
                    bioEntityInstance.addToEntityAnnotation(annotation)
                }


                if (toBeDeleted) {
                    annotation.setDeleted(Boolean.parseBoolean(toBeDeleted))
                }
            }
            bioEntityInstance.clearErrors();
            return bioEntityInstance
        }
        else {
            bioEntityInstance.errors.rejectValue("entityAnnotation", "default.updated.message", [message(code: 'bioEntityInstance.id', default: 'BioEntity')] as Object[], "You must be logged in")
            return null;
        }


    }

    def Term getTerm(entityUri, entityLabel, ontologyName) {

        Term entity = null;

        if (entityUri) {
            entity = Term.findByUri(entityUri);
        }
        if (entity == null) {
            log.debug("creating new term " + entityUri + " " + entityLabel + " " + ontologyName)
            entity = new Term(uri: entityUri, label: entityLabel, ontologyId: ontologyName).save()
        }
        return entity;
    }

    def show = {
        def entityInstanceList = BioEntity.get(params.id)
        if (!entityInstanceList) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'entityInstanceList.id', default: 'BioEntity'), params.id])}"
            redirect(action: "list")
        }
        else {
            if (authenticationService.isLoggedIn(request)) {
                def user = authenticationService.getUserPrincipal(true)
                def filteredEntityAnnotation = EntityAnnotation.findAll('from EntityAnnotation as e where e.bioEntity = ?', [entityInstanceList] )
                [entityInstanceList: entityInstanceList, filteredEntityAnnotation:filteredEntityAnnotation]
            }
            else {
                [entityInstanceList: entityInstanceList]
            }
        }
    }

    def edit = {
        def entityInstanceList = BioEntity.get(params.id)
        if (!entityInstanceList) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'entityInstanceList.id', default: 'BioEntity'), params.id])}"
            redirect(action: "list")
        }
        else {
            if (authenticationService.isLoggedIn(request)) {
                def user = authenticationService.getUserPrincipal(true)
                def filteredEntityAnnotation = EntityAnnotation.findAll('from EntityAnnotation as e where e.user = ? and e.bioEntity = ?', [user, entityInstanceList] )
                return [entityInstanceList: entityInstanceList, filteredEntityAnnotation:filteredEntityAnnotation]
            }
            else {
                return [entityInstanceList: entityInstanceList]
            }
        }
    }

    def update = {
        def entityInstanceList =  bindBioEntity(BioEntity.get(params.id), params)

        if (entityInstanceList) {
            if (params.version) {
                def version = params.version.toLong()
                if (entityInstanceList.version > version) {
                    entityInstanceList.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'entityInstanceList.id', default: 'BioEntity')] as Object[], "Another user has updated this BioEntity while you were editing")
                    render(view: "edit", model: [entityInstanceList: entityInstanceList])
                    return
                }
            }

            entityInstanceList.properties = params;
            def _toBeDeleted = entityInstanceList.entityAnnotation.findAll {it.deleted}


            if (_toBeDeleted) {
                entityInstanceList.entityAnnotation.removeAll(_toBeDeleted)
            }

            if (!entityInstanceList.hasErrors() && entityInstanceList.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'entityInstanceList.id', default: 'BioEntity'), entityInstanceList.id])}"
                redirect(action: "show", id: entityInstanceList.id)
            }
            else {
                if (authenticationService.isLoggedIn(request)) {
                    def user = authenticationService.getUserPrincipal(true)
                    def filteredEntityAnnotation = EntityAnnotation.findAll('from EntityAnnotation as e where e.bioEntity = ?', [entityInstanceList] )
                    render(view: "edit", model: [entityInstanceList: entityInstanceList, filteredEntityAnnotation:filteredEntityAnnotation])
                }
                else {
                    render(view: "edit", model: [entityInstanceList: entityInstanceList])
                }
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'entityInstanceList.id', default: 'BioEntity'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def entityInstanceList = BioEntity.get(params.id)
        if (entityInstanceList) {

            boolean otherAnnotation  = false;
            def user = authenticationService.getUserPrincipal(true)

            for (EntityAnnotation ea : entityInstanceList.getEntityAnnotation()) {

                if (ea.getUser() != user) {
                    otherAnnotation = true;
                }
            }


            if (otherAnnotation) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'entityInstanceList.id', default: 'BioEntity'), params.id])}"
                redirect(action: "show", id: params.id)
            }
            else {
                try {
                    entityInstanceList.delete(flush: true)
                    flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'entityInstanceList.id', default: 'BioEntity'), params.id])}"
                    redirect(action: "list")
                }
                catch (org.springframework.dao.DataIntegrityViolationException e) {
                    flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'entityInstanceList.id', default: 'BioEntity'), params.id])}"
                    redirect(action: "show", id: params.id)
                }
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'entityInstanceList.id', default: 'BioEntity'), params.id])}"
            redirect(action: "list")
        }
    }

}
