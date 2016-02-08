<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
					<div class="panel-heading">File Details</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="dataTable_wrapper">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>Sr No</th>
										<th></th>
										<th>File Name</th>
										<th>File Ext</th>
										<th>File Path</th>
										<th>File Size</th>
										<th>File Status</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${lstObjFileDetails}" var="objFileDetails"
										varStatus="idx">
											<tr>
												<td>${idx.index+1}</td>
												<td>${objFileDetails.fileDrive}</td>
												<td>${objFileDetails.fileName}</td>
												<td>${objFileDetails.fileExtension}</td>
												<td>${objFileDetails.fileFullPath}</td>
												<td>
													<c:choose>
														<c:when test="${objFileDetails.fileSize gt 1000}">
															<fmt:formatNumber value="${objFileDetails.fileSize div 1000 div 1000}" maxFractionDigits="3"/> MB
														</c:when>
														<c:otherwise>
															<fmt:formatNumber value="${objFileDetails.fileSize div 1000}" maxFractionDigits="3"/> KB
														</c:otherwise>
													</c:choose>
												</td>
												<td>${objFileDetails.fileStatus}</td>
												<td></td>
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
				document.fileFilter.submit();;
			}
			function validateForm(){
				if(document.getElementById("objScheduleMaster.schId").value==""){
					alert("Please select schedule.");
					return false;
				}
				return true;
			}
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>