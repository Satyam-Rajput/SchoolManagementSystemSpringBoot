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
        <title>Dashboard- Admin</title>
        <link href="../resources/static/css/styles.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
   
   <script>
   
   function checkPassword()
   {
	  
	   var newpass=document.getElementById("newPassword").value
	   var conpass=document.getElementById("confirmPassword").value;
	   
	   if(newpass!=conpass)
		   {
		  
		  document.getElementById("warningLabel").innerHTML="Password doesn't match";
		   }
	   else
		   {
		   document.getElementById("warningLabel").innerHTML=" ";
		   }
   }
   
   </script>
    </head>
    <body class="sb-nav-fixed bg-dark">
        <nav class="sb-topnav navbar navbar-expand navbar-dark sb-sidenav-dark">
            <a class="navbar-brand" href="../employee/teacherPage">Teacher Dashboard</a><button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button
            ><!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
                <div class="input-group">
                    
                    <div class="input-group-append">
                       <h5 class="text-light"> <%out.println(request.getSession(false).getAttribute("user")); %></h5>
                    </div>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ml-auto ml-md-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                        <a class="dropdown-item" href="../employee/changePasswordPage">Change Password</a>
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
                            <a class="nav-link" href="../employee/teacherPage"
                                ><div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard</a
                            >
                           <div class="sb-sidenav-menu-heading">Teacher</div>
                            
                              <a class="nav-link" href="../employee/updateDetails"
                                ><div class="sb-nav-link-icon"><i class="fas fa-edit"></i></div>
                                Update Details</a
                            ><a class="nav-link" href="../employee/viewDetails"
                                ><div class="sb-nav-link-icon"><i class="fa fa-bars" aria-hidden="true"></i></div>
                               veiw Details</a>
                             
                           
                            <div class="sb-sidenav-menu-heading">Student</div>
                            
                              <a class="nav-link" href="../employee/getStudents"
                                ><div class="sb-nav-link-icon"><i class="fa fa-bars" aria-hidden="true"></i></div>
                                Display Student Details</a>
                                <a class="nav-link" href="../employee/findStudent"
                                ><div class="sb-nav-link-icon"><i class="fa fa-search" aria-hidden="true"></i></div>
                                Find Student Details</a>
                                
                                <a class="nav-link" href="../employee/uploadMarksPage"
                                ><div class="sb-nav-link-icon"><i class="fa fa-upload" aria-hidden="true"></i></div>
                               Upload Student Marks</a>
                               
                                <a class="nav-link" href="../employee/viewMarksPage"
                                ><div class="sb-nav-link-icon"><i class="fa fa-list" aria-hidden="true"></i></div>
                              View Student Marks</a>
                               
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                    v
                         ${usermsg }
                           ${error }
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                <div align="center" style="border: 1px;margin-top:150px;margin-left:500px; border-radius: 20px;height: 350px; width: 500px"  class="sb-sidenav-dark text-white">
	<div class="container">
		<div >
			<h4 style="padding-top: 30px"   class="text-center text-light">Change Password</h4>
			<br/>
			<div class="panel panel-info">
				
				<div class="panel-body">
					<form:form action="../employee/changePassword" cssClass="form-horizontal"
						method="post" >


               <div class="row">
						<div class="col-sm-12" align="left"></div>
						<div class="col-sm-6" align="left">
<p class="contact"><label for="password">Old Password</label></p></div><div class="col-sm-6" align="left">
<input type="password" id="password" class="form-control"  name="password" required="required"/></div>
</div>



   <div class="row">
						
						<div class="col-sm-6" align="left">
<p class="contact"><label for="password">New Password</label></p></div><div class="col-sm-6" align="left">
<input type="password" id="newPassword" class="form-control"  name="newPassword" required="required"/></div>
</div>




   <div class="row">
						
						<div class="col-sm-6" align="left">
<p class="contact"><label for="password">Confirm Password</label></p></div><div class="col-sm-6" align="left">
<input type="password" id="confirmPassword" class="form-control"  name="confirmPassword" onblur="checkPassword()" required="required"/></div>
</div>


   <div class="row">
   <div class="col-sm-6" align="left"></div>
						
						<div class="col-sm-6" align="left"><label id="warningLabel" style="color:red;"></label></div></div>
						
   <div class="row">
   <div class="col-sm-12" align="center"><input onclick="return validate()" type="submit" value="Change Password" class="btn btn-outline-success my-2 my-sm-0  text-light">
							</div>
						
						</div>
					
						
						
					</form:form>
				</div>	
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
