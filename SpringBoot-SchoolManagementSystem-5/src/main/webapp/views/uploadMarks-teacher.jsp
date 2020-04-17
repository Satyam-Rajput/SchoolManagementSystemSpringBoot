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
        <body class="sb-nav-fixed bg-light">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-success">
            <a class="navbar-brand text-light" href="../employee/teacherPage">Teacher Dashboard</a><button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button
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
                <nav class="sb-sidenav accordion bg-success" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading text-light">Core</div>
                            <a class="nav-link text-light" href="../employee/teacherPage"
                                ><div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard</a
                            >
                           <div class="sb-sidenav-menu-heading text-light">Teacher</div>
                            
                              <a class="nav-link text-light" href="../employee/updateDetails"
                                ><div class="sb-nav-link-icon"><i class="fas fa-edit"></i></div>
                                Update Details</a
                            ><a class="nav-link text-light" href="../employee/viewDetails"
                                ><div class="sb-nav-link-icon"><i class="fa fa-bars" aria-hidden="true"></i></div>
                               veiw Details</a>
                             
                           
                            <div class="sb-sidenav-menu-heading text-light">Student</div>
                            
                              <a class="nav-link text-light" href="../employee/getStudents"
                                ><div class="sb-nav-link-icon"><i class="fa fa-bars" aria-hidden="true"></i></div>
                                Display Student Details</a>
                                <a class="nav-link text-light" href="../employee/findStudent"
                                ><div class="sb-nav-link-icon"><i class="fa fa-search" aria-hidden="true"></i></div>
                                Find Student Details</a>
                                
                                <a class="nav-link text-light" href="../employee/uploadMarksPage"
                                ><div class="sb-nav-link-icon"><i class="fa fa-upload" aria-hidden="true"></i></div>
                               Upload Student Marks</a>
                               
                                <a class="nav-link text-light" href="../employee/viewMarksPage"
                                ><div class="sb-nav-link-icon"><i class="fa fa-list" aria-hidden="true"></i></div>
                              View Student Marks</a>
                               
                        </div>
                    </div>
               <!--      <div class="sb-sidenav-footer sb-sidenav-dark">
                        <div class="small">Logged in as:</div>
                     <p> 
                        </p> 
                    </div>--> 
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                
                
                
              <!--   
                
                 <div  align="center" style="border: 1px;margin-top:30px;margin-left:500px; border-radius: 30px;height: 500px; width: 450px"  class="sb-sidenav-dark text-white">
	
	<div class="container">
		<div >
				<h3 class="text-center text-light">Upload Marks Form</h3><br/>
			
				<div class="panel-body">
					<form:form id="registrationform" action="../employee/uploadMarks" cssClass="form-horizontal"
						method="post" modelAttribute="marks">
						<br/><div class="row">
						<div class="col-sm-12">
				</div>
				
				
				<div class="col-sm-12">
				</div>
				
			</div>
				
			<div class="row">	<div class="col-sm-6" align="left">
<p class="contact"><label for="studentId">Enter Student Id</label></p></div><div class="col-sm-6">
<input type="number" id="studentId" name="studentId"  required="required" /></div></div>

			
				<div class="row">	<div class="col-sm-6" align="left">
<p class="contact"><label for="marksInEnglish">English</label></p></div><div class="col-sm-6">
<input id="marksInEnglish" name="marksInEnglish" placeholder="Enter marks" required tabindex="1" type="number"></div></div>

	
				<div class="row">	<div class="col-sm-6" align="left">
<p class="contact"><label for="marksInHindi">Hindi</label></p></div><div class="col-sm-6">
<input id="marksInHindi" name="marksInHindi" placeholder="Enter marks" required tabindex="1" type="number"></div></div>

	
				<div class="row">	<div class="col-sm-6" align="left">
<p class="contact"><label for="marksInMath">Mathematics</label></p></div><div class="col-sm-6">
<input id="marksInMath" name="marksInMath" placeholder="Enter marks" required tabindex="1" type="number"></div></div>

	
				<div class="row">	<div class="col-sm-6" align="left">
<p class="contact"><label for="marksInScience">Science</label></p></div><div class="col-sm-6">
<input id="marksInScience" name="marksInScience" placeholder="Enter marks" required tabindex="1" type="number"></div></div>

	
				<div class="row">	<div class="col-sm-6" align="left">
