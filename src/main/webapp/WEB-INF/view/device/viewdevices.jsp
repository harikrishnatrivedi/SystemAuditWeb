<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="layoutTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Device Management</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">View Devices</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>Sr. No</th>
										<th>Device Name</th>
										<th>User Name</th>
										<th>Os Name</th>
										<th>Processor Type</th>
										<th>Group Name</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${lstObjDeviceInfo}"
										var="objDeviceInfo" varStatus="idx">
										<tr>
											<td>${idx.index+1}</td>
											<td>${objDeviceInfo.compName}</td>
											<td>${objDeviceInfo.compUserName}</td>
											<td>${objDeviceInfo.compOsName}</td>
											<td>${objDeviceInfo.compProcessorType}</td>
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

			</div>
			<!-- /.col-lg-12 -->
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>