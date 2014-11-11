package sysmicro.grails

class TermRequest {

    String term
    String definition
    String comment
    Ontology ontology

    static belongsTo = [user:User]

    enum Ontology {
         CELLTYPE ('Cellular component'),
         ENTITY ('Biological Process/Function'),
         QUALITY ('Quality'),
         OTHER ('Other')

        String value
        Ontology (String value) {
            this.value =  value
        }

        @Override
        String toString() {
            return value;
        }
    }

    static constraints = {
        term(blank:false, nullable: false)
        definition(blank: false, nullable: false, size:1..1000)
        ontology(nullable: false)
        comment(blank: true, nullable: true, size:1..1000)
        user(display:false, nullable: true, blank:true)
    }
}
