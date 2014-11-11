package sysmicro.grails

class Term  {

    String uri;
    String label;
    String ontologyId;
    String ontologyUri;

    static hasMany = [bioEntity:BioEntity, entity:EntityAnnotation, quality:EntityAnnotation]

    static mappedBy = [entity: 'entity',
            quality: 'quality']

    @Override
    public String toString() {
        return label
    }

    static constraints = {
        uri(nullable:false, blank: false, unique: true)
        label(nullable: false, blank: false, size: 1..300)
        ontologyId(nullable: true, blank: true, size: 1..300)
        ontologyUri(nullable: true, blank: true, size: 1..300)


    }
}
