package uk.ac.ebi.sysmicro.owl

import org.semanticweb.owlapi.apibinding.OWLManager
import org.semanticweb.owlapi.model.OWLOntologyManager
import org.semanticweb.owlapi.model.OWLDataFactory
import org.semanticweb.owlapi.model.OWLOntology
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration
import org.semanticweb.owlapi.reasoner.SimpleConfiguration
import org.semanticweb.elk.owlapi.ElkReasonerFactory
import org.apache.commons.logging.LogFactory
import org.semanticweb.owlapi.reasoner.InferenceType
import org.semanticweb.owlapi.reasoner.OWLReasoner
import sysmicro.grails.BioEntity
import sysmicro.grails.EntityAnnotation
import org.semanticweb.owlapi.model.OWLClass
import org.semanticweb.owlapi.model.IRI
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom
import org.semanticweb.owlapi.model.OWLObjectProperty
import org.semanticweb.owlapi.model.OWLOntologyStorageException
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom
import org.semanticweb.owlapi.model.OWLLiteral
import org.semanticweb.owlapi.model.OWLAnnotation

/**
 *
 * @author Simon Jupp
 * @date 14/05/2013
 * Functional Genomics Group EMBL-EBI
 *
 */

class OWLCPOOntology {

    def log = LogFactory.getLog(OWLReasoner.class)

    def baseUri = "http://www.ebi.ac.uk/cmpo/phenotator/";

    def rootClassUri = "http://www.ebi.ac.uk/cmpo/CMPO_0000003";
    def isPhenotypeOf = "http://purl.obolibrary.org/obo/RO_0002201";
    def  hasQualityUri = "http://www.ebi.ac.uk/cmpo/has_quality";
    def  hasQualifier = "http://purl.obolibrary.org/obo/RO_0002180";
    def  inheres_in = "http://purl.obolibrary.org/obo/RO_0000052";
    def  towardsUri = "http://purl.obolibrary.org/obo/BFO_0000070";
    def hasPart = "http://purl.obolibrary.org/obo/BFO_0000051";
    def qualityUri = "http://purl.obolibrary.org/obo/PATO_0000001";
    def rootUri = "http://www.ebi.ac.uk/cmpo/CMPO_0000003";
    def hasAccuracy = IRI.create(baseUri + "hasAccuracy");

    def creator = IRI.create("http://purl.org/dc/elements/1.1/creator");

    def poorAccuracyUri = IRI.create(baseUri +  "Poor");
    def closeAccuracyUri = IRI.create(baseUri +  "Close");
    def goodAccuracyUri = IRI.create(baseUri +  "Good");

    //    private static String isPhenotypeOf = "";
//    def ontfile = new File("/Users/jupp/Dropbox/dev/cpo/cpo-dev.owl")
    def ontfile = IRI.create("http://www.ebi.ac.uk/~jupp/downloads/cmpo-merged.owl")

    OWLOntologyManager manager;
    OWLDataFactory factory;
    OWLOntology ont;
    OWLReasoner reasoner;

    public OWLCPOOntology () {

        println ("Initilaising OWL CPO...")
        manager = OWLManager.createOWLOntologyManager();
        factory = manager.getOWLDataFactory();
        System.setProperty("entityExpansionLimit", "1000000000")
//        ont = manager.loadOntology(ontfile);
        ont = manager.createOntology(IRI.create(baseUri + "ontology"));
        println ("Initilaising OWL CPO complete!");

    }

    void classify() {


        log.info("ontology loaded...")
        OWLReasonerFactory reasonerFactory = null

        ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor()
        OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor)

        log.info("creating reasoner...")
        OWLReasonerFactory f1 = new ElkReasonerFactory()
        reasoner = f1.createReasoner(ont,config)

