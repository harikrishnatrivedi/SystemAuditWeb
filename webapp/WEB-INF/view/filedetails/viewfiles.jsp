<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="org.systemaudit.model.EnumFileFolderOperationStatus"%>

<tiles:insertDefinition name="layoutTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">File Management</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Filters</h3>
					</div>
					<div class="panel-body">
						<form:form id="fileFilter" name="fileFilter" role="form" method="POST"
									onsubmit="validateForm();" modelAttribute="fileDetails">
							<fieldset>
								<div class="panel-body">
									<div class="form-group">
										<label>Device Name : </label>
										<form:select path="objDeviceInfo.compId" class="form-control" onchange="getSchedules(this);">
						   					<form:option value="">Select</form:option>
									       	<form:options items="${mapObjDeviceInfo}" />
						   				</form:select>
									</div>
									<div class="form-group">
										<label>Schedule List : </label>
										<form:select path="objScheduleMaster.schId" class="form-control">
						   					<form:option value="">Select</form:option>
									       	<form:options items="${mapObjScheduleMaster}" />
						   				</form:select>
									</div>
									<div class="panel-body">
										<div class="form-group">
											<label>File Name : </label>
											<form:input path="fileName" class="form-control"
												placeholder="Enter File Name Filter." />
										</div>
										<div class="form-group">
											<label>File Path : </label>
											<form:input path="fileFullPath" class="form-control"
												placeholder="Enter Path Filter." />
										</div>
										<div class="form-group">
											<label>File Extension : </label>
											<form:input path="fileExtension" class="form-control"
												placeholder="Enter File Extension Filter." />
										</div>
										<div class="form-group">
											<label>Drive : </label>
											<form:input path="fileDrive" class="form-control"
												placeholder="Enter File Drive Filter." />
										</div>
										<div class="form-group">
											<label>File Status : 
											</label>
											<form:select path="fileStatus" class="form-control">
												<form:options items="${EnumFileFolderOperationStatus.values() }"></form:options>
												<%--<form:option value="${EnumFileFolderOperationStatus.values().SECPICIOUS}">Suspicious</form:option>
												<form:option value="${EnumFileFolderOperationStatus.GOOD}">Normal Files</form:option>
												<form:option value="${EnumFileFolderOperationStatus.NOTEXIST}">File Not Exist</form:option>
												<form:option value="${EnumFileFolderOperationStatus.NORIGHTS}">No Permission to Do Operation</form:option>
												<form:option value="${EnumFileFolderOperationStatus.FOLDERNOTEMPTY}">Folder is Not Empty to Delete</form:option>
												<form:option value="${EnumFileFolderOperationStatus.MOVEREQUEST}">Move Requested</form:option>
												<form:option value="${EnumFileFolderOperationStatus.MOVED}">Moved For Audit</form:option>
												<form:option value="${EnumFileFolderOperationStatus.MOVEFAILED}">Move Failed</form:option>
												<form:option value="${EnumFileFolderOperationStatus.DELETEREQUEST}">Delete Requested</form:option>
												<form:option value="${EnumFileFolderOperationStatus.DELETEED}">Deleted</form:option>
												<form:option value="${EnumFileFolderOperationStatus.DELETEFAILED}">Delete Failed</form:option>
												<form:option value="${EnumFileFolderOperationStatus.ALL}">All.</form:option> --%>
											</form:select>
										</div>
										
									</div>
								</div>
								<div class="panel-footer">
									<button type="submit" id="btnSearch" class="btn btn-success">Apply Filter</button>
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
							File Details
					</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="col-lg-3 col-md-6">
							<select id="selectFileAction" name="actionRequest" class="form-control" >
								<option value="MOVEREQUEST">Move Request</option>
								<option value="DELETEREQUEST">Delete Request</option>
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
										<th><input type="checkbox" class="check" id="checkAll"></th>
										<th>Sr No</th>
										<th></th>
										<th>File Name</th>
										<th>File Ext</th>
										<th>File Path</th>
										<th>File Size</th>
										<th>File Status</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${lstObjFileDetails}" var="objFileDetails"
										varStatus="idx">
											<tr>
												<td>
													<input type="checkbox" name="multipleFileOperationRequest" value="${objFileDetails.fileId}" class="check">
													<%-- <c:choose>
														<c:when test="${objFileDetails.fileStatus ne EnumFileFolderOperationStatus.DELETED and objFileDetails.fileStatus ne EnumFileFolderOperationStatus.NOTEXIST }">
															<input type="checkbox" value="${objFileDetails.fileId}" name="fileAction" />
														</c:when>
														<c:otherwise>
															<input type="checkbox" value="${objFileDetails.fileId}" desabled="true" name="fileAction" />
														</c:otherwise>
													</c:choose> --%>
												</td>
												<td>${idx.index+1}</td>
												<td>${objFileDetails.fileDrive}</td>
												<td>${objFileDetails.fileName}</td>
												<td>${objFileDetails.fileExtension}</td>
												<td>${objFileDetails.fileFolderPath}</td>
												<td>
													<c:choose>
														<c:when test="${objFileDetails.fileSize gt 1000000}">
															<fmt:formatNumber value="${objFileDetails.fileSize div 1000 div 1000}" maxFractionDigits="3"/> MB
														</c:when>
														<c:otherwise>
															<fmt:formatNumber value="${objFileDetails.fileSize div 1000}" maxFractionDigits="3"/> KB
														</c:otherwise>
													</c:choose>
												</td>
												<td id="td_status_${objFileDetails.fileId}">${objFileDetails.fileStatus}</td>
												
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
			function getSchedules(objElement){
				if(objElement.velue==""){
					alert("Please select device.");
					return false;
				}
				document.fileFilter.submit();
			}
			function validateForm(){
				if(document.getElementById("objScheduleMaster.schId").value==""){
					alert("Please select schedule.");
					return false;
				}
				return true;
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
		        	xhttp.open("GET", "./fileOperation?multipleFileOperationRequest="+allSelectedFileIds+"&operationRequest="+document.getElementById("selectFileAction").value, true);
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