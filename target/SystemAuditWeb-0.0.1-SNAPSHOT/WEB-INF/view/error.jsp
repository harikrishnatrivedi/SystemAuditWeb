<%@page import="java.util.Random"%>
<%@page import="org.joda.time.DateTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    

	<title>Bar Code Search Demo</title>

	<!-- QRCode CSS -->
	<link href="./resources/theme/default/css/qrcode.css" rel="stylesheet">

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

    <div id="wrapper">

            
            <div class="row">
	            <div class="col-lg-12">
	            
	            	<div class="panel panel-primary">
	                        <div class="panel-heading">
	                            Error Page
	                        </div>
	                        <%
	                        Integer randomNo=0;
	                        
	                        	do{
	                        		randomNo=new Random().nextInt(999);
	                        	}while(randomNo.toString().length()!=3);
	                        	 String token=randomNo.toString() + DateTime.now().getYear()+DateTime.now().getDayOfMonth()+DateTime.now().getMonthOfYear();
	                        %>
	                        <div class="panel-body">
	                            <p>Some error occured while processing your request.
	                            Please contact administrator with token : <%= token  %> 
	                            Click here to <a href="./login">continue.</a>
	                            </p>
	                            <% System.out.println("Token no : "+token+" Exception : "+ exception.getMessage()); %>
	                        </div>
	                    </div>
	            </div>
       		</div>
    	
 	</div>
 



					           
</body>
</html>