<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<link href="<c:url value="/resources/static/css/bootstrap.min.css" />"
	rel="stylesheet">
	<link href="<c:url value="/resources/static/css/styles.css" />"
 rel="stylesheet">
<script src="<c:url value="/resources/static/js/jquery-3.3.0.min.js" />"></script>
<script src="<c:url value="/resources/static/js/bootstrap.min.js" />"></script>

</head>
<body >

<nav class="navbar navbar-expand-lg navbar-light sb-nav-fixed bg-success">
 <a class="navbar-brand text-light" style="font-size:20px " href="#">Alpha School</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link text-light" href="../">Home <span class="sr-only">(current)</span></a>
      </li>
        
      
       <li class="nav-item">
        <a class="nav-link text-light" href="#">About Us</a>
      </li>
       <li class="nav-item">
        <a class="nav-link text-light" href="#">Contact Us</a>
      </li>
     
     
    </ul>
    <form class="form-inline my-2 my-lg-0">
    <div>
    
    <a href=" ../user/loginForm"  class="btn btn-outline-secondary my-2 my-sm-0  text-light"> Login </a>

    </div>
      </form>
  </div>
</nav>
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="d-block w-100" src="/resources/static/images/college8.jpg" alt="First slide">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="/resources/static/images/school4.jpg" alt="Second slide">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="/resources/static/images/school5.jpg" alt="Third slide">
    </div>
    
    <div class="carousel-item">
      <img class="d-block w-100" src="/resources/static/images/school6.jpg" alt="Third slide">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</body>
</html>
