package org.systemaudit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.systemaudit.model.DeviceInfo;
import org.systemaudit.model.EmployeeDetails;
import org.systemaudit.model.EnumFileFolderOperationStatus;
import org.systemaudit.model.EnumScheduleStatus;
import org.systemaudit.model.FileDetails;
import org.systemaudit.model.ScheduleMaster;
import org.systemaudit.service.DeviceInfoService;
import org.systemaudit.service.FileDetailsService;
import org.systemaudit.service.ScheduleMasterService;

@Controller
public class FileController {

	@Autowired
	private FileDetailsService objFileDetailsService;

	@Autowired
	private DeviceInfoService objDeviceInfoService;

	@Autowired
	private ScheduleMasterService objScheduleMasterService;

	@RequestMapping(value = { "/viewFiles" }, method = RequestMethod.GET)
	public String viewFilesGet(FileDetails objFileDetails, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";
		List<DeviceInfo> lstObjDeviceInfo = objDeviceInfoService.listDeviceInfo();
		Map<String, String> mapObjDeviceInfo = new HashMap<String, String>();
		for (DeviceInfo objDeviceInfo : lstObjDeviceInfo)
			mapObjDeviceInfo.put(((Integer) objDeviceInfo.getCompId()).toString(),
					objDeviceInfo.getCompUserName() + "_" + objDeviceInfo.getCompName());

		model.addAttribute("mapObjDeviceInfo", mapObjDeviceInfo);
		model.addAttribute("enumFileOperations", EnumFileFolderOperationStatus.values());
		model.addAttribute("fileDetails", objFileDetails);

		return "filedetails/viewfiles";
	}

	@RequestMapping(value = { "/viewFiles" }, method = RequestMethod.POST)
	public String viewFileDetails(FileDetails objFileDetails, BindingResult result, ModelMap redirectedModel,
			HttpSession session) {
		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";
		List<DeviceInfo> lstObjDeviceInfo = objDeviceInfoService.listDeviceInfo();
		Map<String, String> mapObjDeviceInfo = new HashMap<String, String>();
		for (DeviceInfo objDeviceInfo : lstObjDeviceInfo)
			mapObjDeviceInfo.put(((Integer) objDeviceInfo.getCompId()).toString(),
					objDeviceInfo.getCompUserName() + "_" + objDeviceInfo.getCompName());

		redirectedModel.addAttribute("mapObjDeviceInfo", mapObjDeviceInfo);

		if (objFileDetails != null) {
			System.out.println("objFileDetails : " + objFileDetails);
			if (objFileDetails.getObjScheduleMaster() != null
					&& objFileDetails.getObjScheduleMaster().getSchId() != null) {
				redirectedModel.addAttribute("lstObjFileDetails",
						objFileDetailsService.listFileDetailsByFileFilter(objFileDetails));
			}
			if (objFileDetails.getObjDeviceInfo() != null && objFileDetails.getObjDeviceInfo().getCompId() != null) {
				Map<String, String> mapObjScheduleMaster = new HashMap<String, String>();
				List<ScheduleMaster> lstObjScheduleMaster = objScheduleMasterService
						.listScheduleMasterByDeviceIdAndScheduleStatus(objFileDetails.getObjDeviceInfo().getCompId(),
								EnumScheduleStatus.SUCCESS);

				// System.out.println("lstObjScheduleMaster :
				// "+lstObjScheduleMaster);

				for (ScheduleMaster objScheduleMaster : lstObjScheduleMaster)
					mapObjScheduleMaster.put(((Integer) objScheduleMaster.getSchId()).toString(),
							objScheduleMaster.getSchActualRunDateTime().toString());

				// System.out.println("mapObjScheduleMaster :
				// "+mapObjScheduleMaster);

				redirectedModel.addAttribute("mapObjScheduleMaster", mapObjScheduleMaster);
			}
		}

		return "filedetails/viewfiles";
	}

	@ResponseBody
	@RequestMapping(value = { "/fileOperation" }, method = RequestMethod.GET)
	public void performOperation(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		EmployeeDetails objEmployeeDetails=null;
		if (session.getAttribute("empDetails") == null){
			try{response.getWriter().print("Error");}catch(Exception e){System.out.println("CantGetSession"); e.printStackTrace();}
			return;
		}else{
			System.out.println("GetEmpSession");
			objEmployeeDetails=(EmployeeDetails)session.getAttribute("empDetails");
			System.out.println("AfterGetEmpSession");
		}
		
		String fileIds="";
		String operation="";
		if(request.getParameter("multipleFileOperationRequest")!=null)
			fileIds=request.getParameter("multipleFileOperationRequest").toString();
		if(request.getParameter("operationRequest")!=null)
			operation=request.getParameter("operationRequest").toString();
		try{
		EnumFileFolderOperationStatus enumEnumFileFolderOperationStatus=EnumFileFolderOperationStatus.valueOf(operation);
		objFileDetailsService.updateFileDetailsByOperationRequest(fileIds, enumEnumFileFolderOperationStatus, objEmployeeDetails.getEmpLoginName());
		response.getWriter().print("Success");
		}catch(Exception ex){
			try{
				ex.printStackTrace();
			response.getWriter().print("Error");}catch(Exception e){System.out.println("Can'tSaveOperationInfo");e.printStackTrace();}
		}
		
		
		
	}

}
