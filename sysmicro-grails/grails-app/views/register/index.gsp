<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Phenotator Registration</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
</head>
<body>

<div style="margin-left:20px;margin-top:20px;">
    <h2>Register</h2>
    <p style="font-size:smaller;">Apologies for making you sign up for this, but this will help us track user input and help with our evaluation.</p>
    <auth:form authAction="signup"
               success="[controller:'home', action:'index']"
               error="[controller:'register', action:'index']">

        <div class="dialog">
   <table>
       <tbody>

       <tr>
       <td valign="top" style="width:130px;">
       User ID:
        </td>
        <td valign="top">
        <g:textField name="login" value="${flash.signupForm?.login?.encodeAsHTML()}"/>
        </td>

        <td>
            <g:hasErrors bean="${flash.signupFormErrors}" field="login"><g:renderErrors bean="${flash.signupFormErrors}" as="list" field="login"/></g:hasErrors>
        </td>
        </tr>
        <tr>
            <td valign="top">
                Email:
            </td>
            <td valign="top">
                <g:textField name="email" value="${flash.signupForm?.email?.encodeAsHTML()}"/>
            </td>
            <td>
                <g:hasErrors bean="${flash.signupFormErrors}" field="email"><g:renderErrors bean="${flash.signupFormErrors}" as="list" field="email"/></g:hasErrors>
            </td>

        </tr>
        <tr>
            <td valign="top">
                Password:
            </td>
            <td valign="top">
                <input name="password" value="" type="password"/>
            </td>

            <td><g:hasErrors bean="${flash.signupFormErrors}" field="password"><g:renderErrors bean="${flash.signupFormErrors}" as="list" field="password"/></g:hasErrors></td>
        </tr>
        <tr>
            <td valign="top">
                Confirm password:
            </td>
            <td valign="top">
                <input name="passwordConfirm" value="" type="password"/>
            </td>
            <td>
                <g:hasErrors bean="${flash.signupFormErrors}" field="passwordConfirm"><g:renderErrors bean="${flash.signupFormErrors}" as="list" field="passwordConfirm"/></g:hasErrors>
            </td>
        </tr>

        </tbody>
     </table>

        <g:actionSubmit value="Sign up"/>
    </auth:form>

</div>
</div>
</body>
</html>