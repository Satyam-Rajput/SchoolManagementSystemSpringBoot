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
        <title>Dashboard Student</title>
        <link href="../resources/static/css/styles.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
    </head>
      
               <body class="sb-nav-fixed bg-light" background="<c:url value="/resources/static/images/student.jpg"/>"/>
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-success">
            <a class="navbar-brand text-light" href="../student/StudentPage"><img src="<c:url value="/resources/static/images/logo1.jpg"/>" alt="Logo" style="width:60px; "></a><button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button
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
                <nav class="sb-sidenav accordion bg-success" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading text-light">Core</div>
                            <a class="nav-link text-light" href="../student/studentPage"
                                ><div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard</a
                            >
                           <div class="sb-sidenav-menu-heading text-light">Actions</div>
                            
                              <a class="nav-link text-light" href="../student/updateDetails"
                                ><div class="sb-nav-link-icon"><i class="fas fa-edit"></i></div>
                                Update Personal Details</a
                            ><a class="nav-link text-light" href="../student/viewDetails"
                                ><div class="sb-nav-link-icon"><i class="fa fa-bars" aria-hidden="true"></i></div>
                               View Details</a>
                       
                              <a class="nav-link text-light" href="../student/viewMarks"
                                ><div class="sb-nav-link-icon"><i class="fa fa-bars" aria-hidden="true"></i></div>
                                View Marks</a>
                              
                            
                        </div>
                    </div>
                  
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                
               
                
                
                <div class="card " style="width:80%;margin-left:10%;margin-top:1%;">
  <div class="card-header bg-success text-light text-center font-weight-bold">
   ${student.firstName}&nbsp;${student.lastName}'s Marks
  </div>
  <br/>
  <div class="card-body">
  
							
								
								
								
							
  	
  
  <br/>
<div class="row text-left">
<div class="col-md-3"></div>
<div class="col-md-3 text-dark font-weight-bold">Class :</div>
<div class="col-md-3 text-dark">${student.studentClass}</div>
<div class="col-md-3"></div></div><br/>


<div class="row">
<div
						class="col-md-12 text-center "><div style="color: red;font-size: 15px; "><label id="warningLabel" name="warningLabel">${error} ${usermsg }</label></div></div>
				</div>	
<c:if test="${marks!=null }">
<div class="row text-left">
<div class="col-md-3"></div>
<div class="col-md-3 text-dark font-weight-bold">Attendence :</div>
<div class="col-md-3 text-dark">${marks.attendence}%</div>
<div class="col-md-3"></div></div><br/>	
<div class="row text-left">
<div class="col-md-3"></div>
<div class="col-md-3 text-dark font-weight-bold">Marks in English :</div>
<div class="col-md-3 text-dark">${marks.marksInEnglish}</div>
<div class="col-md-3"></div></div><br/>


<div class="row text-left">
<div class="col-md-3"></div>
<div class="col-md-3 text-dark font-weight-bold">Marks in Hindi :</div>
<div class="col-md-3 text-dark">${marks.marksInHindi}</div>
<div class="col-md-3"></div></div><br/>


<div class="row text-left">
<div class="col-md-3"></div>
<div class="col-md-3 text-dark font-weight-bold">Marks in Math :</div>
<div class="col-md-3 text-dark">${marks.marksInMath}</div>
<div class="col-md-3"></div></div><br/>

<div class="row text-left">
<div class="col-md-3"></div>
<div class="col-md-3 text-dark font-weight-bold">Marks in Science :</div>
<div class="col-md-3 text-dark">${marks.marksInScience}</div>
<div class="col-md-3"></div></div><br/>

<div class="row text-left">
<div class="col-md-3"></div>
<div class="col-md-3 text-dark font-weight-bold">Marks in Social Science :</div>
<div class="col-md-3 text-dark">${marks.marksInSocialScience}</div>
<div class="col-md-3"></div></div><br/>


<div class="row text-left">
<div class="col-md-3"></div>
<div class="col-md-3 text-dark font-weight-bold">Percentage :</div>
<div class="col-md-3 text-dark">${marks.percentage}%</div>
<div class="col-md-3"></div></div><br/>

</c:if>								
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
