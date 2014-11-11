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



<body class="body">

<script type="text/javascript">

    function loadTree() {

        $("#tree").jstree({
            "plugins" : ["themes", "html_data", "types", "sort"],
            "themes" : {
                "theme" : "default",
                "dots" : true,
                "icons" : false,
                "url" : "../"

            }
        }).bind('select_node.jstree', function(e,data) {
                    window.location.href = data.rslt.obj.attr("href");
                });

    }

    $(document).ready ( function () {
        loadTree();
    })

</script>


<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLinkTo(dir:'')}">Home</a></li>
        <li><g:link class="list" action="list">Show all</g:link></li>
        <auth:ifLoggedIn>
            <li><g:link class="create" action="create">Add new phenotype</g:link></li>
        </auth:ifLoggedIn>
        <li>
            <g:link controller="bioEntity" action="downloadOntology">
                Download OWL file</g:link>
        </li>

    </ul>
</div>

<h1 style="padding-left: 20px;">Cellular phenotype ontology</h1>


<div id="tree" style="overflow: scroll;">
    <ul>
        <g:each in="${ontologyNode}" var="rootNode">

            <li class="jstree-open">
                <a href="show/${rootNode.id}">${rootNode.equivLabel}</a>

                <ul>
                    <g:each in="${rootNode.children}" var="childNode">
                        <g:render template="treeNode" model="${[childNode: childNode]}" />
                    </g:each>
                </ul>
            </li>


        </g:each>


    </ul>
</div>




</body>
</html>
