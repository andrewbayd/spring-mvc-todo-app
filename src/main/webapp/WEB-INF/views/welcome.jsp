<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>
<div class="container">
    <spring:message code="welcome.caption"/> ${username}! <br/>
    <a href="/todos">TODO List</a>
</div>
<%@include file="common/footer.jspf"%>
