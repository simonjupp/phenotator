

<script type="text/javascript">
    var childCount = ${filteredEntityAnnotation?.size()} + 0;

    function addChild(
            entityLabel, entityUri, entityOid,
            qualityLabel, qualityUri, qualityOid,
            modLabel, modUri, modOid,
            accuracyValue) {
        var htmlId = "annotation-" + childCount;
        var deleteIcon = "${resource(dir:'images/skin', file:'database_delete.png')}";
        var templateHtml = "<tr id='" + htmlId + "' name='" + htmlId + "'>\n";
        templateHtml += "<td>" +
                "<input type='hidden' id='annotation-" + childCount + "-entity-ontology-id' name='annotation-" + childCount + "-entity-ontology-id' value='" + entityOid + "'/>" +
                "<input type='hidden' id='annotation-" + childCount + "-entity-uri' name='annotation-" + childCount + "-entity-uri' value='" + entityUri + "'/>" +
                "<input type='hidden' id='annotation-" + childCount + "-entity-label' name='annotation-" + childCount + "-entity-label' value='" + entityLabel + "'/>" +
                "<a href='" + entityUri + "' target='_blank'>" + entityLabel + "</a>" +
                "</td>\n";
        templateHtml +=
                "<td>" +
                        "<input type='hidden' id='annotation-" + childCount + "-quality-ontology-id' name='annotation-" + childCount + "-quality-ontology-id' value='" + qualityOid + "'/>" +
                        "<input type='hidden' id='annotation-" + childCount + "-quality-uri' name='annotation-" + childCount + "-quality-uri' value='" + qualityUri + "'/>" +
                        "<input type='hidden' id='annotation-" + childCount + "-quality-label' name='annotation-" + childCount + "-quality-label' value='" + qualityLabel + "'/>" +
                        "<a href='" + qualityUri + "' target='_blank'>" + qualityLabel + "</a>" +
                        "</td>\n";

        templateHtml +=
                "<td>" +
                        "<input type='hidden' id='annotation-" + childCount + "-modifier-ontology-id' name='annotation-" + childCount + "-modifier-ontology-id' value='" + modOid + "'/>" +
                        "<input type='hidden' id='annotation-" + childCount + "-modifier-uri' name='annotation-" + childCount + "-modifier-uri' value='" + modUri + "'/>" +
                        "<input type='hidden' id='annotation-" + childCount + "-modifier-label' name='annotation-" + childCount + "-modifier-label' value='" + modLabel + "'/>" +
                        "<a href='" + modUri + "' target='_blank'>" + modLabel + "</a>" +
                        "</td>\n";
        templateHtml += "<td><auth:user/></td>\n";
        templateHtml += "<td>";

        if (accuracyValue == 1) {
            templateHtml += "<input type='hidden' id='annotation-" + childCount + ".accuracy' name='annotation-" + childCount + ".accuracy' value='" + 1 + "'/>Poor match";
        } else if (accuracyValue == 3) {
            templateHtml += "<input type='hidden' id='annotation-" + childCount + ".accuracy' name='annotation-" + childCount + ".accuracy' value='" + 3 + "'/>Good match";
        } else {
            templateHtml += "<input type='hidden' id='annotation-" + childCount + ".accuracy' name='annotation-" + childCount + ".accuracy' value='" + 2 + "'/>Close match";
        }

        templateHtml += "</td>\n";
        templateHtml += "<td><span onClick='$(\"#" + htmlId + "\").remove();'><img src='" + deleteIcon + "' /></span></td>\n";
        templateHtml += "</tr>\n";
        $("#childList").append(templateHtml);

        childCount++;
        $("#annoCount").val(childCount);

    }


    function addAnnotationPopup () {

        $('#entity-input').empty();
        $('#quality-input').empty();
        $('#modifier-input').empty();

        formComplete_setup_functions();


    }

    function addAnnotation() {

        var entityLabel = $('#entity-input').val();
        var qualityLabel = $('#quality-input').val();
        var modifierLabel = $('#modifier-input').val();

        var entityUri = $('#entity-input_bioportal_full_id').val();
        var qualityUri = $('#quality-input_bioportal_full_id').val();
        var modifierUri = $('#modifier-input_bioportal_full_id').val();

        var entityOntoid = $('#entity-input_bioportal_ontology_id').val();
        var qualityOntoid = $('#quality-input_bioportal_ontology_id').val();
        var modifierOntoid = $('#modifier-input_bioportal_ontology_id').val();

        // get the accuracy
        var accuracy = $('input:radio[name=accuracy]:checked').val();;

        if($("input[type='radio'].accuracy").is(':checked')) {
            accuracy = $(this).val();
        }

        // add the value to the page

        addChild(entityLabel, entityUri, entityOntoid,
                qualityLabel, qualityUri, qualityOntoid,
                modifierLabel, modifierUri, modifierOntoid,
                accuracy);

    }

    function clearPopup ()  {
        $('#entity-input').empty();
        $('#quality-input').empty();
        $('#modifier-input').empty();
    }

    $(function() {

        var entity = $( "#entity-input" ),
                quality = $( "#quality-input" ),
                entity_uri = $( "#entity-input_bioportal_full_id" ),
                quality_uri = $( "#quality-input_bioportal_full_id" ),
                allFields = $( [] ).add( entity ).add( quality).add(entity_uri).add(quality_uri),
                tips = $( ".validateTips" );

        function updateTips( t ) {
            tips
                    .text( t )
                    .addClass( "ui-state-highlight" );
            setTimeout(function() {
                tips.removeClass( "ui-state-highlight", 1500 );
            }, 500 );
        }

        function checkLength( o, n) {
            if ( o.val() == "" ) {
                o.addClass( "ui-state-error" );
                updateTips( "You must enter a valid " + n);
                return false;
            } else {
                return true;
            }
        }

        $('#add-annotation-div').dialog({

            autoOpen: false,
            height: 380,
            width: 550,
            modal: true,
            buttons: {
                "Add annotation": function() {
                    var bValid = true;
                    allFields.removeClass( "ui-state-error" );

                    bValid = checkLength( entity, "entity");
                    bValid = checkLength( quality, "quality");
                    bValid = bValid && checkLength( entity_uri, "entity-uri, bioportal plugin may have failed");
                    bValid = bValid && checkLength( quality_uri, "quality-uri, bioportal plugin may have failed");

                    if (bValid) {
                        addAnnotation();
                        $( this ).dialog( "close" );
                    }

                },
                "Cancel": function() {
                    clearPopup();
                    $( this ).dialog( "close" );
                }
            },
            close: function() {
                allFields.val( "" ).removeClass( "ui-state-error" );
                clearPopup();
            }
        });

        $( "#add-annotation" )
                .button()
                .click(function() {
                    $( "#add-annotation-div" ).dialog( "open" );
                });

    });



