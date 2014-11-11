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
%{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'jOWL.css')}" type="text/css">--}%

<g:javascript library="jquery"/>
<g:javascript library="jquery-ui"/>
<g:javascript library="jsTree"/>

<g:layoutHead/>
<r:layoutResources />
<jsTree:resources />

<script type="text/javascript" src="${resource(dir: 'js', file: 'form_complete.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.tooltip.js')}"></script>
<script type="text/javascript" src="http://keg.cs.uvic.ca/ncbo/ontologytree/AC_OETags.js"></script>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui-1.10.3.custom.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'themes/default/style.css')}" type="text/css">

</head>
<body>

    <div style="float:right;">
        <auth:ifLoggedIn>
            <div style="text-align: right; padding-right:10px;">

            <p>Logged in as: <auth:user/></p>
            <auth:form authAction="logout" success="[controller:'home', action:'index']" error="[controller:'home', action:'index']">
                <g:actionSubmit value="Log out"/>
            </auth:form>
            </div>
        </auth:ifLoggedIn>
        <auth:ifNotLoggedIn>
            <div style="text-align: right; padding-right:10px;">

            <g:if test="${flash.authenticationFailure}">
                <p>Sorry but your login failed - reason: <g:message code="authentication.failure.${flash.authenticationFailure.result}"/> <g:link controller="register" action="index">Register?</g:link></p>
            </g:if>
                <g:else>
                        <p>You are not logged in. Please log in or <g:link controller="register" action="index">register</g:link>:</p>
                </g:else>


            <auth:form authAction="login" success="[controller:'home', action:'index']" error="[controller:'home', action:'index']">
                <div style="float:right;">User ID: <g:textField name="login" value="${flash.loginForm?.login?.encodeAsHTML()}"/></div><br/>
                <g:hasErrors bean="${flash.loginFormErrors}" field="login"><g:renderErrors bean="${flash.loginFormErrors}" as="list" field="login"/></g:hasErrors>
                <div style="float:right;">Password: <input name="password" value="" type="password"/></div><br/> <br/>
                <g:hasErrors bean="${flash.loginFormErrors}" field="password"><g:renderErrors bean="${flash.loginFormErrors}" as="list" field="password"/></g:hasErrors>
                <div style="float:right;"><g:link style="font-size: smaller;padding-right: 10px;" controller="reminder">new password?</g:link><g:actionSubmit value="Log in"/></div>
            </auth:form>
            </div>
        </auth:ifNotLoggedIn>
    </div>
    <div id="grailsLogo" role="banner" style="height:130px;"><a href="http://www.ebi.ac.uk"><img src="http://www.ebi.ac.uk/web_guidelines/images/logos/EMBL-EBI/EMBL-EBI_Logo_black_big.png" alt="Phenotator"/></a><h1 style="display: inline;font-size: 300%;">Phenotator</h1></div>


    <g:layoutBody/>
    <div class="footer" role="contentinfo">

    <section id="ebi-footer-meta">
        <p class="address">EMBL-EBI, Wellcome Trust Genome Campus, Hinxton, Cambridgeshire, CB10 1SD, UK &nbsp; &nbsp; +44 (0)1223 49 44 44</p>
        <p class="legal">Copyright &copy; EMBL-EBI 2013 | EBI is an outstation of the <a href="http://www.embl.org">European Molecular Biology Laboratory</a> | <a href="/about/privacy">Privacy</a> | <a href="/about/cookies">Cookies</a> | <a href="/about/terms-of-use">Terms of use</a></p>
    </section>

    </div>
    %{--<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>--}%
    <g:javascript library="application"/>
    <r:layoutResources />
</body>
</html>
