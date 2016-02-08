<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="layoutTemplate">
	<tiles:putAttribute name="body">

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Group Management</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-6 col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Add Group</h3>
					</div>
					<div class="panel-body">
						<form:form id="addGroup" name="addGroup" role="form" method="POST"
							onsubmit="validateForm();" modelAttribute="deviceGroup">
							<fieldset>
								<div class="panel-body">
									<div class="form-group has-error">
										<label class="control-label" for="inputError"><form:errors
												path="grpName" /></label>
									</div>
									<div class="form-group">
										<label>Group Name : </label>
										<form:input path="grpName" class="form-control"
											placeholder="Enter Group Name." />
									</div>
									<div class="form-group">
										<label>Group Description : </label>
										<form:input path="grpDescription" class="form-control"
											placeholder="Enter Group Description." />
									</div>
								</div>
								<div class="panel-footer">
									<button type="submit" id="btnSave" class="btn btn-success">Add
										Group</button>
								</div>
							</fieldset>
						</form:form>
					</div>
				</div>
			</div>

		</div>

		<script type="text/javascript">
			function validateForm() {
				var txtElement = document.getElementById("grpName");
				Trim(txtElement);
				if (txtElement.value == "") {
					alert("Group name is required.");
					txtElement.focus();
					return true;
				}
				return true;
			}
		</script>

	</tiles:putAttribute>
</tiles:insertDefinition>