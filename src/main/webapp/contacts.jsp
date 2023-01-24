<%--
  Created by IntelliJ IDEA.
  User: pavelgladky
  Date: 24/01/2023
  Time: 15:46
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


<section class="last-info-section padding-small" id="contact">
  <div class="container">
    <div class="row">
      <div class="col">
        <h3 class="mb-4">Lorem ipsum dolor</h3>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem.  Aliquam erat volutpat. Donec placerat nisl magna.</p>
      </div>
      <div class="col pl-4 ml-4">
        <h3 class="mb-4">Lorem ipsum dolor</h3>
        <ul class="container">
          <li>consectetur adipiscing elit</li>
          <li>sed do eiusmod tempor</li>
          <li>incididunt ut labore</li>
          <li>et dolore magna aliqua</li>
        </ul>
      </div>
      <div class="col">
        <h3 class="mb-4">Lorem ipsum dolor</h3>
        <div class="input-group mb-3">
          <input type="text" class="form-control border-0 rounded-0" placeholder=""
                 aria-label="Recipient's username" aria-describedby="basic-addon2">
          <div class="input-group-append">
            <button class="input-group-text btn-color border-0 rounded-0" type="submit" id="basic-addon2"><a
                    href="index.html">Lorem</a></button>
          </div>
        </div>
        <div class="container d-flex-row">
          <a href="#">
            <i class="fab fa-facebook-square mr-4 icon-social"></i>
          </a>
          <a href="#">
            <i class="fab fa-twitter-square mr-4 icon-social"></i>

          </a>
          <a href="#">
            <i class="fab fa-instagram icon-social"></i>
          </a>
        </div>
      </div>
    </div>
  </div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
