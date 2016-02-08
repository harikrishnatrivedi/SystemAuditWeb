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
						<h3 class="panel-title">Filters</h3>
					</div>
					<div class="panel-body">

						<fieldset>
							<div class="panel-body">
								<div class="form-group">
									<label>Device Name : </label>
<%-- 									<form:select path="schCompId" class="form-control" onchange="getSchedule(this);"> --%>
<%-- 					   					<form:option value="">Select</form:option> --%>
<%-- 								       	<form:options items="${lstObjDeviceInfo}" /> --%>
<%-- 					   				</form:select> --%>
								</div>
								<div class="col-lg-12">
				                    <div class="panel panel-default">
				                        <div class="panel-heading">
				                            Schedule Details
				                        </div>
				                        <!-- /.panel-heading -->
				                        <div class="panel-body">
				                            <div class="table-responsive">
				                                <table class="table table-striped table-bordered table-hover">
				                                    <thead>
				                                        <tr>
				                                            <th>Sr No.</th>
				                                            <th>Schedule By</th>
				                                            <th>Schedule Create Time</th>
				                                            <th>Schedule Run Time</th>
				                                            <th>Schedule Status</th>
				                                            <th>Click To View</th>
				                                        </tr>
				                                    </thead>
				                                    <tbody>
				                                    	<c:forEach items="${lstObjScheduleMaster}" var="objScheduleMaster"
										varStatus="idx">
					                                    	<tr>
					                                    		<td>${idx.index+1}</td>
					                                            <td>${objScheduleMaster.schCreatedBy}</td>
					                                            <td>${objScheduleMaster.schCreatedDate}</td>
					                                            <td>${objScheduleMaster.schRunDateTime}</td>
					                                            <td>${objScheduleMaster.schStatus}</td>
					                                            <td><a href="#">View Files</a></td>
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
							</div>
							<div class="panel-footer">
							</div>
						</fieldset>
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
										<th>Sr. No</th>
										<th>Drive Letter</th>
										<th>File Name</th>
										<th>File Extension</th>
										<th>File Path</th>
										<th>File Size</th>
										<th>File Status</th>
										<th>Required Action</th>
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
												<td>${objFileDetails.fileSize}</td>
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
	</tiles:putAttribute>
</tiles:insertDefinition>