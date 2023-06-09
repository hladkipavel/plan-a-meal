<%--
  Created by IntelliJ IDEA.
  User: pavelgladky
  Date: 27/01/2023
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/loginHeader.jsp"></jsp:include>
<div class="m-4 p-3 width-medium ">
    <div class="dashboard-content border-dashed p-3 m-4">
        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
                <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
            </div>
            <div class="col d-flex justify-content-end mb-2 noPadding">
                <a href="/app/plan/list" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
            </div>
        </div>

        <div class="schedules-content">
            <div class="schedules-content-header">
                <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                    <div class="col-sm-10">
                        <p class="schedules-text">${plan.name}</p>
                    </div>
                </div>
                <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                    <div class="col-sm-10">
                        <p class="schedules-text">
                            ${plan.description}
                        </p>
                    </div>
                </div>
            </div>

            <table class="table">
                <c:set var="day" scope="session" value="0"/>
                <c:forEach items="${detailsPlan}" var="details">
                <c:if test="${details.dayName!=day}">
                    <c:set var="day" scope="session" value="${details.dayName}"/>
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">${day}</th>
                        <th class="col-7"></th>
                        <th class="col-1"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                </c:if>
                <tbody class="text-color-lighter">
                <tr class="d-flex">
                    <td class="col-2">${details.mealName}</td>
                    <td class="col-7">${details.recipeName}</td>
                    <td class="col-1 center">
                        <a href="#" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                    </td>
                    <td class="col-2 center">
                        <a href="app-details-schedules.html" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>
<jsp:include page="/loginFooter.jsp"></jsp:include>
