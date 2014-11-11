package sysmicro.grails

class FileDownloadController {

    def index() {
    }

    def help () {

        def fileDoc = grailsAttributes.getApplicationContext().getResource("/docs/PHENOTATOR_instructions.pdf").file;
        if(fileDoc.exists()){
            // force download
            def fileName = fileDoc.getName();
            response.setContentType("application/pdf")
            response.setHeader "Content-disposition", "attachment; filename=${fileName}" ;
            response.outputStream << fileDoc.newInputStream();
            response.outputStream.flush();
            return true;
       }
    }

}
