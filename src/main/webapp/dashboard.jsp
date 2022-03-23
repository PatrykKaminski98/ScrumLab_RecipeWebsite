<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="headerLoggedIn.jsp"/>

<%-- Poprawic linki jak juz beda strony--%>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <ul class="nav flex-column long-bg">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/dashboard">
                    <span>Pulpit</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/recipe/list">
                    <span>Przepisy</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app-schedules">
                    <span>Plany</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app-edit-user-data">
                    <span>Edytuj dane</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="${pageContext.request.contextPath}/app-edit-password.html">
                    <span>Zmień hasło</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/super-admin-users.html">
                    <span>Użytkownicy</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
        </ul>

        <div class="m-4 p-4 width-medium">
            <div class="dashboard-header m-4">
                <div class="dashboard-menu">
                    <div class="menu-item border-dashed">
                        <a href="">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj plan</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis do planu</span>
                        </a>
                    </div>
                </div>

                <div class="dashboard-alerts">
                    <div class="alert-item alert-info">
                        <i class="fas icon-circle fa-info-circle"></i>
                        <span id="recipeQty" class="font-weight-bold">Liczba przepisów: ${recipe}</span>
                    </div>
                    <div class="alert-item alert-light">
                        <i class="far icon-calendar fa-calendar-alt"></i>
                        <span id="planQty" class="font-weight-bold">Liczba planów: ${planQty}</span>
                    </div>
                </div>
            </div>
            <div class="m-4 p-4 border-dashed">
                <h2 class="dashboard-content-title">
                    <span id="lastPlan">Ostatnio dodany plan:</span> TO TEZ DOROBIC
                </h2>
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Poniedziałek</th>
                        <th class="col-8"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="d-flex">
                        <td class="col-2">śniadanie</td>
                        <td class="col-8">płatki owsiane z jagodami i komosą ryżową</td>
                        <td class="col-2"><button type="button" class="btn btn-primary rounded-0">Szczegóły</button></td>
                    </tr>
                    <tr class="d-flex">
                        <td class="col-2">śniadanie</td>
                        <td class="col-8">kanapka z pastą rybną</td>
                        <td class="col-2"><button type="button" class="btn btn-primary rounded-0">Szczegóły</button></td>
                    </tr>
                    <tr class="d-flex">
                        <td class="col-2">obiad</td>
                        <td class="col-8">zupa pomidorowa</td>
                        <td class="col-2"><button type="button" class="btn btn-primary rounded-0">Szczegóły</button></td>
                    </tr>
                    </tbody>
                </table>
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Wtorek</th>
                        <th class="col-8"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="d-flex">
                        <td class="col-2">śniadanie</td>
                        <td class="col-8">płatki owsiane z jagodami i komosą ryżową</td>
                        <td class="col-2"><button type="button" class="btn btn-primary rounded-0">Szczegóły</button></td>
                    </tr>
                    <tr class="d-flex">
                        <td class="col-2">drugie śniadanie</td>
                        <td class="col-8">pączki</td>
                        <td class="col-2"><button type="button" class="btn btn-primary rounded-0">Szczegóły</button></td>
                    </tr>
                    <tr class="d-flex">
                        <td class="col-2">obiad</td>
                        <td class="col-8">schabowy w panierce</td>
                        <td class="col-2"><button type="button" class="btn btn-primary rounded-0">Szczegóły</button></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>