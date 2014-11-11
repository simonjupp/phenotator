package sysmicro.grails

import com.grailsrocks.authentication.AuthenticationService

class User {

    String login
   	String password
   	String email
    String role = "user"


   	int status = AuthenticationService.STATUS_NEW

   	def authenticationService

   	static constraints = {
   		login(size:5..64, unique: true, validator: { val, obj -> obj.authenticationService.checkLogin(val) } )
   		password(size:5..64, password: true, validator: { val, obj -> obj.authenticationService.checkPassword(val) })
   		email(email:true, nullable: true, blank: false)
   		status(inList:[
   			AuthenticationService.STATUS_NEW,
   			AuthenticationService.STATUS_VALID,
   			AuthenticationService.STATUS_AWAITING_CONFIRMATION,
   			AuthenticationService.STATUS_CONFIRMATION_LAPSED
   		])
   	}

    static hasMany = [requests:TermRequest, annotations:EntityAnnotation]

    @Override
    String toString() {
        return login;
    }
}
