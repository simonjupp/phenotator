if (typeof jQuery !== 'undefined') {
    (function($) {
        $('#spinner').ajaxStart(function() {
            $(this).fadeIn();
        }).ajaxStop(function() {
                $(this).fadeOut();
            });
    })(jQuery);
}

var patoFile = "/sysmicro-grails/ontology/pato.owl";
var patoOntologyId = 1107;
var patoRoot = "http://purl.obolibrary.org/obo/PATO_0000001";

var goFile = "/sysmicro-grails/ontology/go.owl";
var goOntologyId = 1070;
var goRoot= "http://www.ebi.ac.uk/GeneOntology";

var rootTerm;
var ontologyFile;

var viewId;
var currentElement;

$(function() {

    $('#patoTreeViewDiv').dialog({

        autoOpen: false,
        minWidth: 350,
        zindex: 1000000,
        modal: false
    });

    $('#goTreeViewDiv').dialog({

        autoOpen: false,
        minWidth: 350,
        zindex: 1000000,
        modal: false
    });

});

function treeViewer (type, element) {


    currentElement = element;

//    var left = (window.width/2);
//    var top = (window.height/2);

//    $('#treeViewDiv').height(300);
//    $('#treeViewDiv').width(300);
    if (type == 'quality') {
        viewId = '#patoTreeViewDiv';
//        $('#patoTreeViewDiv').css('top',top);
//        $('#patoTreeViewDiv').css('left',left);
//        $('#patoTreeViewDiv').show();
        $( "#patoTreeViewDiv" ).dialog( "open" );

    }

    else if (type == 'entity') {
        viewId = '#goTreeViewDiv';
//        $('#goTreeViewDiv').css('top', top);
//        $('#goTreeViewDiv').css('left', left);
//        $('#goTreeViewDiv').show();
        $( "#goTreeViewDiv" ).dialog( "open" );
    }
}


function toggleElement(id){
    var e = document.getElementById(id);
    e.style.display = e.style.display != 'block' ? 'block' : 'none';
  };

/*
 * The following JavaScript functions allow you to interact with the Flash BioPortal Ontology Tree.
 * You can load an ontology by id, and get the current ontology id or name.
 * Once an ontology is loaded then you can get the currently selected concept by id or name,
 * and you can also select a concept by id or name.
 * You can also listen for one of these events by implementing the following functions (see the stubs below):
 * appComplete, treeSelectionChanged, treeNodeDoubleClicked, or errorLoadingOntology
 *
 * These are the parameters that you can pass into the application using the "flashVars" parameter:
 * (see the examples below):
 * - ontology: the id of the ontology (version or virtual)
 * - virtual: if false (default) then the ontology id above is the version id
 * 			  if true then the above ontology id is assumed to be the virtual id
 * - alerterrors: (true/false) determines whether the application will display errors (default is false)
 * - server: defines the URL of the rest server (null by default)
 * - title: changes the default title for the page
 * - canchangeontology: if true then the ontology can be changed
 * - rootconceptid: sets the optional root node of the tree
 * - canchangeroot: if false then the root of the tree cannot be changed by the user (using context menu items)
 */

function getAppById(id) {
    if (navigator.appName.indexOf ("Microsoft") != -1) {
        app = window[id];
    } else {
        app = document[id];
    }
    if (app == null) {
        app = document.getElementById(id);
    }
    return app;
}

// get a handle for the flash application
function getApp() {
    if (navigator.appName.indexOf ("Microsoft") != -1) {
        app = window["OntologyTreePato"];
    } else {
        app = document["OntologyTreePato"];
    }
    if (app == null) {
        app = document.getElementById("OntologyTreePato");
    }
    if (app == null) {
        alert("Could not get Flash object, JavaScript/Flex communication failed.");
    }
    return app;
}

// these are the available functions that you can call ONCE the
// flash SWF has finished loading

/** Loads a new ontology by id. */
function loadOntology(ontologyID) {
    var app = getApp();
    if (app && app.loadOntology) {
        app.loadOntology(ontologyID);
    }
}

/** Gets the id of the current ontology, will be null if no ontology is loaded. */
function getOntologyID() {
    var ontologyID = null;
    var app = getApp();
    if (app && app.getOntologyID) {
        ontologyID = app.getOntologyID();
    }
    return ontologyID;
}

/** Gets the name of the current ontology, will be null if no ontology is loaded. */
function getOntologyName() {
    var ontologyName = null;
    var app = getApp();
    if (app && app.getOntologyName) {
        ontologyName = app.getOntologyName();
    }
    return ontologyName;
}

/** Gets the id of the currently selected concept, will be null if nothing is selected. */
function getSelectedConceptID() {
    var conceptID = null;
    var app = getApp();
    if (app && app.getSelectedConceptID) {
        conceptID = app.getSelectedConceptID();
    }
    return conceptID;
}

/**
 * Gets the full id (not all ontologies support this) of the currently selected concept.
 * Will be null if nothing is selected, will be the same as the conceptID if no fullID exists.
 */
function getSelectedConceptFullID() {
    var conceptID = null;
    var app = getApp();
    if (app && app.getSelectedConceptFullID) {
        conceptID = app.getSelectedConceptFullID();
    }
    return conceptID;
}

/** Gets the name of the currently selected concept, will be null if nothing is selected. */
function getSelectedConceptName() {
    var conceptName = null;
    var app = getApp();
    if (app && app.getSelectedConceptName) {
        conceptName = app.getSelectedConceptName();
    }
    return conceptName;
}

/** Loads and selects a concept (by id) in the current ontology. */
function loadConceptByID(conceptID) {
    var app = getApp();
    if (app && app.loadConceptByID) {
        app.loadConceptByID(conceptID);
    }
}

/** Attempts to load and select a concept (by name) in the current ontology. */
function loadConceptByName(conceptName) {
    var app = getApp();
    if (app && app.loadConceptByName) {
        alert ('loading:' + conceptName)
        app.loadConceptByName(conceptName);
    }
}

/** This function gets call by flash when the swf has finished loading. */
function appComplete(swfID) {
//	alert("flash app finished loading: " + swfID);
}

/** Implement this function to listen for tree selection changes */
function treeSelectionChanged(nodeID, nodeName, swfID) {
//	alert("tree selection: " + nodeID + " - " + nodeName + " - " + swfID);
}

/** Implement this function to listen for tree double click events */
function treeNodeDoubleClicked(nodeID, nodeName, swfID) {


    var url =  "http://purl.obolibrary.org/obo/" + nodeID.replace(":", "_");
//    $("#" + currentElement + "-uri").val(url);
    if  (/PATO/i.test(nodeID)) {
//        $("#" + currentElement + "-ontology-id").val(patoOntologyId);
        $("#quality-input").val(nodeName);
        $("#quality-input_bioportal_ontology_id").val(patoOntologyId);
        $("#quality-input_bioportal_full_id").val(url);
    }
    else {
        $("#entity-input").val(nodeName);
        $("#entity-input_bioportal_ontology_id").val(goOntologyId);
        $("#entity-input_bioportal_full_id").val(url);
    }
//    $("#" + currentElement).val(nodeName);
    $( viewId ).dialog( "close" );


//	alert("tree node double clicked: " + nodeID + " - " + nodeName + " - " + swfID);
}

/** Implement this function to listen for error messages when loading an ontology */
function errorLoadingOntology(errorMsg, swfID) {
    alert("Error: " + errorMsg);
}

