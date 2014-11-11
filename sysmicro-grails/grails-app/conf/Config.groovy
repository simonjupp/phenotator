 // locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = true
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    rdf:           ['application/rdf+xml'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

grails.validateable.classes = [com.grailsrocks.authentication.SignupForm, com.grailsrocks.authentication.LoginForm]
grails.authenticationUserClass = sysmicro.grails.User


environments {
    development {
        grails.logging.jul.usebridge = true
        authenticationUserClass = sysmicro.grails.User

        // log4j configuration
        log4j = {
            // Example of changing the log pattern for the default console appender:
            //
            appenders {

                file name:'file', file: 'logs/phenotator.log', layout:pattern(conversionPattern: '%d %-5p [%t] %C{2} (%F:%L) - %m%n')
                rollingFile name: "stacktrace", maxFileSize: 1024, file: 'logs/stacktrace.log'
            }

            //appenders {
            //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
            //}

            error  'org.codehaus.groovy.grails.web.servlet',        // controllers
                   'org.codehaus.groovy.grails.web.pages',          // GSP
                   'org.codehaus.groovy.grails.web.sitemesh',       // layouts
                   'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
                   'org.codehaus.groovy.grails.web.mapping',        // URL mapping
                   'org.codehaus.groovy.grails.commons',            // core / classloading
                   'org.codehaus.groovy.grails.plugins',            // plugins
                   'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
                   'org.springframework',
                   'org.hibernate',
                   'net.sf.ehcache.hibernate'
        }
    }
    test {
        grails.logging.jul.usebridge = false
        authenticationUserClass = sysmicro.grails.User
        System.properties.setProperty("entityExpansionLimit", "1000000000")

        // log4j configuration
        log4j = {
            // Example of changing the log pattern for the default console appender:
            //
            appenders {

                file name:'file', file: '/ebi/microarray/home/tomcats/ORANGE.FGPT/logs/phenotator.log', layout:pattern(conversionPattern: '%d %-5p [%t] %C{2} (%F:%L) - %m%n')
                rollingFile name: "stacktrace", maxFileSize: 1024,  file: '/ebi/microarray/home/tomcats/ORANGE.FGPT/logs/stacktrace.log'
            }

            //appenders {
            //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
            //}

            error  'org.codehaus.groovy.grails.web.servlet',        // controllers
                   'org.codehaus.groovy.grails.web.pages',          // GSP
                   'org.codehaus.groovy.grails.web.sitemesh',       // layouts
                   'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
                   'org.codehaus.groovy.grails.web.mapping',        // URL mapping
                   'org.codehaus.groovy.grails.commons',            // core / classloading
                   'org.codehaus.groovy.grails.plugins',            // plugins
                   'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
                   'org.springframework',
                   'org.hibernate',
                   'net.sf.ehcache.hibernate'
        }

    }
    production {
        grails.logging.jul.usebridge = false
        authenticationUserClass = sysmicro.grails.User
        // TODO: grails.serverURL = "http://www.changeme.com"
        System.properties.setProperty("entityExpansionLimit", "1000000000")


        // log4j configuration
        log4j = {
            // Example of changing the log pattern for the default console appender:
            //
            appenders {

                file name:'file', file: System.getProperty('catalina.base') + '/logs/' + System.getProperty('tomcat.hostname') + '/phenotator.log', layout:pattern(conversionPattern: '%d %-5p [%t] %C{2} (%F:%L) - %m%n')
                rollingFile name: "stacktrace", maxFileSize: 1024, file: System.getProperty('catalina.base') + '/logs/' + System.getProperty('tomcat.hostname') + '/stacktrace.log'
            }

            //appenders {
            //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
            //}

            error  'org.codehaus.groovy.grails.web.servlet',        // controllers
                   'org.codehaus.groovy.grails.web.pages',          // GSP
                   'org.codehaus.groovy.grails.web.sitemesh',       // layouts
                   'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
                   'org.codehaus.groovy.grails.web.mapping',        // URL mapping
                   'org.codehaus.groovy.grails.commons',            // core / classloading
                   'org.codehaus.groovy.grails.plugins',            // plugins
                   'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
                   'org.springframework',
                   'org.hibernate',
                   'net.sf.ehcache.hibernate'
        }

    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    appenders {

        file name:'file', file: System.getProperty('catalina.base') + '/logs/' + System.getProperty('tomcat.hostname') + '/phenotator.log', layout:pattern(conversionPattern: '%d %-5p [%t] %C{2} (%F:%L) - %m%n')
        rollingFile name: "stacktrace", maxFileSize: 1024, file: System.getProperty('catalina.base') + '/logs/' + System.getProperty('tomcat.hostname') + '/phenotator.log'
    }

    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

grails {
   mail {
     host = "smtp.ebi.ac.uk"
   }
}