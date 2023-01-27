<%--
  Created by IntelliJ IDEA.
  User: lukaszskoniecki
  Date: 26.01.2023
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>>
<jsp:include page="/loginHeader.jsp"></jsp:include>

        <div class="m-4 p-3 width-medium text-color-darker">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <!-- fix action, method -->
                <!-- add name attribute for all inputs -->
                <form action="/app/recipe/add" method="post">
                    <div class="mt-4 ml-4 mr-4">
                        <div class="row border-bottom border-3">
                            <div class="col"><h3 class="color-header text-uppercase">Nowy przepis</h3></div>
                            <div class="col d-flex justify-content-end mb-2">
                                <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
                            </div>
                        </div>

                        <table class="table borderless">
                            <tbody>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Nazwa Przepisu</th>
                                <td class="col-7">

                                    <input name="name" class="w-100 p-1" value="">
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Opis przepisu</th>
                                <td class="col-7"><textarea class="w-100 p-1" name="description" rows="5"></textarea></td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                                <td class="col-3">
                                    <input class="p-1" type="number" value="" name="preparationTime">
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <div class="row d-flex">
                            <div class="col-5 border-bottom border-3"><h3 class="text-uppercase" >Sposób
                                przygotowania</h3></div>
                            <div class="col-2" ></div>
                            <div class="col-5 border-bottom border-3"><h3 class="text-uppercase" >Składniki</h3></div>
                        </div>
                        <div class="row d-flex">
                            <div class="col-5 p-4">
                                <textarea class="w-100 p-1" rows="10" name="preparation"></textarea>
                            </div>
                            <div class="col-2"></div>

                            <div class="col-5 p-4">
                                <textarea class="w-100 p-1" rows="10" name="ingredients"></textarea>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
<jsp:include page="/loginFooter.jsp"></jsp:include>
