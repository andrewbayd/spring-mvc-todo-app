<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>

    <div class="container">
        <h1>Create a todo</h1>
        <f:form method="post" commandName="todo">

            <f:hidden path="id"/>
            <f:hidden path="user"/>

            <fieldset class="form-group">
                <f:label path="desc">Description</f:label>
                <f:input path="desc" type="text" class="form-control" required="required"/>
                <f:errors path="desc" cssClass="text-warning"/>
            </fieldset>

            <fieldset class="form-group">
                <f:label path="targetDate">Target date</f:label>
                <f:input path="targetDate" type="text" class="form-control" required="required"/>
                <f:errors path="targetDate" cssClass="text-warning"/>
            </fieldset>

            <input class="btn btn-success" type="submit" value="Submit"/>
        </f:form>
    </div>

<%@include file="common/footer.jspf"%>