</script>

<g:hiddenField id="annoCount" name="annoCount" value="${filteredEntityAnnotation?.size()}" />
<input style="display:inline;padding-left: 20px;background-position: left; background-image: url(${resource(dir:'images/skin', file:'database_add.png')});background-repeat:no-repeat;" type="button" value="Add an annotation" id="add-annotation" />

<a style="font-size:smaller;" onclick="toggleElement('annotation-help')">Help?</a>
<p id="annotation-help" style="margin:10px 20px 10px 20px;font-size: smaller;display:none;">An ontology annotation is composed of an Entity and Quality (EQ) pair. You can enter multiple EQ pairs to help capture the phenotype. An entity could be a cellular components, such as a chromosome, or a
physiological process, like mitosis. Examples of a quality are increased size, or increased rate etc.. Simply start typing into the box to autocomplete against the ontology or browse the ontology by clicking the tree like icon. You must use a term provided by the ontology, if you can't find a suitable term, then submit a term request from the link at the bottom of the page.
    <br/><br/>
    We are trying to explore the EQ pattern and existing ontologies for annotation phenotypes, so please use the comments fields and the accuracy measure to say how well the annotation matches the phenotype.</p>
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
    <th>
        Remove
    </th>
    </thead>

    <auth:ifLoggedIn>
        <tbody>
        <g:each var="entityAnnotation" in="${filteredEntityAnnotation}" status="i">
            <g:render template='annotation' model="['entityAnnotation':entityAnnotation,'i':i]"/>
        </g:each>

        </tbody>

    </auth:ifLoggedIn>

</table>

