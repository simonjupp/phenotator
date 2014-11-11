package sysmicro.grails

class UserController {

    def scaffold = true;

    def index = {
        redirect(action: "list", params: params)
    }
}
