<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <title>Send Password Reminder</title>
</head>



<body>


<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLinkTo(dir:'')}">Home</a></li>
    </ul>

</div>

<div style="margin-left: 20px;" class="body">

    <h1>New Password Request</h1>
    <p>Enter your e-mail address and we will send you a new randomly generated password</p>
    <g:form controller="reminder" action="send" method="post">
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>

        Please enter your e-mail address: <g:textField name="email"/>
        <span class="value ${hasErrors(field:'email','errors')}"></span>
        <g:actionSubmit value="Send"/>

    </g:form>

</div>

</body>
</html>