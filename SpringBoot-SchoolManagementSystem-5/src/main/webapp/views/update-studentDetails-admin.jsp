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
	 function validate()
     {
  	  
  	   var newpass=document.getElementById("password").value
  	   var conpass=document.getElementById("confirmPassword").value;
  	   
  	   if(newpass!=conpass)
  		   {
  		 
  		  document.getElementById("warningLabel").innerHTML="Password doesn't match";
  		  return false;
  		   }
  	   else
  		   {
  		   document.getElementById("warningLabel").innerHTML=" ";
  		   return true;
  		   }
     }
	</script>
    
    
    
    
    </head>
        <body class="sb-nav-fixed bg-white" background="<c:url value="/resources/static/images/admin.jpg"/>"/>
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-success">
            <a class="navbar-brand" href="../admin/adminPage"><img src="<c:url value="/resources/static/images/logo1.jpg"/>" alt="Logo" style="width:60px; "></a><button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button
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
                                Register Teachers</a
                            ><a class="nav-link text-light" href="../admin/getEmployees"
                                ><div class="sb-nav-link-icon"><i class="fa fa-bars" aria-hidden="true"></i></div>
                                Display Teachers List</a>
                                <a class="nav-link text-light" href="../admin/findTeacher"
                                ><div class="sb-nav-link-icon"><i class="fa fa-search" aria-hidden="true"></i></div>
                                Find Teacher Details</a>
                           
                            <div class="sb-sidenav-menu-heading text-light">Student</div>
                            
                              <a class="nav-link text-light" href="../admin/newStudent"
                                ><div class="sb-nav-link-icon"><i class="fa fa-plus" aria-hidden="true"></i></div>
                                Register Students</a
                            ><a class="nav-link text-light" href="../admin/getStudents"
                                ><div class="sb-nav-link-icon"><i class="fa fa-bars" aria-hidden="true"></i></div>
                                Display Students List</a>
                                <a class="nav-link text-light" href="../admin/findStudent"
                                ><div class="sb-nav-link-icon"><i class="fa fa-search" aria-hidden="true"></i></div>
                                Find Student Details</a>
                            
                        </div>
                    </div>
            <!--         <div class=" py-2 sb-sidenav-footer bg-dark">
                        <div class="small text-light">Logged in as:</div>
                  <p class="text-light">  
                   </p> </div>--> 
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
             
             <!--              
             
             
             <div  align="center" style="border: 1px;margin-top:20px;margin-left:500px; border-radius: 30px;height: 700px; width: 450px"  class="sb-sidenav-dark text-white">
	
	<div class="container">
		<div >
			<h3 class="text-center text-light">Update Details Form</h3><br/>
			
				<div class="panel-body">
					<form:form id="addStudentForm" action="../admin/updateStudentDetails" cssClass="form-horizontal"
						method="post" modelAttribute="student">
						
						<div class="row">
						<div class="col-sm-12" align="left"><input type="hidden" value="${student.id }" name="id" id="id"  hidden=""/></div>
						<div class="col-sm-6" align="left">
<p class="contact"><label for="firstName">First Name</label></p>
<input id="firstName" name="firstName" placeholder="Enter first name" value="${student.firstName }" required tabindex="1" type="text"></div>
<div class="col-sm-6" align="left">
<p class="contact"><label for="lastName">Last Name</label></p>
<input id="lastName" name="lastName" placeholder="Enter last name" required tabindex="2" value="${student.lastName }" type="text ">
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
<input id="fatherName" name="fatherName" placeholder="Enter Father Name " value="${student.fatherName }" required tabindex="5" type="text">

</div>
<div class="col-sm-6" align="left">
<p class="select-style city"><label for="studentClass">Class</label></p>
            <select class="select-style city" required="required" name="studentClass" tabindex="13">

            
              <option value="Intermediate" selected="selected">Intermediate</option>
            <option value="HighSchool">High School</option>
            <option value="9">9<sup>th</sup></option>
             <option value="8">8<sup>th</sup></option>
            </select><br><br>
</div>
</div>




<div class="row"><div class="col-sm-6" align="left">
<label for="birthday">Birthday:</label>
<input type="date" id="dateOfBirth" value="${student.dateOfBirth }"
       min="1994-01-01" max="2016-12-31" name="dateOfBirth" tabindex="4">
</div><div class="col-sm-6" align="left">


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
            <p class="state"><label for="state">state</label></p>
            <select class="select-style city" name="state" tabindex="10">

            
            <option value="UP" selected="selected">UP</option>
            <option value="MP">MP</option>
            <option value="UK">UK</option>
            
            </select></div></div>
            <div class="row"><div class="col-sm-6" align="left">
            <p class="country"><label for="country">Country</label></p>
            <select class="select-style city"  name="country" tabindex="11">
  
            
            <option value="India" selected="selected">India</option>
            <option value="Nepal">Nepal</option>
            <option value="US">US</option>
             
            </select></div><div class="col-sm-6" align="left">

