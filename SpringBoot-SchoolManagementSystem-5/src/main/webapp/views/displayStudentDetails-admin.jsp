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
    </head>
        <body class="sb-nav-fixed bg-white">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-success">
            <a class="navbar-brand" href="../admin/adminPage">Admin Dashboard</a><button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button
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
                        <a class="dropdown-item" href="../admin/changePasswordPage">Change Password</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="../user/logout">Logout</a>
                    </div>
                </li>
                ${usermsg }
                ${error }
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion bg-success" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading text-light">Core</div>
                            <a class="nav-link text-light" href="../admin/adminPage"
                                ><div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard</a
                            >
                           <div class="sb-sidenav-menu-heading text-light">Teacher</div>
                            
                              <a class="nav-link text-light" href="../admin/newTeacher"
                                ><div class="sb-nav-link-icon"><i class="fa fa-plus" aria-hidden="true"></i></div>
                                Add Teachers</a
                            ><a class="nav-link text-light" href="../admin/getEmployees"
                                ><div class="sb-nav-link-icon"><i class="fa fa-bars" aria-hidden="true"></i></div>
                                Display Teacher Details</a>
                                <a class="nav-link text-light" href="../admin/findTeacher"
                                ><div class="sb-nav-link-icon"><i class="fa fa-search" aria-hidden="true"></i></div>
                                Find Teacher Details</a>
                           
                            <div class="sb-sidenav-menu-heading text-light">Student</div>
                            
                              <a class="nav-link text-light" href="../admin/newStudent"
                                ><div class="sb-nav-link-icon"><i class="fa fa-plus" aria-hidden="true"></i></div>
                                Add Students</a
                            ><a class="nav-link text-light" href="../admin/getStudents"
                                ><div class="sb-nav-link-icon"><i class="fa fa-bars" aria-hidden="true"></i></div>
                                Display Student Details</a>
                                <a class="nav-link text-light" href="../admin/findStudent"
                                ><div class="sb-nav-link-icon"><i class="fa fa-search" aria-hidden="true"></i></div>
                                Find Student Details</a>
                            
                        </div>
                    </div>
            <!--         <div class=" py-2 sb-sidenav-footer bg-dark">
                        <div class="small text-light">Logged in as:</div>
                  <p class="text-light">  <%out.println(request.getSession(false).getAttribute("user")); %>
                   </p> </div>--> 
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
              <br/>
             
             <div class="card" style="width: 90%;margin-left: 5%;">
  <div class="card-header text-light text-center bg-success">
   Student Details
  </div>
  <div class="card-body" >
  <form:form id="filterform" action="../admin/filterStudent" cssClass="form-horizontal"
						method="post" >
						
					<div class="row"><div class="col-sm-2 text-center"></div> <div class="col-sm-2 text-center">
									<p class="text-dark">
										<label for="studentClass"> Class :</label>
									</p>
								</div>

								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										   <select class="form-control" required="required" name="studentClass" tabindex="4">

            
            <option value="Intermediate" selected="selected">Intermediate</option>
            <option value="HighSchool">High School</option>
            <option value="9">9<sup>th</sup></option>
             <option value="8">8<sup>th</sup></option>
            </select>
									</div>
								</div><div class="col-sm-2"><input class="btn btn-success badge-pill" style="width:150px" type="submit" value="Filter">
      </div>
      <div class="col-sm-3"></div>
								  </div>
						</form:form>
    
    
    
    <br/><br/>
    
   <table  class=" table table-striped table-hover table-bordered">
  <thead>
    <tr class="text-dark">
      <th scope="col">Roll No</th>
      <th scope="col">First Name</th>
							<th scope="col">Last Name</th>
							<th scope="col">Father Name</th>
							<th scope="col">Class</th>
							<th scope="col">Email</th>
						
							
							<th scope="colgroup" class="text-center" colspan="1"> Actions </th>
    </tr>
  </thead>
  <tbody>
  <!-- loop over and print our customers -->
						<c:forEach var="tempStudent" items="${students}">

							<!-- construct an "update" link with customer id -->
							<c:url var="updateLink" value="../admin/updateStudent">
								<c:param name="id" value="${tempStudent.id}" />
							</c:url>

<c:url var="viewLink" value="../admin/viewStudent">
								<c:param name="id" value="${tempStudent.id}" />
							</c:url>


							<!-- construct an "delete" link with customer id -->
							<c:url var="deleteLink" value="../admin/deleteStudent">
								<c:param name="id" value="${tempStudent.id}" />
							</c:url>
  
    <tr class="text-dark">
     
      
                                    <td>${tempStudent.id}</td>
								<td>${tempStudent.firstName}</td>
								<td>${tempStudent.lastName}</td>
								<td>${tempStudent.fatherName}</td>
								<td>${tempStudent.studentClass}</td>
								<td>${tempStudent.email}</td>
								
							
							

								<td class="text-center" >
								
									<!-- display the update link --> <a  class="btn btn-success badge-pill" style="width:80px" href="${viewLink}">Details</a>&nbsp;&nbsp; <a  class="btn btn-primary badge-pill" style="width:80px" href="${updateLink}">Update</a>
									&nbsp;&nbsp; <a  class="btn btn-danger badge-pill" style="width:80px" href="${deleteLink}"
									onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
								</td>

							</tr>

						</c:forEach>
   
   
  </tbody>
</table>   
    
    
    
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
