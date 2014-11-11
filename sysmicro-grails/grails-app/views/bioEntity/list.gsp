<%--
  Created by IntelliJ IDEA.
  User: jupp
  Date: 04/12/2012
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <title>SysMicro cell phenotator</title>
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
    <h1 style="padding-left: 20px;float: left;">All phenotypes</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${entityInstanceList}">
        <div class="errors">
            <g:renderErrors bean="${entityInstanceList}" as="list" />
        </div>
    </g:hasErrors>

    <fieldset class="form" style="text-align: right;">
        <g:form action="list" method="GET">
            <div class="fieldcontain">
                <label for="query">Search for phenotype:</label>
                <g:textField name="query" value="${params.query}"/>
            </div>
        </g:form>
    </fieldset>

    <table>
        <thead>
        %{--<g:sortableColumn property="accession" title="Accession" />--}%
        %{--<g:sortableColumn property="species.label" title="Species" />--}%
        <g:sortableColumn property="phenotype" title="Phenotype" />
        <th>Ontology annotations </th>
        <auth:ifLoggedIn>
            <th>Actions </th>
        </auth:ifLoggedIn>

        </thead>
        <tbody>

        <g:each in="${entityInstanceList}" status="i" var="entity">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <g:form>
                    <input type="hidden" name="id" value="${entity.id}" />

                    <td><g:link action="show" id="${entity.id}">${entity.phenotype.encodeAsHTML()}</g:link></td>
                    <td>
                        <ul style="list-style-type: none;">
                            <g:each var="annotation" in="${entity.entityAnnotation}">
                                <li>
                                <a href="${annotation.entity.uri}">'${annotation.entity.label.encodeAsHTML()}'</a>
                                    &nbsp;<span style="font-style: italic;color: #87cefa;">has quality</span>
                                <a href="${annotation.quality.uri}">'${annotation.quality.label.encodeAsHTML()}'</a>
                                    <g:if test="${annotation.modifier}">
                                        &nbsp;<span style="font-style: italic;color: #87cefa;">has modifier</span>
                                    <a href="${annotation.modifier.uri}">'${annotation.modifier.label.encodeAsHTML()}'</a>

                                    </g:if>

                                </li>
                            </g:each>
                        </ul>
                    </td>
                %{--<td>${entity.species.label.encodeAsHTML()}</td>--}%
                    <auth:ifLoggedIn>
                        <td>

                            <div class="buttons">
                                <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                                %{--<span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>--}%
                            </div>
                        </td>
                    </auth:ifLoggedIn>
                </g:form>
            </tr>
        </g:each>
        </tbody>
    </table>


    <div class="pagination">
        <g:paginate controller="BioEntity" action="list"  params="[query:query]" total="${entityInstanceTotal}" />
    </div>

</div>

</body>
</html>