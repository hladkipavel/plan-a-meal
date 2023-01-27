<%--
  Created by IntelliJ IDEA.
  User: pavelgladky
  Date: 26/01/2023
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/loginHeader.jsp"></jsp:include>
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
                        <a href="/app/addPlan.jsp">
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
                        <span class="font-weight-bold">Liczba przepisów: <c:out value="${countRecipe}" default="0"/></span>
                    </div>
                    <div class="alert-item alert-light">
                        <i class="far icon-calendar fa-calendar-alt"></i>
                        <span class="font-weight-bold">Liczba planów: <c:out value="${countPlans}" default="0"/></span>
                    </div>
                </div>
            </div>
            <div class="m-4 p-4 border-dashed">
                <h2 class="dashboard-content-title">
                    <span>Ostatnio dodany plan:</span> <c:out value="${planName}"/>
                </h2>
                <table class="table">
                    <c:set var="day" scope="session" value="0"/>
                    <c:forEach items="${latestPlans}" var="plan">
                    <c:if test="${plan.dayName!=day}">
                    <c:set var="day" scope="session" value="${plan.dayName}"/>
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">${day}</th>
                        <th class="col-8"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    </c:if>
                    <tr class="d-flex">
                        <td class="col-2">${plan.mealName}</td>
                        <td class="col-8">${plan.recipeName}</td>
                        <td class="col-2">
                            <button type="button" class="btn btn-primary rounded-0">Szczegóły</button>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
<jsp:include page="/loginFooter.jsp"></jsp:include>
