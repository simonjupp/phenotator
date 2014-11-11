package sysmicro.grails

class TermRequestController {

    def scaffold = true
    def authenticationService


    def save() {

        def termRequest = new TermRequest(params);
        if (authenticationService.isLoggedIn(request)) {
            User user = (User) authenticationService.getUserPrincipal(true)
            termRequest.user = user

            if (termRequest.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'termRequest.id', default: 'BioEntity'), termRequest.id])}"
                redirect(action: "show", id: termRequest.id)
            }
            else {
                render(view: "create", model: [termRequest: termRequest])
            }
        }
        else {
            termRequest.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'termRequest.id', default: 'TermRequest')] as Object[], "You must be signed in to request new terms")
            redirect(view: "create", model: [termRequest: termRequest])

        }
    }
}
