<%=packageName%>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create phenotype entry</title>
    </head>

    <body>


    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLinkTo(dir:'')}">Home</a></li>
            <li><g:link class="list" action="list">Show all phenotypes</g:link></li>
            <li>
                <g:link controller="bioEntity" action="ontology">View ontology</g:link>
            </li>
        </ul>
    </div>
        <div class="body">
            <h1 style="padding-left: 20px;">Create new phenotype entry</h1>
            <p style="margin:10px 20px 10px 20px;font-size: smaller;">Enter a description of an observed phenotype. You can also include an optional reference to the sample/experiment where the observation was made. There is a space for any additional comments
            about this phenotype. When you are done, use the "Add Ontology Annotation" button at the bottom to try add a controlled vocabulary based annotation for this phenotype.</p>

            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${entityInstanceList}">
            <div class="errors">
                <g:renderErrors bean="${entityInstanceList}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <g:render template="bioentity" model="['entityInstanceList':entityInstanceList, 'filteredEntityAnnotation':filteredEntityAnnotation]"/>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>

            <g:render template="bpTreeView"/>
        </div>
    </body>
</html>
