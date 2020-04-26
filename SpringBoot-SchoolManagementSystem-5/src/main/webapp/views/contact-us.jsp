<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Contact Us</title>
<link href="../resources/static/css/styles.css" rel="stylesheet" />
<link
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css"
	rel="stylesheet" crossorigin="anonymous" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
	crossorigin="anonymous"></script>
	<style>
.footer {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 2.5rem;            /* Footer height */
}
</style>
</head>
<body style="background-repeat: no-repeat;" background="<c:url value="/resources/static/images/contact.jpg"/>"/>
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
	<div id="layoutSidenav">
		
		<div id="layoutSidenav_content">
			<main>
				<div class="card bg-success"
					style="width: 40%; margin-left:29%; margin-top: 8%; border-radius: 10px">
					<div class="card-header text-center text-light font-weight-bolder">Contact
						Us</div>
					<div class="card-body bg-light" style="border-radius: 10px;">
						<form:form  action="../user/contactDetails" method="post">
							<div class="row">
								<div class="col-sm-12" style="align-content: center;">
									<input type="hidden"  name="id" id="id" hidden="" />
								</div>
							</div>
							
							
							<div class="row " style= " align-content: center;">
								<div class="col-sm-4 text-left" style="margin-left: 2%; align-content: center;">
									<p class="text-dark">
										<label for="Name">Name :</label>
									</p>
								</div>
								<div class="col-sm-4 text-left">
									<div class="input-group mb-6 ">
										<input type="text" class="form-control" id="firstName"
											name="firstName" placeholder="Enter First Name"
											aria-label="firstName" aria-describedby="basic-addon2"
											required="required">
									</div>
								</div>
							</div>
							
					
							
							<div class="row">
								<div class="col-sm-4 text-left" style="margin-left: 2%">
									<p class="text-dark">
										<label for="email">Email :</label>
									</p>
								</div>
								<div class="col-sm-4 text-left">
									<div class="input-group mb-6 ">
										<input class="form-control" id="email" name="email"
											placeholder="Enter Email" type="email"
											aria-label="email" aria-describedby="basic-addon2"
											required="required">
									</div>
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-sm-4 text-left" style="margin-left: 2%"><p class="text-dark">
										<label for="message">Message :</label>
									</p></div>

								<div class="col-sm-6 text-left">

									<div class="input-group mb-6 ">
									<textarea rows="4" cols="50" id="message" name="message" 
											placeholder="Enter Message" required class="form-control"></textarea>
									</div>
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-sm-12 text-right">
									<div style="color: red; font-size: 12px; margin-right: 15%">
										<label id="warningLabel" name="warningLabel"></label>
									</div>
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-sm-4 text-left" style="margin-left: 2%">
									<p class="text-dark">
										<label for="phoneNumber">Phone Number :</label>
									</p>
								</div>

								<div class="col-sm-4 text-left">

									<div class="input-group mb-6 ">
										<input id="phone" name="phoneNumber"
											placeholder="phone number" required tabindex="9" type="tel"
											pattern="[0-9]{10}" class="form-control"
											aria-label="lastName" aria-describedby="basic-addon2">

									</div>
								</div>
							</div>


							
								
							<div class="row">
								<div class="col-sm-12 text-center">
									<div style="color: red; font-size: 15px;">
										<label>${response} ${usermsg }</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 text-center text-dark" >
									<input onclick="return validate()" id="regButton" type="submit"
										tabindex="17" value="Submit"
										class="btn btn-outline-success my-2 my-sm-0  text-dark c ">
								</div>
							</div>
							
						</form:form>
					</div>
				</div>
			</main>
			
		</div>
	</div>
	 <footer class="bg-dark  footer" style="width:100%;height:8%">
                    <div class="container-fluid">
                        <div class="d-flex align-items-center text-center justify-content-between small">
                            <div class="text-muted " style="align: center;">Copyright &copy; Alpha School Management System 2020</div>
                           
                        </div>
                    </div>
                </footer>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="../resources/static/js/scripts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script src="../resources/static/assets/demo/chart-area-demo.js"></script>
	<script src="../resources/static/assets/demo/chart-bar-demo.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"
		crossorigin="anonymous"></script>
	<script src="../resources/static/assets/demo/datatables-demo.js"></script>
</body>
</html>
