<div class="dialog">
    <table>
        <tbody>

        <tr>
            <td valign="top" class="phenotype"><label for="phenotype">Phenotype:</label></td>
            <td valign="top" class="value ${hasErrors(bean:entityInstanceList,field:'phenotype','errors')}">
                <g:textArea type="text" id="phenotype" name="phenotype" value="${fieldValue(bean:entityInstanceList,field:'phenotype')}"/>
            </td>
        </tr>

        <tr class="prop">
            <td valign="top" class="accession"><label for="xref">External reference:</label></td>
            <td valign="top" class="value ${hasErrors(bean:entityInstanceList,field:'xref','errors')}">
                <g:textField type="text" style="width: 450px" id="xref" name="xref" value="${fieldValue(bean:entityInstanceList,field:'xref')}"/>
            </td>
        </tr>


        <tr>
            <td valign="top" class="comment"><label for="comment">Comment:</label></td>
            <td valign="top" class="value ${hasErrors(bean:entityInstanceList,field:'comment','errors')}">
                <g:textArea type="text" id="comment" name="comment" value="${fieldValue(bean:entityInstanceList,field:'comment')}"/>
            </td>
        </tr>
        </tbody>
    </table>

    <h1 style="float:left; padding-left: 20px;padding-right: 20px;">Ontology annotations</h1>

    %{--<div style="padding-left: 20px;"  class="prop">--}%

        %{--<span style="padding-bottom: 5px;" class="annotations">--}%%{--@declare id="annotations"--}%%{--<label for="annotations">Ontology annotations:</label></span>--}%
            <g:render template="annotationEdits" model="['entityInstanceList':entityInstanceList, 'filteredEntityAnnotation':filteredEntityAnnotation]" />
    %{--</div>--}%

    <div style="padding: 20px;" class="prop">
        <span>Can't find a suitable ontology term? <g:link controller="TermRequest" action="create">Then submit a term request</g:link></span>
    </div>

</div>
