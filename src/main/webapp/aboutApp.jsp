<%--
  Created by IntelliJ IDEA.
  User: pavelgladky
  Date: 24/01/2023
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"></jsp:include>
<section class="padding-large bg-light">
  <div id="carouselExampleControls" class="carousel slide main-slider" data-ride="carousel">
    <div class="carousel-inner container">
      <div class="carousel-item active">
        <div class="container w-75 d-flex">
          <div class="carousel-caption d-block">
            <h1>Lorem ipsum dolor sit amet</h1>
            <h3> consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
              magna aliqua.</h3>
          </div>
        </div>
      </div>
      <div class="carousel-item">
        <div class="container w-75 d-flex">
          <div class="carousel-caption d-block">
            <h1>Torem ipsum dolor sit amet</h1>
            <h3> consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
              magna aliqua.</h3>
          </div>
        </div>
      </div>
      <div class="carousel-item">
        <div class="container w-75 d-flex">
          <div class="carousel-caption d-block">
            <h1>Borem ipsum dolor sit amet</h1>
            <h3> consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
              magna aliqua.</h3>
          </div>
        </div>
      </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</section>

<section class="padding-medium story bg-light" id="about">
  <div class="container d-flex justify-content-center align-items-center">
    <div class="row">
      <div class="col-4 mr-4">
        <div class="div-img">
        </div>
      </div>

      <div class="col-7 ml-4">
        <h1 class="pb-1">Lorem ipsum dolor sit amet</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem. Aliquam erat
          volutpat. Donec placerat nisl magna, et faucibus arcu condimentum sed. Lorem ipsum dolor sit
          amet, consectetur adipiscing elit. Aliquam at porttitor sem. Aliquam erat volutpat. Donec
          placerat nisl magna, et faucibus arcu condimentum sed.
        </p>
      </div>
    </div>
  </div>
</section>
<jsp:include page="footer.jsp"></jsp:include>