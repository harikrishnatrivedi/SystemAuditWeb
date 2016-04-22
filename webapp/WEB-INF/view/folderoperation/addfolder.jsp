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
						<h3 class="panel-title">Add New Folder Request</h3>
					</div>
					<div class="panel-body">
						<form:form id="folderOperation" name="folderOperation" role="form" method="POST"
									onsubmit="return validateForm();" modelAttribute="folderOperationRequest">
							<fieldset>
								<div class="panel-body">
									<div class="form-group has-error">
										<label class="control-label" for="inputError"><form:errors
												path="foldFullPath" /></label>
									</div>
									<div class="form-group">
										<label>Device Name : </label>
										<form:select path="objDeviceInfo.compId" class="form-control" >
						   					<form:option value="">Select</form:option>
									       	<form:options items="${mapObjDeviceInfo}" />
						   				</form:select>
									</div>
									
									<div class="panel-body">
										<div class="form-group">
											<label>Floder Path : </label>
											<form:input path="foldFullPath" class="form-control"
												placeholder="Enter Path." />
										</div>
										<div class="form-group">
											<label>Folder Status : 
											</label>
											<form:select path="foldStatus" class="form-control">
												<form:option value="MOVEREQUEST">Move Request</form:option>
												<form:option value="DELETEREQUESTE">Delete Request</form:option>
												<form:option value="SUSPICIOUS">Suspicious</form:option>
												<form:option value="GOOD">GOOD</form:option>
											</form:select>
										</div>
										
									</div>
								</div>
								<div class="panel-footer">
									<button type="submit" id="btnSearch" class="btn btn-success">Save</button>
								</div>
							</fieldset>
						</form:form>
					</div>
				</div>

			</div>
		</div>
	
		<script type="text/javascript">
			function validateForm(){
				if(checkRequiredFields("folderOperation")){
					return true;
				}
				return false;
			}
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>