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
						<form:form id="folderDetials" name="folderDetials" role="form" method="POST"
									onsubmit="validateForm();" modelAttribute="fileDetails">
							<fieldset>
								<div class="panel-body">
									<div class="form-group">
										<label>Device Name : </label>
										<form:select path="objDeviceInfo.compId" class="form-control" onchange="getSuspiciousFolders(this);">
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
						<div class="dataTable_wrapper">
							<table class="table table-striped table-bordered table-hover" id="dataTables-example">
								<thead>
									<tr>
										<th>Sr No</th>
										<th>Folder Path</th>
										<th>Total Files</th>
										<th>Total Suspicious Files</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${lstObjFolderDetails}" var="objFileDetails"
										varStatus="idx">
											<tr>
												<td>${idx.index+1}</td>
												<td>${objFileDetails.fileFolderPath}</td>
												<td>${objFileDetails.fileTotal}</td>
												<td>${objFileDetails.fileTotalSuspicious}</td>
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
			function getSuspiciousFolders(objElement){
				if(objElement.velue==""){
					alert("Please select device.");
					return false;
				}
				document.folderDetials.submit();
			}
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>