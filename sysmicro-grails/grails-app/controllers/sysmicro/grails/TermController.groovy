package sysmicro.grails

class TermController {

    def scaffold = true

    def index() {
        redirect(action: "list", params: params)
    }
}
