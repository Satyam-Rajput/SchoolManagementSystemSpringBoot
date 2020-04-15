<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard- Teacher</title>
        <link href="../resources/static/css/styles.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
    </head>
       <body class="sb-nav-fixed bg-dark">
        <nav class="sb-topnav navbar navbar-expand navbar-dark sb-sidenav-dark">
            <a class="navbar-brand" href="../student/StudentPage">Student Dashboard</a><button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button
            ><!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
                <div class="input-group">
                    
                    <div class="input-group-append">
                       <h5 class="text-light">  <%out.println(request.getSession(false).getAttribute("user")); %></h5>
                    </div>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ml-auto ml-md-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                        <a class="dropdown-item" href="../student/changePasswordPage">Change Password</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="../user/logout">Logout</a>
                    </div>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="../student/teacherPage"
                                ><div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard</a
                            >
                           <div class="sb-sidenav-menu-heading">Actions</div>
                            
                              <a class="nav-link" href="../student/updateDetails"
                                ><div class="sb-nav-link-icon"><i class="fas fa-edit"></i></div>
                                Update Details</a
                            ><a class="nav-link" href="../student/viewDetails"
                                ><div class="sb-nav-link-icon"><i class="fa fa-bars" aria-hidden="true"></i></div>
                               View Details</a>
                       
                              <a class="nav-link" href="../student/viewMarks"
                                ><div class="sb-nav-link-icon"><i class="fa fa-bars" aria-hidden="true"></i></div>
                                View Marks</a>
                              
                            
                        </div>
                    </div>
                    <div class="sb-sidenav-footer sb-sidenav-dark">
                        <div class="small">Logged in as:</div>
                    <%out.println(request.getSession(false).getAttribute("user")); %>
                    ${usermsg}
                    ${error}
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                
                
                
                
                
                
                
                
                
                
                
             <div  align="center" style="border: 1px;margin-top:20px;margin-left:500px; border-radius: 30px;height: 600px; width: 450px"  class="sb-sidenav-dark text-white">
	
	<div class="container">
		<div >
				<h3 class="text-center text-light">Update Details Form</h3><br/>
			
				<div class="panel-body">
					<form:form id="addStudentForm" action="../student/updateStudentDetails" cssClass="form-horizontal"
						method="post" modelAttribute="student">
						
						<div class="row">
						<div class="col-sm-12" align="left"><input type="hidden" value="${student.id }" name="id" id="id"  hidden=""/></div>
						<div class="col-sm-6" align="left">
<p class="contact"><label for="firstName">First Name</label></p>
<input id="firstName" name="firstName" placeholder="Enter first name" value="${student.firstName }" readonly="readonly" required tabindex="1" type="text"></div>
<div class="col-sm-6" align="left">
<p class="contact"><label for="lastName">Last Name</label></p>
<input id="lastName" name="lastName" placeholder="Enter last name" required tabindex="2" readonly="readonly" value="${student.lastName }" type="text ">
</div></div><div class="row"><div class="col-sm-6" align="left">
<p class="contact"><label for="email">Email</label></p>
<input id="email" name="email" placeholder="Enter Email " required tabindex="5" type="email" value="${student.email }">

</div>
<div class="col-sm-6" align="left">
<p class="contact"><label for="password">Enter a password</label></p>
<input type="password" id="password" name="password" required tabindex="3" >
</div>
</div>





<div class="row"><div class="col-sm-6" align="left">
<p class="contact"><label for="fatherName">Father Name</label></p>
<input id="fatherName" name="fatherName" placeholder="Enter Father Name " value="${student.fatherName }" readonly="readonly" required tabindex="5" type="text">

</div>

<div class="col-sm-6" align="left">


<p class="contact"><label for="phone">Mobile phone</label></p>
<input id="phone" name="phoneNumber" placeholder="phone number" value="${student.phoneNumber }" required tabindex="6" type="tel"
pattern="[0-9]{10}"></div></div><div class="row"><div class="col-sm-6" align="left"> 
<p class="contact"><label for="houseNumber">house number</label></p>
<input id="houseNumber" name="houseNumber" pattern="[0-9]" placeholder="Enter houseNumber " value="${student.address.houseNumber }" required tabindex="7" type="number">
</div><div class="col-sm-6" align="left">
<p class="contact"><label for="street">Address</label></p>
<input id="streetName" name="streetName" placeholder="Street Address" value="${student.address.streetName }" required tabindex="8" type="text">
</div></div>
<div class="row"><div class="col-sm-6" align="left">
<p class="contact"><label for="city">City</label></p>
<select class="select-style city" name="city" tabindex="9">

            
            <option value="Bangalore" selected="selected">Bangalore</option>
            <option value="Chennai">Chennai</option>
            <option value="Delhi">Delhi</option>
             <option value="Kolkatta">Kolakatta</option>
              <option value="Mumbai">Mumbai</option>
            </select>
            </div>
          <div class="col-sm-6" align="left">

<p class="pincode"><label for="pincode">Pin Code</label></p>
<input id="pincode" name="pincode" placeholder="Enter Pin Code" required tabindex="12" type="number" value="${student.address.pincode }">
</div></div><br><div class="row"><div class="col-sm-12" align="center">
<input class="btn btn-outline-success my-2 my-sm-0 text-light" name="submit" onclick="return validate()" id="submit" tabindex="13" value="Update" type="submit"> 
</div></div></form:form>
				</div>
			</div>
		</div>
	</div>
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                </main>
                  <footer class="py-4 bg-dark mt-auto">
                    <div class="container-fluid">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Alpha School Management System 2020</div>
                           
                        </div>
                    </div>
                </footer>
            </div>
        </div>        
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../resources/static/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="../resources/static/assets/demo/chart-area-demo.js"></script>
        <script src="../resources/static/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
        <script src="../resources/static/assets/demo/datatables-demo.js"></script>
    </body>
</html>
