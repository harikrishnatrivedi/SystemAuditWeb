<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"   %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>System Audit Web Application</title>

    <!-- Bootstrap Core CSS -->
    <link href="./resources/theme/default/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="./resources/theme/default/css/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="./resources/theme/default/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="./resources/theme/default/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                    	
                        
                        <form:form id="login" name="login" role="form" method="POST" onsubmit="return validateUsernamePassword();" modelAttribute="employeeDetails">
                        	<fieldset>
		                        <div class="panel-body">
		                            	<div class="form-group has-error">
											<label class="control-label" for="inputError"><form:errors path="empLoginName" /></label>
										</div>
										<div class="form-group">
											<label>User Name : </label>
		                                    <form:input path="empLoginName" class="form-control" placeholder="Enter Login Name." />
		                                </div>
		                                <div class="form-group">
											<label>Password : </label>
		                                    <form:password path="empPassword" class="form-control" placeholder="Enter password." />
		                                </div>
		                        </div>
		                        <div class="panel-footer">
		                            <button type="submit" id="btnSave" class="btn btn-success">Login</button>
		                        </div>
		                        </fieldset>
	                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>

<!-- jQuery -->
	<script src="./resources/theme/default/js/jquery.min.js"></script>
    
    <!-- Bootstrap Core JavaScript -->
    <script src="./resources/theme/default/js/bootstrap.min.js"></script>
    
    <!-- Metis Menu Plugin JavaScript -->
    <script src="./resources/theme/default/js/metisMenu.min.js"></script>
    
    

    <!-- Custom Theme JavaScript -->
    <script src="./resources/theme/default/js/sb-admin-2.js"></script>
    <script type="text/javascript" src="./resources/theme/default/js/common.js"></script>
   
</body>

	
	
	
    <script type="text/javascript">

    function validateUsernamePassword() {
        Trim(document.getElementById("empLoginName"));
        Trim(document.getElementById("empPassword"));
        if(document.getElementById("empLoginName").value=="" || document.getElementById("empPassword").value==""){
            alert("Please enter username and password.");
        	return false;
        }else{
            return true;
        }
    }
    
	</script>



</html>