<p class="pincode"><label for="pincode">Pin Code</label></p>
<input id="pincode" name="pincode" placeholder="Enter Pin Code" required tabindex="12" type="number" value="${student.address.pincode }">
</div></div><br><div class="row"><div class="col-sm-12" align="center">
<input class="btn btn-outline-success my-2 my-sm-0 text-light" name="submit" onclick="return validate()" id="submit" tabindex="13" value="Update" type="submit"> 
</div></div></form:form>
				</div>
			</div>
		</div>
	</div>-->
	
	
	
	
	
	
	
	
		<div class="card bg-success"
					style="width: 75%; margin-left: 13%; margin-top: 3%; border-radius: 10px">
					<div class="card-header text-center text-light font-weight-bolder">Update Student Details</div>
					<div class="card-body bg-light" style="border-radius: 10px;">
						<form:form id="addStudentForm" action="../admin/updateStudentDetails" cssClass="form-horizontal"
						method="post" modelAttribute="student">
							<div class="row">
								<div class="col-sm-12" align="left">
									<input type="hidden" value="${student.id }" name="id" id="id"  />
								</div>
							</div>

							<div class="row ">

								<div class="col-sm-2 text-left" style="margin-left: 2%">
									<p class="text-dark">
										<label for="firstName">First Name :</label>
									</p>
								</div>

								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										<input type="text" class="form-control" id="firstName"
											name="firstName" placeholder="Enter First Name" tabindex="1"
											aria-label="firstName" aria-describedby="basic-addon2"
									 value="${student.firstName }"		required="required" >

									</div>
								</div>
								<div class="col-sm-1"></div>

								<div class="col-sm-2 text-left">
									<p class="text-dark">
										<label for="firstName">Last Name :</label>
									</p>
								</div>

								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										<input type="text" class="form-control" id="lastName"
											name="lastName" placeholder="Enter last name" tabindex="2"
											aria-label="lastName" aria-describedby="basic-addon2"
						value="${student.lastName }"					required="required">

									</div>
								</div>

							</div>









							<div class="row ">

								<div class="col-sm-2 text-left" style="margin-left: 2%">
									<p class="text-dark">
										<label for="fatherName">Father Name :</label>
									</p>
								</div>

								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										<input type="text" class="form-control" id="fatherName"
											name="fatherName" placeholder="Enter Father Name " required tabindex="3"
					value="${student.fatherName }"						aria-label="firstName" aria-describedby="basic-addon2">

									</div>
								</div>
								<div class="col-sm-1"></div>

							<div class="col-sm-2 text-left" >
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
								</div>
								

							</div>


							<div class="row ">

								<div class="col-sm-2 text-left" style="margin-left: 2%">
									<p class="text-dark">
										<label for="email">Email :</label>
									</p>
								</div>

								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										<input class="form-control" id="email" name="email"
											placeholder="Enter Email " tabindex="5" type="email"
											aria-label="email" aria-describedby="basic-addon2"
									value="${student.email }"		required="required">

									</div>
								</div>
								<div class="col-sm-1"></div>

								<div class="col-sm-2 text-left">
									<p class="text-dark">
										<label for="password">Password :</label>
									</p>
								</div>
                                     
								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										<input class="form-control" type="password" id="password" tabindex="6"
											name="password" placeholder="Enter password "
											aria-label="password" aria-describedby="basic-addon2"
											required="required">

									</div>
								</div>
								

							</div>
<div class="row">
								<div class="col-sm-8 text-center"></div>
								<div class="col-sm-3 text-center">
									<div style="color: red; font-size: 15px;">${error}</div>
								</div>
							</div>
							<div class="row ">
							
								<div class="col-sm-2 text-left" style="margin-left: 2%">
									<p class="text-dark">
										<label for="DateOfBirth">Date Of Birth :</label>
									</p>
								</div>

								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										<input type="date" class="form-control"  id="dateOfBirth" 
    value="${student.dateOfBirth }"   min="1994-01-01" max="2016-12-31" name="dateOfBirth" tabindex="7">

									</div>
								</div>
							
							

								<div class="col-sm-1"></div>

								<div class="col-sm-2 text-left">
									<p class="text-dark">
										<label for="confirmPassword">Confirm Password :</label>
									</p>
								</div>

								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										<input class="form-control" type="password" tabindex="8"
											id="confirmPassword" name="confirmPassword"
											placeholder="Enter password  " aria-label="confirmPassword"
											aria-describedby="basic-addon2" required="required" onblur="validate()">

									</div>
								</div>
								

							</div>



<div class="row"><div class="col-sm-12 text-right"><div style="color: red;font-size: 12px;margin-right: 15%  "><label id="warningLabel" name="warningLabel"></label></div></div>

</div>

