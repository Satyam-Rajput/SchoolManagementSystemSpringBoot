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
		alert('Please Enter email')
		return false;
		}
	else if(pass==''||pass==null)
		{
		alert('please Enter password')
		return false;
		}
	else
		{return true;}
	
	}

</script>
</head>
<body background="<c:url value="/resources/static/images/college5.jpg"/>"/>
<nav class="navbar navbar-expand-lg navbar-light sb-nav-fixed sb-sidenav-dark">
 <a class="navbar-brand text-light" style="font-size:20px " href="#">Alpha School Management System</a>
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
    <a  href=" ../user/loginForm"  class="btn btn-outline-success my-2 my-sm-0  text-light"> Login </a>

    </div>
      </form>
  </div>
</nav>
<div align="center" style="border: 1px;margin-top:200px;margin-left:550px; border-radius: 20px;height: 300px; width: 400px"  class="sb-sidenav-dark text-white">
	<div class="container">
		<div class="col-md-offset-2 col-md-7">
			<h2 style="padding-top: 30px"   class="text-center text-light">Login</h2>
			<div class="panel panel-info">
				
				<div class="panel-body">
					<form:form action="../user/login" cssClass="form-horizontal"
						method="post" modelAttribute="user">

						
						<div align="center" class="form-group">
							<label for="email" class="col-md-10 control-label">
							<div align="left" style="padding-right: 20px" class="text-light">Email</div></label>
							<div class="col-md-12">
								<input type="email" class="form-control" id="email" name="email" required="required"/>
							</div><div style="color: red;font-size: 15px; ">${error}</div>
						</div>
						
						<div align="center" class="form-group">
							<label   for="password" class="col-md-10 control-label">
							<div align="left" style="padding-right: 20px"  class="text-light">Password</div></label>
							<div class="col-md-12">
							<input type="password" id="password" class="form-control"  name="password" required="required"/>
							</div>
						</div>
						<div class="form-group">
							<!-- Button -->
							<div class="col-md-offset-3 col-md-9">
								
								<input onclick="return validate()" type="submit" value="Login" class="btn btn-outline-success my-2 my-sm-0  text-light">
							</div>
						</div>

					</form:form>
				</div>	
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>