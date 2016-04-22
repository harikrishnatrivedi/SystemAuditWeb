<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="org.systemaudit.model.EnumFileFolderOperationStatus"%>

<tiles:insertDefinition name="layoutTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Folder Management</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">View </h3>
					</div>
					<div class="panel-body">
						<form:form id="folderOperation" name="folderOperation" role="form" method="POST"
									onsubmit="validateForm();" modelAttribute="folderOperationRequest">
							<fieldset>
								<div class="panel-body">
									<div class="form-group">
										<label>Device Name : </label>
										<form:select path="objDeviceInfo.compId" class="form-control" onchange="getFolders(this)">
						   					<form:option value="">Select</form:option>
									       	<form:options items="${mapObjDeviceInfo}" />
						   				</form:select>
									</div>
								</div>
							</fieldset>
						</form:form>
					</div>
				</div>

			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
							Folder Details
					</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="col-lg-3 col-md-6">
							<select id="selectFileAction" name="actionRequest" class="form-control" >
								<option value="MOVEREQUEST">Move Request</option>
								<option value="DELETEREQUESTE">Delete Request</option>
								<option value="SUSPICIOUS">Suspicious</option>
								<option value="GOOD">GOOD</option>
							</select>
						</div>
						<div class="col-lg-3 col-md-6">
							<button type="button" id="btnDeleteRequest" class="btn btn-danger" style="float: right;" onclick="applyAction();">Apply Action</button>
						</div>
						<div class="dataTable_wrapper">
							<table class="table table-striped table-bordered table-hover" id="dataTables-example">
								<thead>
									<tr>
										
										<th>Sr No</th>
										<th>Folder Path</th>
										<th>Operation By</th>
										<th>Device Owner</th>
										<th>Operation Time</th>
										<th>Folder Status</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${lstObjFolderOperationsDetails}" var="objFolderOperationsDetails"
										varStatus="idx">
											<tr>
												<td>${idx.index+1}</td>
												<td>${objFolderOperationsDetails.foldFullPath}</td>
												<td>${objFolderOperationsDetails.foldOperationRequestedBy}</td>
												<td>${objFolderOperationsDetails.objDeviceInfo.compUserName}</td>
												<td>${objFolderOperationsDetails.foldOperationRequestedDatetime}</td>
												<td>${objFolderOperationsDetails.foldStatus}</td>
											</tr>
									</c:forEach>
								</tbody>
								
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<script type="text/javascript">
			function getFolders(objElement){
				if(objElement.velue==""){
					return false;
				}
				document.folderOperation.submit();
			}

			function applyAction(){
				var chkElements=document.getElementsByName('multipleFileOperationRequest');
				var allSelectedFileIds="";

				for(i=0 ; i<chkElements.length ; i++){
					if(chkElements[i].checked==true)
						if(allSelectedFileIds=="")
							allSelectedFileIds=chkElements[i].value;
						else 
							allSelectedFileIds=allSelectedFileIds+','+chkElements[i].value;
				}
				
				if(allSelectedFileIds==""){
					alert("Please select atleast one file.");
					return false;	
				}
				
				var xhttp;
				  if (window.XMLHttpRequest) { // Mozilla, Safari, ...
		            	xhttp= new XMLHttpRequest();
		              //alert("Yes. Your browser must be one among them - Mozilla, Safari, Chrome, Rockmelt, IE 8.0 or above");
		            } else if (window.ActiveXObject) { // IE
		              try {
		            	  xhttp= new ActiveXObject("Msxml2.XMLHTTP");
		                //alert("Yes. Your browser must be IE");
		              } 
		              catch (e) {
		                try {
		                	xhttp= new ActiveXObject("Microsoft.XMLHTTP");
		                  //alert("Yes. Your browser must be IE");
		                } 
		                catch (e) {}
		              }
		            }
		      	
		         	if (!xhttp) {
		              alert("Your browser is not supported AJAX!");
		              return false;
		            }
		        
		        	xhttp.onreadystatechange = function() {
						if (xhttp.readyState == 4 && xhttp.status == 200) {
							var respTxt = xhttp.responseText;
							if(respTxt=="Error"){
								alert("Error while processing. Please try after sometime.");
							}else if(respTxt=="Success"){
								updateStatus(allSelectedFileIds,document.getElementById("selectFileAction").value);
								alert("Updated successfully.");
							}
						}
		        	}	
		        	xhttp.open("GET", "./folderOperation?multipleFileOperationRequest="+allSelectedFileIds+"&operationRequest="+document.getElementById("selectFileAction").value, true);
		        	//xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		        	xhttp.send();
				
			}

			function updateStatus(rowsIds,status){
				var ids=rowsIds.split(",");
				for(i=0 ; i<ids.length ; i++)
					$('#td_status_'+ids[i]).html(status);
			}
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>