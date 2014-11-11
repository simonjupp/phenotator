package sysmicro.grails

import org.apache.commons.validator.EmailValidator
import com.grailsrocks.authentication.AuthenticationUser
import org.apache.commons.logging.LogFactory
import org.apache.commons.lang.RandomStringUtils

class ReminderController {

    private static final log = LogFactory.getLog(this)
    def authenticationService

    def index() {

        render(view: "index")

    }

    def send () {

        def email = params.email;
        EmailValidator emailValidator = EmailValidator.getInstance()

        if (emailValidator.isValid(email)) {

            def u = User.findByEmail(email)
            if (u) {
                log.debug("sending password reminder to " + u.email)

                String charset = (('A'..'Z') + ('0'..'9')).join()
                Integer length = 6
                String randomString = RandomStringUtils.random(length, charset.toCharArray())

                u.password = authenticationService.encodePassword(randomString);
                if (u.save()) {
                    sendMail {
                        to email
                        from "no-reply-phenotator@ebi.ac.uk"
                        subject "Phenotator login details"
                        body "username:" + u.login + "\npassword:" + randomString
                    }
                    flash.message = "E-mail sent to " + email
                }
                else {
                    log.error("problem setting password for: " + u.login)
                    flash.message = "Couldn't not set new password for this user, please contact an administrator"
                }

            }
            else {
                flash.message ="Sorry, no such user with this e-mail address"
            }
        }
        else {
            flash.message = "Please supply a valid email address"
        }
        render(view: "index")

//        render ("Success!")

    }
}