<div class="row ">

								
								
								
									<div class="col-sm-2 text-left" style="margin-left: 2%">
									<p class="text-dark">
										<label for="phoneNumber">Phone Number :</label>
									</p>
								</div>

								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										<input id="phone" name="phoneNumber"
											placeholder="phone number" required tabindex="9" type="tel"
											pattern="[0-9]{10}" class="form-control"
							value="${student.phoneNumber }"				aria-label="lastName" aria-describedby="basic-addon2">

									</div>
								</div>
								
								
								<div class="col-sm-1"></div>
								
									<div class="col-sm-2 text-left">
									<p class="text-dark">
										<label for="AlternateNumber">Alternate Number :</label>
									</p>
								</div>

								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										<input id="phone" name="alternateNumber"
											placeholder="alternate number" required tabindex="10" type="tel"
											pattern="[0-9]{10}" class="form-control"
											aria-label="lastName" aria-describedby="basic-addon2">

									</div>
								</div>

							

							</div>
							
							
					
							
							<div class="row ">

								
								
								
								
								
								
								
								<div class="col-sm-2 text-left" style="margin-left: 2%">
									<p class="text-dark">
										<label for="houseNumber">House Number :</label>
									</p>
								</div>

								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										<input type="number" class="form-control" id="houseNumber" tabindex="11"
											name="houseNumber" pattern="[0-9]"
											placeholder="Enter houseNumber " aria-label="houseNumber"
								value="${student.address.houseNumber }"			aria-describedby="basic-addon2" required="required">

									</div>
								</div>
								
								<div class="col-sm-1"></div>

								<div class="col-sm-2 text-left">
									<p class="text-dark">
										<label for="pincode">pincode :</label>
									</p>
								</div>

								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										<input type="number" class="form-control"  tabindex="12" id="pincode" name="pincode" placeholder="Enter Pin Code" aria-label="houseNumber"
								value="${student.address.pincode }"				aria-describedby="basic-addon2" required="required">

									</div>
								</div>

							</div>
					

<div class="row ">

								<div class="col-sm-2 text-left" style="margin-left: 2%">
									<p class="text-dark">
										<label for="streetName">Street Name :</label>
									</p>
								</div>

								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										<input type="text" class="form-control" id="streetName" tabindex="13" name="streetName" placeholder="Street Address"
											aria-label="firstName" aria-describedby="basic-addon2"
								value="${student.address.streetName }"				required="required">

									</div>
								</div>
								<div class="col-sm-1"></div>

								<div class="col-sm-2 text-left">
									<p class="text-dark">
										<label for="city">City :</label>
									</p>
								</div>

								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										<select class="form-control" name="city" tabindex="14">

            
           
            
              <option value="Agra" selected="selected">Agra</option>
              <option value="Aligarh">Aligarh</option>
               <option value="Bangalore" >Bangalore</option>
            <option value="Chennai">Chennai</option>
            <option value="Delhi">Delhi</option>
              <option value="Etawah">Etawah</option>
              <option value="Kanpur">Kanpur</option>
               <option value="Kolkatta">Kolakatta</option>
              <option value="Mumbai">Mumbai</option>
              <option value="Pune">Pune</option>
              <option value="Hyderabad">Hyderabad</option>
            </select>

									</div>
								</div>

							</div>


<div class="row ">

								<div class="col-sm-2 text-left" style="margin-left: 2%">
									<p class="text-dark">
										<label for="state">State :</label>
									</p>
								</div>

								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										  <select class="form-control" name="state" tabindex="15">

            
            <option value="Uttar Pradesh" selected="selected">Uttar Pradesh</option>
            <option value="Maharashtra">Maharashtra</option>
            <option value="Telangana">Telangana</option>
             <option value="Delhi">Delhi</option>
             <option value="Tamil Nadu">Tamil Nadu</option>
              <option value="Karnataka">Karnataka</option>
            
            </select>
									</div>
								</div>
								<div class="col-sm-1"></div>

								<div class="col-sm-2 text-left">
									<p class="text-dark">
										<label for="country">Country :</label>
									</p>
								</div>

								<div class="col-sm-3 text-left">

									<div class="input-group mb-6 ">
										<select class="form-control"  name="country" tabindex="16">
  
            
            <option value="India" selected="selected">India</option>
            <option value="Nepal">Nepal</option>
            <option value="Canada">Canada</option>
              <option value="Israel">Israel</option>
             
            </select>

									</div>
								</div>

							</div>



						<div class="row"><div class="col-sm-12 text-center"><div style="color: red;font-size: 15px; "><label >${errormsg} ${usermsg }</label></div></div>
</div>



							<div class="row">
								<div class="col-sm-12 text-center text-dark">
									<input onclick="return validate()" id="regButton" type="submit" tabindex="17" value="Update"
										class="btn btn-outline-success my-2 my-sm-0  text-dark c ">
								</div>
							</div>




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
