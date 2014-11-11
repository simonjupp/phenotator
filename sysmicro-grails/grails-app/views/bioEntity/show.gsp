<%--
  Created by IntelliJ IDEA.
  User: jupp
  Date: 05/12/2012
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <title>Show Phenotype</title>
</head>
<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLinkTo(dir:'')}">Home</a></li>
        <li><g:link class="list" action="list">Show all</g:link></li>
        <auth:ifLoggedIn>
            <li><g:link class="create" action="create">Add new phenotype</g:link></li>
        </auth:ifLoggedIn>
        <li>
            <g:link controller="bioEntity" action="ontology">View ontology</g:link>
        </li>
    </ul>
</div>
<div class="body">
    <h1 style="padding-left: 20px;">Phenotype entry</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>


            %{--<tr class="prop">--}%
            %{--<td valign="top" class="name">Id:</td>--}%
            %{--<td valign="top" class="value">${fieldValue(bean:entityInstanceList, field:'id')}</td>--}%
            %{--</tr>--}%

            <tr class="prop">
                <td valign="top" class="name">Phenotype:</td>
                <td valign="top" class="value">${fieldValue(bean:entityInstanceList, field:'phenotype')}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name">External reference:</td>
                <td valign="top" class="value">${fieldValue(bean:entityInstanceList, field:'xref')}</td>
            </tr>


            <tr class="prop">
                <td valign="top" class="name">Comment:</td>
                <td valign="top" class="value">${fieldValue(bean:entityInstanceList, field:'comment')}</td>
            </tr>


            </tbody>
        </table>

        <h1 style="padding-left: 20px;">Ontology annotations</h1>

        <table id="childList">
            <thead>
            <th>
                Entity
            </th>
            <th>
                Quality
            </th>
            <th>
                Modifier
            </th>
            <th>
                Annotator
            </th>
            <th>
                Accuracy
            </th>
            </thead>

            <tbody>
            <g:each var="entityAnnotation" in="${filteredEntityAnnotation}" status="i">
                <g:render template='annotationShow' model="['entityAnnotation':entityAnnotation,'i':i]"/>
            </g:each>

            </tbody>
        </table>


    </div>
    <div class="buttons">
        <g:form>
            <input type="hidden" name="id" value="${entityInstanceList?.id}" />
            <auth:ifLoggedIn>
                <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
            </auth:ifLoggedIn>
        </g:form>
    </div>
</div>
</body>
</html>
