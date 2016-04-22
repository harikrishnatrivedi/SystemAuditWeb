<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="layoutTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Schedule Management</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							New Schedule
						</h3>
					</div>
					<div class="panel-body">
						<form:form id="scheduleMaster" name="scheduleMaster" role="form"
							method="POST" modelAttribute="scheduleMaster" onsubmit="return validate();">
							<div class="panel-body">
								<fieldset>
									<div class="form-group has-error">
	                        			<label class="control-label" for="inputError"><form:errors path="objDeviceInfo.compId" /></label>
									</div>
									<div class="form-group">
										<label>Device Name : </label>
										<form:input path="objDeviceInfo.compId"
											list="listObjDeviceInfo" />
										<datalist id="listObjDeviceInfo">
											<c:forEach items="${lstObjDeviceInfo}" var="objDeviceInfo">
												<option data-value="${objDeviceInfo.compId}" value="${objDeviceInfo.compName}"></option>
											</c:forEach>
										</datalist>
									</div>
								</fieldset>
							</div>
							<div class="panel-footer">
								<button type="submit" id="createSchedule" class="btn btn-success">Create Current Schedule</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			function validate(){
				alert(document.getElementById("objDeviceInfo.compId").value);
				return false;
			}
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>