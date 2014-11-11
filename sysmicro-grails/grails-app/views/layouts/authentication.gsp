<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js" xmlns="http://www.w3.org/1999/html"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>SysMicro Phenotator</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'grid.css')}" type="text/css">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">


<g:javascript library="jquery"/>
<g:layoutHead/>
<r:layoutResources />


</head>
<body>
<div id="glass">

    <div style="float:right;">
        <auth:ifLoggedIn>
            You are currently logged in as: <auth:user/>
            <auth:form authAction="logout" success="[controller:'home', action:'index']" error="[controller:'home', action:'index']">
                <g:actionSubmit value="Log out"/>
            </auth:form>
        </auth:ifLoggedIn>
    </div>
    <div id="grailsLogo" role="banner"><a href="http://www.ebi.ac.uk"><img src="http://www.ebi.ac.uk/web_guidelines/images/logos/EMBL-EBI/EMBL-EBI_Logo_black_big.png" alt="Phenotator"/></a><h1 style="display: inline;font-size: 300%">Phenotator</h1></div>

    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="/phenotator">Home</a></li>
        </ul>
    </div>

    <g:layoutBody/>
    <div class="footer" role="contentinfo"></div>
    <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
    <g:javascript library="application"/>
    <r:layoutResources />
</body>
</div>
</html>
