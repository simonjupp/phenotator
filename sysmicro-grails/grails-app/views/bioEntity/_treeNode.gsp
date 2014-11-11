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
    <title>View ontology</title>

</head>

<li class="jstree-close">

<a href="#">${childNode.equivLabel}</a>

<ul>
<g:each in="${childNode.children}" var="decNode">
        <g:render template="treeNode" model="${[childNode: decNode]}" />
</g:each>
</ul>


</li>


</body>
</html>
