<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style>


/* Create two unequal columns that floats next to each other */
/* Left column */
.leftcolumn {   
  float: center;
  width: 85%;
  border: 10px;
}

/* Fake image */
.fakeimg {
  background: #aaaa;
  width: 100%;
  padding: 20px;
}

/* Add a card effect for articles */
.card {
   background-color: white;
   padding: 20px;
   margin-top: 20px;
   margin-left: 100px;
   
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Footer */

.footer {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 2.5rem;            /* Footer height */
}
/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 800px) {
  .leftcolumn, .rightcolumn {   
    width: 100%;
    padding: 0;
  }
}
</style>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Dashboard- Admin</title>
<link href="../resources/static/css/styles.css" rel="stylesheet" />
<link
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css"
	rel="stylesheet" crossorigin="anonymous" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed bg-white" background="<c:url value="/resources/static/images/about1.jpg"/>"/>
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
	
		<div id="layoutSidenav_content">
			<main>
			<div class="header bg-light" style="font-family: Arial;
  padding: 20px;
  background: #f1f1f1; padding: 30px;
  font-size: 40px;
  text-align: center;
  background: white;">
  <h2>Alpha School</h2>
</div>
<div class="row">
  <div class="leftcolumn">
    <div class="card bg-light">
      <h2>Mission and Vision Statement</h2>
      
      <p><b>Mission:</b></p><p> Alpha School seeks to educate children so that they may grow in knowledge and wisdom, live in humility and integrity, and serve God and mankind with joy and sincerity.
      
<p><b>Vision Statement:</b></p> <p>A School known for values and striving for excellence in every field.</p>
    </div>
    <div class="card bg-light">
      <h2>History</h2>
      
		<p>Alpha School was established by seven visionaries on 15 January 1963, in a residential building at 13/15 East Patel Nagar with twelve students and six teachers. The school grew but remained in seven residential buildings. The school moved to its current school building after 5 years.</p>
    </div>
  </div>
</div>
			</main>
		
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