        log.info("precomputing inferences...")
        reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY)
        log.info("precomputing inferences complete!")
    }


    void addPhenotype (BioEntity be) {
        String phenotype = be.getPhenotype();
        String comment = be.getComment();
        String xref = be.getXref();
        Long id = be.getId();

        Set<OWLObjectSomeValuesFrom> equivClassDescription = new HashSet<OWLObjectSomeValuesFrom>();
        OWLClass cpoClass = factory.getOWLClass(IRI.create(baseUri + id));

        Set<OWLAnnotation> creatorAnnotation = new HashSet<OWLAnnotation>();
        for (EntityAnnotation e : be.getEntityAnnotation()) {
            if (e.getEntity() != null) {
                System.out.println(phenotype + "!!!");

                creatorAnnotation.add(factory.getOWLAnnotation(factory.getOWLAnnotationProperty(creator), factory.getOWLLiteral(e.getUser().getLogin())))

                manager.addAxiom(ont, factory.getOWLSubClassOfAxiom(cpoClass, factory.getOWLClass(IRI.create(rootClassUri))));
                setAnnotation(OWLRDFVocabulary.RDFS_LABEL.getIRI(), cpoClass, phenotype);
                setAnnotation(OWLRDFVocabulary.RDFS_COMMENT.getIRI(), cpoClass, comment);
                setAnnotation(IRI.create(baseUri, "xref"), cpoClass, xref);

                String entityUri = e.getEntity().getUri();
                // fix DNA URI to use ChEBI
                if (entityUri.equals("http://purl.obolibrary.org/obo/GO_0005574")) {
                    entityUri = "http://purl.obolibrary.org/obo/CHEBI_16991"
                }

                // fix GO cell to use CL cell
                if (entityUri.equals("http://purl.obolibrary.org/obo/GO_0005623")) {
                    entityUri = "http://purl.obolibrary.org/obo/CL_0000000"
                }

                OWLClass entity = factory.getOWLClass(IRI.create(entityUri));


//                OWLObjectSomeValuesFrom equivClassDescription = null;

                OWLClass quality = factory.getOWLClass(IRI.create(qualityUri));
                if (e.getQuality() != null) {
                    String uri = e.getQuality().getUri();
                    // fix 'enhanced' to 'increased magnitude'
                    if (uri.equals("http://purl.obolibrary.org/obo/PATO_0001589")) {
                        uri = "http://purl.obolibrary.org/obo/PATO_0002017";
                    }
                    // fix 'impaired' to 'decreased functionality'
                    if (uri.equals("http://purl.obolibrary.org/obo/PATO_0000762")) {
                        uri = "http://purl.obolibrary.org/obo/PATO_0001624";
                    }

                    quality = factory.getOWLClass(IRI.create(uri));
                }

                if (e.getModifier() != null) {

                    OWLClass modifier = factory.getOWLClass(IRI.create(e.getModifier().getUri()));

                    OWLObjectProperty modifierProp = factory.getOWLObjectProperty(IRI.create(hasQualifier));

                    if (e.getModifier().getUri().contains("GO_")) {
                        // fix GO cell to use CL cell
                        if (e.getModifier().getUri().equals("http://purl.obolibrary.org/obo/GO_0005623")) {
                            modifier = factory.getOWLClass(IRI.create("http://purl.obolibrary.org/obo/CL_0000000"));
                        }
                        modifierProp = factory.getOWLObjectProperty(IRI.create(towardsUri));
                    }

                    equivClassDescription.add(factory.getOWLObjectSomeValuesFrom(
                            factory.getOWLObjectProperty(IRI.create(hasPart)),
                            factory.getOWLObjectIntersectionOf(
                                    quality,
                                    factory.getOWLObjectSomeValuesFrom(
                                            factory.getOWLObjectProperty(IRI.create(inheres_in)),
                                            entity
                                    )
                                    ,
                                    factory.getOWLObjectSomeValuesFrom(modifierProp,
                                            modifier)
                            )
                    ));
                }
                else {
                    equivClassDescription.add(factory.getOWLObjectSomeValuesFrom(
                            factory.getOWLObjectProperty(IRI.create(hasPart)),
                            factory.getOWLObjectIntersectionOf(
                                    quality,
                                    factory.getOWLObjectSomeValuesFrom(
                                            factory.getOWLObjectProperty(IRI.create(inheres_in)),
                                            entity
                                    )
                            )
                    ));
                }

                def accuraryUri = closeAccuracyUri;

                if (e.getAccuracy() == 1) {
                    accuraryUri = poorAccuracyUri;
                }
                else if (e.getAccuracy() == 3) {
                    accuraryUri = goodAccuracyUri;
                }
                manager.addAxiom(ont, factory.getOWLSubClassOfAxiom(cpoClass,
                        factory.getOWLObjectSomeValuesFrom(
                                factory.getOWLObjectProperty(hasAccuracy),
                                factory.getOWLClass(accuraryUri))
                ));



            }
        }

        if (equivClassDescription.size() > 0) {
            manager.addAxiom(ont,
                    factory.getOWLEquivalentClassesAxiom(cpoClass, factory.getOWLObjectIntersectionOf(equivClassDescription), creatorAnnotation)
            );
        }
    }

    void save() {
        try {
            manager.saveOntology(ont, IRI.create("file:/Users/jupp/tmp/cpo/cmpo-generated2.owl"));
        } catch (OWLOntologyStorageException e) {
            e.printStackTrace();
        }
    }

    void setAnnotation(IRI property, OWLClass cpoClass, String label) {
        if (label != null) {
            manager.addAxiom(ont, factory.getOWLAnnotationAssertionAxiom(
                    factory.getOWLAnnotationProperty(property),
                    cpoClass.getIRI(),
                    factory.getOWLLiteral(label)
            ))  ;
        }
    }



    OntologyTree getOntologyTree() {

        classify()
        OntologyTree tree = new OntologyTree();

        TreeNode root = tree.getNode("CMPO_0000001", rootUri, "Cellular microscopy phenotype ontology");


        root.getChildren().addAll(getChildren(tree, root));

        tree.setRoot(root);
        return tree;

    }

    Set<TreeNode> getChildren(OntologyTree tree, TreeNode n) {

        // find all the children of node n
        Set<TreeNode> childNodes = new HashSet<TreeNode>();
        for (OWLClass c : reasoner.getSubClasses(factory.getOWLClass(IRI.create(n.getUri())), true).getFlattened()) {

            String uri = c.getIRI().toURI().toString();
            String frag =    c.getIRI().getFragment();
            if (!frag.equals("Nothing"))  {
                TreeNode node = tree.getNode(frag, uri, getLabel(c));
                childNodes.add(node);
                //recurse
                node.getChildren().addAll(getChildren(tree, node));
                node.getSiblings().addAll(getSiblings(tree, node));
            }

        }
        return childNodes;
    }

    Set<TreeNode> getSiblings(OntologyTree tree, TreeNode n) {

        Set<TreeNode> sibNodes = new HashSet<TreeNode>();

        for (OWLClass sib : reasoner.getEquivalentClasses(factory.getOWLClass(IRI.create(n.getUri())))) {


            String uri = sib.getIRI().toURI().toString();
            String frag =    sib.getIRI().getFragment();
            if (!frag.equals(n.getId())) {
                sibNodes.add(tree.getNode(frag, uri, getLabel(sib)));
            }
        }
        return sibNodes
    }

    String getLabel (OWLClass cls) {

        for (OWLAnnotationAssertionAxiom ax : cls.getAnnotationAssertionAxioms(ont)) {
            if (ax.getProperty().getIRI().equals(OWLRDFVocabulary.RDFS_LABEL.getIRI())) {
                return ((OWLLiteral) ax.getValue()).getLiteral();
            }
        }
        return null;
    }

    public static void main(String[] args) {

        OWLCPOOntology op = new OWLCPOOntology();
        OntologyTree t = op.getOntologyTree();
        for (TreeNode n : t.getAllNodes()) {
            println(n.getId() + " - > " + n.getLabel());

            for (TreeNode sibs : n.getSiblings()) {
                println ("\t |- sibs " + sibs.getId() + " - > " + sibs.getLabel())
            }
            for (TreeNode child : n.getChildren()) {
                println ("\t |- child " + child.getId() + " - > " + child.getLabel())
            }
        }
    }

    public void asRDFXML (OutputStream stream ) {

        manager.saveOntology(ont, stream);

    }

}