%{--style="display: none;position: absolute;background: #ffffff;"--}%
<div id="add-annotation-div" title="Create annotation">

    <table width="100%">
        <tr>
            <td>Entity</td>
            <td>
                <img style='padding-right:4px;' height='20' width='20' onclick="treeViewer('entity',  'entity-element')" src='http://www.ebi.ac.uk/web_guidelines/images/icons/EBI-Functional/Functional%20icons/treeHeir.png'/>
                <input style="width:260px;" type='text' data-bp_include_definitions='true' class='bp_form_complete-1070-name' id='entity-input' name='entity-input' />
            </td>
        </tr>

        <tr>
            <td>Quality</td>
            <td>
                <img style='padding-right:4px;' height='20' width='20' onclick="treeViewer('quality',  'quality-element')" src='http://www.ebi.ac.uk/web_guidelines/images/icons/EBI-Functional/Functional%20icons/treeHeir.png'/>
                <input style="width:260px;" type='text' data-bp_include_definitions='true' class='bp_form_complete-1107-name' id='quality-input' name='quality-input' />
            </td>
        </tr>

        <tr>
            <td>Qualifier/Modifier</td>
            <td>
                <img style='padding-right:4px;' height='20' width='20' onclick="treeViewer('modifier',  'modifier-element')" src='http://www.ebi.ac.uk/web_guidelines/images/icons/EBI-Functional/Functional%20icons/treeHeir.png'/>
                <input style="width:260px;" type='text' data-bp_include_definitions='true' class='bp_form_complete-1070,1107-name' id='modifier-input' name='modifier-input' />
            </td>
        </tr>

        <tr>
            <td>Accuracy</td>
            <td>
                <label class='accuracy-block'> <input type='radio'  name='accuracy' value='1' />&nbsp;Poor match</label>
                <label class='accuracy-block'>  <input type='radio' name='accuracy' value='2' checked='checked' />&nbsp;Close match</label>
                <label class='accuracy-block'>  <input type='radio' name='accuracy' value='3' />&nbsp;Good match</label>
            </td>
        </tr>

    </table>

    %{--<table>--}%

        %{--<tr>--}%
            %{--<th>Phenotype</th>--}%
            %{--<th>Entity</th>--}%
            %{--<th>Quality</th>--}%
            %{--<th>Modifier/Qualifier</th>--}%
        %{--</tr>--}%

        %{--<tr>--}%
            %{--<td>--}%
                %{--Small cell--}%
            %{--</td>--}%
            %{--<td>--}%
                %{--<a href="http://purl.obolibrary.org/obo/GO_0005623">cell</a>--}%
            %{--</td>--}%
            %{--<td>--}%
                %{--<a href="http://purl.obolibrary.org/obo/PATO_0000587">cell</a>--}%
                %{--'descreased size' (PATO:XXXXXX)--}%
            %{--</td>--}%
            %{--<td>--}%

            %{--</td>--}%
        %{--</tr>--}%
        %{--<tr>--}%
            %{--<td>--}%
                %{--Strange nuclear shape--}%
            %{--</td>--}%
            %{--<td>--}%
                %{--nucelus--}%
            %{--</td>--}%
            %{--<td>--}%
                %{--morphology--}%
            %{--</td>--}%
            %{--<td>--}%
                %{--abnormal--}%
            %{--</td>--}%
        %{--</tr>--}%
        %{--<tr>--}%
            %{--<td>--}%
                %{--Increased cell number in S--}%
            %{--</td>--}%
            %{--<td>--}%
                %{--cell--}%
            %{--</td>--}%
            %{--<td>--}%
                %{--increased accumulation--}%
            %{--</td>--}%
            %{--<td>--}%
                %{--phase of mitotic cell cycle--}%
            %{--</td>--}%
        %{--</tr>--}%

        %{--<tr>--}%
            %{--<td>--}%
                %{--absence of c--}%
            %{--</td>--}%
            %{--<td>--}%
                %{--cell--}%
            %{--</td>--}%
            %{--<td>--}%
                %{--increased accumulation--}%
            %{--</td>--}%
            %{--<td>--}%
                %{--phase of mitotic cell cycle--}%
            %{--</td>--}%
        %{--</tr>--}%

    %{--</table>--}%

</div>

