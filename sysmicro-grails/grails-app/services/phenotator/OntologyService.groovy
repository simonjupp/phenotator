package phenotator

import uk.ac.ebi.sysmicro.owl.OWLCPOOntology
import sysmicro.grails.BioEntity
import uk.ac.ebi.sysmicro.owl.OntologyTree
import groovy.json.JsonBuilder
import uk.ac.ebi.sysmicro.owl.TreeNode

class OntologyService {

    private OWLCPOOntology cpoOntology;

    def initialise (OWLCPOOntology cpoOntology, List<BioEntity> bioEntityList) {
        this.cpoOntology = cpoOntology;

        for (BioEntity be : bioEntityList) {
         addPhenotype(be);
        }


    }

    def addPhenotype (BioEntity e) {
        cpoOntology.addPhenotype(e);
    }

    def getOntologyTree () {
        return cpoOntology.getOntologyTree();
    }


    def asRDFXML (OutputStream s) {
        cpoOntology.asRDFXML(s)
    }



}
