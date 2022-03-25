<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="headerLoggedIn.jsp"/>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <jsp:include page="left_panel.jsp"></jsp:include>
        <div class="m-4 p-3 width-medium">
            <div class="m-4 p-3 border-dashed view-height">

                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">LISTA UŻYTKOWNIKÓW</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="${pageContext.request.contextPath}/app/dashboard" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-1">ID</th>
                            <th class="col-3">IMIĘ</th>
                            <th class="col-6">NAZWISKO</th>
                            <th class="col-2 center">AKCJE</th>
                        </tr>
                        </thead>
                        <tbody class="text-color-lighter">
                        <c:forEach items="${users}" var="user">
                            <tr class="d-flex">
                                <td class="col-1">${user.id}</td>
                                <td class="col-3">${user.firstName}</td>
                                <td class="col-6">${user.lastName}</td>
                                <td class="col-2 center">
                                    <c:if test="${user.superadmin == 0 && current == 1 && user.enable == 1}">
                                        <a id="block" href="${pageContext.request.contextPath}/app/blockUser?id=${user.id}" class="btn btn-danger rounded-0 text-light m-1">Blokuj</a>
                                    </c:if>
                                    <c:if test="${user.enable == 0}">
                                        <a id="unblock" href="${pageContext.request.contextPath}/app/blockUser?id=${user.id}" class="btn btn-danger rounded-0 text-light m-1">Zablokowany</a>
                                    </c:if>

                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>