<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp"/>

<div class="confirm">
    <form method="post">
        Czy na pewno chcesz usunąć przepis z planu?
        <input type="submit" value="OK">
    </form>
    <input type="button" onclick="location.href='${pageContext.request.contextPath}/app/plan/details?id=${planId}';" value="Anuluj" />





</div>



<jsp:include page="footer.jsp"/>