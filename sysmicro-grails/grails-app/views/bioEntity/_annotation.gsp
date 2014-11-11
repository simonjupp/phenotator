
<tr id="annotation-${i}">

    <g:hiddenField name='annotation-${i}.id' value='${entityAnnotation.id}'/>
    <td>
        <g:hiddenField name='annotation-${i}-entity-uri' value='${entityAnnotation.entity.uri}'/>
        <g:hiddenField name='annotation-${i}-entity-ontology-id' value='${entityAnnotation.entity.ontologyId}'/>
        <g:hiddenField name='annotation-${i}-entity-label' value='${entityAnnotation.entity.label}'/>
    <a href='${entityAnnotation.entity.uri}' target="_blank">${entityAnnotation.entity.label}</a>
    </td>
    <td>
        <g:hiddenField name='annotation-${i}-quality-uri' value='${entityAnnotation.quality.uri}'/>
        <g:hiddenField name='annotation-${i}-quality-ontology-id' value='${entityAnnotation.quality.ontologyId}'/>
        <g:hiddenField name='annotation-${i}-quality-label' value='${entityAnnotation.quality.label}'/>
        <a href='${entityAnnotation.quality.uri}' target="_blank">${entityAnnotation.quality.label}</a>
    </td>
    <td>
        <g:if test="${entityAnnotation.modifier?.uri}">
            <g:hiddenField name='annotation-${i}-modifier-uri' value='${entityAnnotation.modifier.uri}'/>
            <g:hiddenField name='annotation-${i}-modifier-ontology-id' value='${entityAnnotation.modifier.ontologyId}'/>
            <g:hiddenField name='annotation-${i}-modifier-label' value='${entityAnnotation.modifier.label}'/>
            <a href='${entityAnnotation.modifier.uri}' target="_blank">${entityAnnotation.modifier.label}</a>

        </g:if>
    </td>
    <td>
        ${entityAnnotation.user}
    </td>
    <td>
        <g:if test="${entityAnnotation.accuracy.equals('1')}">
            <g:hiddenField name='annotation-${i}.accuracy' value='${entityAnnotation.accuracy}'/>Poor match
        </g:if>
        <g:elseif test="${entityAnnotation.accuracy == 3}">
            <g:hiddenField name='annotation-${i}.accuracy' value='${entityAnnotation.accuracy}'/>Good match
        </g:elseif>
        <g:else>
            <g:hiddenField name='annotation-${i}.accuracy' value='2'/>Close match
        </g:else>
        %{--<g:radioGroup name='annotation-${i}.accuracy' values="[1,2,3]" labels="['Poor','Close','Good']" value='${entityAnnotation.accuracy}' >--}%
            %{--<label class="accuracy-block">${it.radio} <g:message code="${it.label}" />  </label>--}%
        %{--</g:radioGroup>--}%

    </td>

    <td>
        <input type="hidden" name='annotation-${i}.deleted' id='annotation[${i}].deleted' value='false'/>
        <span onClick="$('#annotation\\[${i}\\]\\.deleted').val('true'); $('#annotation-${i}').hide()"><img src="${resource(dir:'images/skin', file:'database_delete.png')}" /></span>
    </td>

</tr>
