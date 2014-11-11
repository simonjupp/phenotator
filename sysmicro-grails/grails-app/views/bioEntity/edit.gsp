<%--
  Created by IntelliJ IDEA.
  User: jupp
  Date: 05/12/2012
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <title>Edit Phenotype</title>
</head>
<body>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLinkTo(dir:'')}">Home</a></li>
        <li><g:link class="list" action="list">Show all phenotypes</g:link></li>
        <li><g:link class="create" action="create">Add new phenotype</g:link></li>
        <li>
            <g:link controller="bioEntity" action="ontology">View ontology</g:link>
        </li>
    </ul>
</div>
<div class="body">
    <h1 style="padding-left: 20px;">Edit Phenotype</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${entityInstanceList}">
        <div class="errors">
            <g:renderErrors bean="${entityInstanceList}" as="list" />
        </div>
    </g:hasErrors>
    <g:form method="post" >
        <input type="hidden" name="id" value="${entityInstanceList?.id}" />
        <input type="hidden" name="version" value="${entityInstanceList?.version}" />
        <g:render template="bioentity" model="['entityInstanceList':entityInstanceList]"/>
        <div class="buttons">
            <span class="button"><g:actionSubmit class="save" value="Update" /></span>
            <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
        </div>
    </g:form>

    <g:render template="bpTreeView"/>






</div>
</body>
</html>
