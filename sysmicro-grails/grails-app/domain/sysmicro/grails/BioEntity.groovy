package sysmicro.grails

import org.apache.commons.collections.list.LazyList
import org.apache.commons.collections.FactoryUtils

class BioEntity {

    String phenotype;
    String xref;
    String comment;

    List<EntityAnnotation> entityAnnotation = new ArrayList()
    static hasMany = [entityAnnotation: EntityAnnotation];

    static mapping = {
        entityAnnotation cascade:"all-delete-orphan"
    }

    static constraints = {
        phenotype(blank:false, nullable: false, size:1..1000)
        xref(blank:true, nullable: true, size:1..80, unique: true)
        comment(blank:true,nullable: true, size: 1..1000)
//        species(blank:true, nullable: true)
    }

    @Override
    String toString() {
        return phenotype
    }
}
