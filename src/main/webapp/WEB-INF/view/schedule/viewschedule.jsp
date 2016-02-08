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
							<c:choose>
                            	<c:when test="${empty viewScheduleMessage}">View All Schedules</c:when>
                            	<c:otherwise>${viewScheduleMessage}</c:otherwise>
                            </c:choose>
						</h3>
					</div>
					<div class="panel-body">

						<fieldset>
							<div class="panel-body">
								<div class="col-lg-12">
				                    <div class="panel panel-default">
				                        <div class="panel-heading">
				                            Schedule Details
				                        </div>
				                        <!-- /.panel-heading -->
				                        <div class="panel-body">
				                            <div class="dataTable_wrapper">
				                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
				                                    <thead>
				                                        <tr>
				                                            <th>Sr No.</th>
				                                            <th>Device Name</th>
				                                            <th>User Name</th>
				                                            <th>Schedule By</th>
				                                            <th>Schedule Run Time</th>
				                                            <th>Schedule Create Time</th>
				                                            <th>Schedule Status</th>
				                                        </tr>
				                                    </thead>
				                                    <tbody>
				                                    	<c:forEach items="${lstObjScheduleMaster}" var="objScheduleMaster"
										varStatus="idx">
					                                    	<tr>
					                                    		<td>${idx.index+1}</td>
					                                    		<td>${objScheduleMaster.objDeviceInfo.compName}</td>
					                                    		<td>${objScheduleMaster.objDeviceInfo.compUserName}</td>
					                                            <td>${objScheduleMaster.schCreatedBy}</td>
					                                            <td>${objScheduleMaster.schRunDateTime}</td>
					                                            <td>${objScheduleMaster.schCreatedDate}</td>
					                                            <td>${objScheduleMaster.schStatus}</td>
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
	</tiles:putAttribute>
</tiles:insertDefinition>