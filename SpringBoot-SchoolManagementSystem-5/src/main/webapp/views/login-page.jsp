<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link href="<c:url value="/resources/static/css/bootstrap.min.css" />"
	rel="stylesheet">
	<link href="../resources/static/css/styles.css" rel="stylesheet" />
<script src="<c:url value="/resources/static/js/jquery-3.3.0.min.js" />"></script>
<script src="<c:url value="/resources/static/js/bootstrap.min.js" />"></script>
<script>
function validate()
{
	
	var email=document.getElementById("email").value;
	var pass=document.getElementById("password").value;
	if(email==null || email=="")
		{
		document.getElementById("warningLabel").innerHTML='Please Enter email'
		return false;
		}
	else if(pass==''||pass==null)
		{
		document.getElementById("warningLabel").innerHTML='Please Enter password'
		return false;
		}
	else
		{
		document.getElementById("warningLabel").innerHTML=' '
		return true;}
	
	}

</script>
</head>
<body style="background-repeat: no-repeat;" background="<c:url value="/resources/static/images/college5.jpg"/>"/>
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
    
    <a href=" ../user/loginForm"  class="btn btn-outline-success my-2 my-sm-0  text-light"> Login </a>

    </div>
      </form>
  </div>
</nav>
<!--  
<div align="center" style="border: 1px;margin-top:200px;margin-left:550px; border-radius: 20px;height: 300px; width: 400px"  class="bg-secondary text-white">
	<div class="container">
		<div class="col-md-offset-2 col-md-7">
			<h2 style="padding-top: 30px"   class="text-center text-light">Login</h2>
			<div class="panel panel-info">
			<br/>
				
				<div class="panel-body">
					<form:form action="../user/login" cssClass="form-horizontal"
						method="post" modelAttribute="user">

						
						<div class="row text-right">
						
						<div class="col-sm-3 text-center">
						<p class="contact"><label for="email">Email</label></p>
</div>
<div class="col-sm-2"></div>
&nbsp;<div class="col-sm-6 text-left"><input id="email" name="email" placeholder="Enter Email" required tabindex="1" type="email"></div></div>
<div class="row">
<div class="col-sm-12 text-center"><div style="color: red;font-size: 15px; ">${error}</div></div></div>

						
						<div class="row"><div class="col-sm-4 " align="left">
						<p class="contact"><label for="password">Password</label></p>
</div><div class="col-sm-1"></div><div class="col-sm-6 "><input id="password" name="password" placeholder="Enter password" required tabindex="2" type="password"></div></div>
						
						<div class="row"><div class="col-sm-12 float-center"><input onclick="return validate()" type="submit" value="Login" class="btn btn-outline-success my-2 my-sm-0  text-light">
						</div></div>
						
						
						
						
					</form:form>
				</div>	
				</div>
			</div>
		</div>
	</div>-->
	
	<div class="card bg-success" style="width: 30%;margin-left: 35% ; margin-top: 16%;border-radius: 10px">
  <div class="card-header text-center text-light">
    LOGIN
  </div>
  <div class="card-body bg-light" style="border-radius: 15px">
<form:form action="../user/login" 
						method="post" modelAttribute="user">

						
						<div class="row ">
						
					&nbsp;&nbsp;	<div class="col-sm-5 text-center">
						<p class="text-dark"><label for="email">Email</label></p>
</div>

<div class="col-sm-6 text-left">
<!--  <input id="email" name="email" placeholder="Enter Email" required tabindex="1" type="email">-->

<div class="input-group mb-6 ">
  <input type="email" required="required" class="form-control" id="email" name="email" placeholder="Enter email" tabindex="1" aria-label="email" aria-describedby="basic-addon2">
  
</div>
</div></div>


						<div class="row"><div class="col-sm-1  text-center" ></div>
					<div class="col-sm-4  text-center" >
						<p class="text-dark"><label for="password">Password</label></p>
</div>

&nbsp;&nbsp;<div class="col-sm-6 ">
<!--  <input id="password" name="password" placeholder="Enter password" required tabindex="2" type="password">-->

<div class="input-group mb-3">
  <input type="password" class="form-control" required="required"  id="password" name="password" tabindex="2" placeholder="Enter password" aria-label="password" aria-describedby="basic-addon2">
  
</div>

</div></div>
				
				<div class="row"><div class="col-sm-12 text-danger text-center" style="font-size:15px;font-style: italic; "><label id="warningLabel" name="warningLabel">${error} ${usermsg}</label></div></div>
						
						<div class="row"><div class="col-sm-12 text-center text-dark"><input onclick="return validate()" type="submit" tabindex="3" value="Login" class="btn btn-outline-success my-2 my-sm-0  text-dark">
						</div></div>
						
						
						
						
					</form:form>
  </div>
</div>
	
</body>
</html>