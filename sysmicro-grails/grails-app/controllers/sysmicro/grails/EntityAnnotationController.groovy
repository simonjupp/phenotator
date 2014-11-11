package sysmicro.grails

class EntityAnnotationController {

    def scaffold = true

    def index() {
        redirect(action: "list", params: params)
    }
}
