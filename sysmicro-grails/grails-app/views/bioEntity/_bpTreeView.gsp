
<script>


</script>

<div id="patoTreeViewDiv" title="PATO browser" style="background-color:#f5f5dc;
border: solid 1px black;
/*position: absolute;*/
z-index: 1000000;
/*margin-top: -50%;*/
/*margin-left: 2%;*/
/*height: 400px;*/
width: 350px;
overflow: scroll;
">

    %{--<hr class="space"/>--}%
    %{--<div style="padding:5px;float:right;"><img alt="close" height="15" width="15" src='http://www.ebi.ac.uk/web_guidelines/images/icons/EBI-Functional/Functional%20icons/close.png' onclick="$('#patoTreeViewDiv').hide()"/> </div>--}%

    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
    	id="OntologyTreePato" width="350" height="100%"
    	codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
    	<param name="movie" value="http://keg.cs.uvic.ca/ncbo/ontologytree/OntologyTree.swf" />
    	<param name="quality" value="high" />
        <param name="bgcolor" value="#f5f5dc" />
        <param name="wmode" value="opaque" />
    	<param name="allowScriptAccess" value="always" />
    	<param name="flashVars" value="virtual=true&ontology=1107&alerterrors=false&canchangeontology=true" />
    	<embed src="http://keg.cs.uvic.ca/ncbo/ontologytree/OntologyTree.swf" quality="high" bgcolor="#f5f5dc"
    		height="400" name="OntologyTreePato" align="middle"
    		play="true"
    		loop="false"
            wmode="opaque"
    		allowScriptAccess="always"
    		type="application/x-shockwave-flash"
    		flashVars="virtual=true&ontology=1107&alerterrors=true&canchangeontology=true"
    		pluginspage="http://www.adobe.com/go/getflashplayer">
    	</embed>
    </object>
</div>

<div id="goTreeViewDiv" title="GO browser" style="background-color:#f5f5dc;
display: none;
border: solid 1px black;
/*position: absolute;*/
z-index: 1000000;
/*margin-top: -50%;*/
/*margin-left: 2%;*/
/*height: 400px;*/
/*width: 300px;*/
overflow: scroll;

">

    %{--<hr class="space"/>--}%
    %{--<div style="padding:5px;float:right;"><img alt="close" height="15" width="15" src='http://www.ebi.ac.uk/web_guidelines/images/icons/EBI-Functional/Functional%20icons/close.png' onclick="$('#goTreeViewDiv').hide()"/> </div>--}%

    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
    	id="OntologyTreeGo" width="350" height="100%"
    	codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
    	<param name="movie" value="http://keg.cs.uvic.ca/ncbo/ontologytree/OntologyTree.swf" />
    	<param name="quality" value="high" />
        <param name="bgcolor" value="#f5f5dc" />
        <param name="wmode" value="opaque" />
    	<param name="allowScriptAccess" value="always" />
    	<param name="flashVars" value="virtual=true&ontology=1070&alerterrors=false&canchangeontology=true" />
    	<embed src="http://keg.cs.uvic.ca/ncbo/ontologytree/OntologyTree.swf" quality="high" bgcolor="#f5f5dc"
               height="400" name="OntologyTreeGo" align="middle"
    		play="true"
    		loop="false"
            wmode="opaque"
    		allowScriptAccess="always"
    		type="application/x-shockwave-flash"
    		flashVars="virtual=true&ontology=1070&alerterrors=true&canchangeontology=false"
    		pluginspage="http://www.adobe.com/go/getflashplayer">
    	</embed>
    </object>
</div>
