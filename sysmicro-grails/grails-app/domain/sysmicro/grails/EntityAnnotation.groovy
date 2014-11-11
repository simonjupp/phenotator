package sysmicro.grails

import org.apache.commons.lang.StringUtils
import org.semanticweb.owlapi.util.SimpleIRIShortFormProvider
import org.semanticweb.owlapi.model.IRI

class EntityAnnotation {

    boolean deleted
    static transients = [ 'deleted' ]
    Integer accuracy;

    static belongsTo = [bioEntity:BioEntity, entity:Term, quality:Term, modifier:Term,  user:User]

    @Override
    public String toString() {
        return render();
    }

    static constraints = {
        accuracy(min: 1, max: 3)
        modifier(nullable: true)
        user (blank:false, nullable: false)
    }

    String render() {
        SimpleIRIShortFormProvider sfp = new SimpleIRIShortFormProvider();
        String v = entity.label + " (" + sfp.getShortForm(IRI.create(entity.uri)) + ") has quality " + quality.label + " (" + sfp.getShortForm(IRI.create(quality.uri)) + ")";
        if (modifier != null) {
            v += " has Modifier " + modifier.label + " (" + sfp.getShortForm(IRI.create(modifier.uri)) + ")";
        }
        return v;
    }

}
