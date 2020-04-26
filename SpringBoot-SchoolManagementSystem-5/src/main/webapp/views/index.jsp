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
<style>
.footer {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 2.5rem;            /* Footer height */
}
</style>

</head>
<body background="<c:url value="/resources/static/images/2.jpg"/>"/>

<nav class="navbar navbar-expand-lg navbar-light sb-nav-fixed bg-success">
 <a class="navbar-brand text-light" style="font-size:20px " href="#"><img src="<c:url value="/resources/static/images/logo1.jpg"/>" alt="Logo" style="width:60px; "></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link text-light" href="../">Home <span class="sr-only">(current)</span></a>
      </li>
        
      
       <li class="nav-item">
        <a class="nav-link text-light" href="../admin/aboutUs">About Us</a>
      </li>
       <li class="nav-item">
        <a class="nav-link text-light" href="../admin/contactUs">Contact Us</a>
      </li>
     
     
    </ul>
    <form class="form-inline my-2 my-lg-0">
    <div>
    
    <a href=" ../user/loginForm"  class="btn btn-outline-success my-2 my-sm-0  text-light"> Login </a>

    </div>
      </form>
  </div>
</nav>
</body>
 <footer class="bg-dark  footer" style="width:100%;height:8%">
                    <div class="container-fluid">
                        <div class="d-flex align-items-center text-center justify-content-between small">
                            <div class="text-muted " style="align: center;">Copyright &copy; Alpha School Management System 2020</div>
                           
                        </div>
                    </div>
                </footer>
</html>
