<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="org.systemaudit.model.EnumFileFolderOperationStatus"%>

<tiles:insertDefinition name="layoutTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">System Configuration</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Update Configuration</h3>
					</div>
					<div class="panel-body">
						<form:form id="keyValue" name="keyValue" role="form" method="POST"
									onsubmit="return validateForm();" modelAttribute="keyValue">
							<fieldset>
								<div class="panel-body">
									<div class="form-group has-error">
										<label class="control-label" for="inputError"><form:errors
												path="keyValue" /></label>
									</div>
									<div class="form-group">
										<label>Select Setting : </label>
										<form:select path="keyId" class="form-control" >
						   					<form:option value="">Select</form:option>
									       	<form:options items="${mapObjKeyValue}" />
						   				</form:select>
									</div>
									<div class="form-group">
										<label>Setting Value</label>
										<form:input path="keyValue" class="form-control"
											placeholder="Enter setting." />
									</div>
								</div>
								<div class="panel-footer">
									<button type="submit" id="btnUpdate" class="btn btn-success">Update</button>
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