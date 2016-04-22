<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title></title>

<!-- Bootstrap Core CSS -->
<link href="./resources/theme/default/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="./resources/theme/default/css/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="./resources/theme/default/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link href="./resources/theme/default/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- DataTables CSS
    <link href="./resources/theme/default/css/dataTables.bootstrap.css" rel="stylesheet"> -->

    <!-- DataTables Responsive CSS -->
    <link href="./resources/theme/default/css/responsive.dataTables.scss" rel="stylesheet">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<div id="wrapper">
		<tiles:insertAttribute name="master" />
		<div id="page-wrapper">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
</body>

<script src="./resources/theme/default/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="./resources/theme/default/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="./resources/theme/default/js/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
    <script src="./resources/theme/default/js/jquery.dataTables.min.js"></script> 
    <script src="./resources/theme/default/js/dataTables.bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="./resources/theme/default/js/sb-admin-2.js"></script>
<script type="text/javascript"
	src="./resources/theme/default/js/common.js"></script>
<script type="text/javascript">
			 $(document).ready(function() {
				$('#dataTables-example').DataTable({
					responsive : true
				});
			}); 

			$("#checkAll").click(function () {
			    $(".check").prop('checked', $(this).prop('checked'));
			});
			
			
		</script>
</html>