<p class="contact"><label for="marksInSocialScience">Social Science</label></p></div><div class="col-sm-6">
<input id="marksInSocialScience" name="marksInSocialScience" placeholder="Enter marks" required tabindex="1" type="number"></div></div>



            
            
            
            
            <div class="row"><div class="col-sm-12" align="center">
<input class="btn btn-outline-success my-2 my-sm-0 text-light" name="submit" onclick="return validate()" id="submit" tabindex="13" value="Upload" type="submit"> 
</div></div></form:form>
				</div>
			</div>
		</div>
	</div>
              -->
                <div class="card bg-success" style="width: 50%;margin-left: 25% ; margin-top: 5%;border-radius: 10px">
  <div class="card-header text-center text-light">
    Upload Marks
  </div>
  <div class="card-body bg-light" style="border-radius: 15px">
<form:form action="../employee/uploadMarks" 
						method="post" modelAttribute="marks">

						
						<div class="row ">
					<div class="col-sm-1 text-center"></div>
					<div class="col-sm-4 text-left">
						<p class="text-dark"><label for="Id">Roll No</label></p>
</div>

<div class="col-sm-6 text-left">

<div class="input-group mb-6 ">
  <input type="number" pattern="[0-9]" tabindex="1" required="required" class="form-control" id="studentId" name="studentId" placeholder="Enter Roll No" aria-label="id" aria-describedby="basic-addon2">
  
</div>
</div></div>






	<div class="row ">
					<div class="col-sm-1 text-center"></div>
					<div class="col-sm-4 text-left">
						<p class="text-dark"><label for="Id">Marks In English</label></p>
</div>

<div class="col-sm-6 text-left">

<div class="input-group mb-6 ">
  <input type="number" pattern="[0-9]" required="required" tabindex="2" class="form-control" id="marksInEnglish" name="marksInEnglish" placeholder="Enter marks" aria-label="id" aria-describedby="basic-addon2">
  
</div>
</div></div>











	<div class="row ">
					<div class="col-sm-1 text-center"></div>
					<div class="col-sm-4 text-left">
						<p class="text-dark"><label for="Id">Marks In Hindi</label></p>
</div>

<div class="col-sm-6 text-left">

<div class="input-group mb-6 ">
  <input type="number" pattern="[0-9]" required="required" tabindex="3" class="form-control" id="marksInHindi" name="marksInHindi" placeholder="Enter marks" aria-label="id" aria-describedby="basic-addon2">
  
</div>
</div></div>














	<div class="row ">
						<div class="col-sm-1 text-center"></div>
					<div class="col-sm-4 text-left">
						<p class="text-dark"><label for="Id">Marks In Mathematics</label></p>
</div>

<div class="col-sm-6 text-left">

<div class="input-group mb-6 ">
  <input type="number" pattern="[0-9]" required="required" tabindex="4" class="form-control" id="marksInMath" name="marksInMath" placeholder="Enter marks" aria-label="id" aria-describedby="basic-addon2">
  
</div>
</div></div>













<div class="row ">
								<div class="col-sm-1 text-center"></div>
					<div class="col-sm-4 text-left">
						<p class="text-dark"><label for="Id">Marks In Science</label></p>
</div>

<div class="col-sm-6 text-left">

<div class="input-group mb-6 ">
  <input type="number" pattern="[0-9]" required="required" tabindex="5" class="form-control" id="marksInScience" name="marksInScience" placeholder="Enter marks" aria-label="id" aria-describedby="basic-addon2">
  
</div>
</div></div>

















<div class="row ">
								<div class="col-sm-1 text-center"></div>
					<div class="col-sm-4 text-left">
						<p class="text-dark"><label for="Id">Marks In Social Science</label></p>
</div>

<div class="col-sm-6 text-left">

<div class="input-group mb-6 ">
  <input type="number" pattern="[0-9]" required="required" tabindex="6" class="form-control" id="marksInSocialScience" name="marksInSocialScience" placeholder="Enter marks" aria-label="id" aria-describedby="basic-addon2">
  
</div>
</div></div>








	
<div class="row">
<div class="col-sm-12 text-center"><div style="color: red;font-size: 15px; ">${error}</div></div></div>

								
						<div class="row"><div class="col-sm-12 text-center text-dark"><input tabindex="7" onclick="return validate()" type="submit" value="Upload" class="btn btn-outline-success my-2 my-sm-0  text-dark">
						</div></div>
						
						
						
						
					</form:form>
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
