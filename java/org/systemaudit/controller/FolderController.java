package org.systemaudit.controller;

import java.util.Date;
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
import org.systemaudit.model.DeviceInfo;
import org.systemaudit.model.EmployeeDetails;
import org.systemaudit.model.EnumFileFolderOperationStatus;
import org.systemaudit.model.FolderOperationRequest;
import org.systemaudit.service.DeviceInfoService;
import org.systemaudit.service.FolderOperationRequestService;

@Controller
public class FolderController {

	@Autowired
	private FolderOperationRequestService objFolderOperationRequestService;

	@Autowired
	private DeviceInfoService objDeviceInfoService;

	
	@RequestMapping(value = { "/addFolder" }, method = RequestMethod.GET)
	public String addFolderGet(FolderOperationRequest objFolderOperationRequest, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";
		List<DeviceInfo> lstObjDeviceInfo = objDeviceInfoService.listDeviceInfo();
		Map<String, String> mapObjDeviceInfo = new HashMap<String, String>();
		for (DeviceInfo objDeviceInfo : lstObjDeviceInfo)
			mapObjDeviceInfo.put(((Integer) objDeviceInfo.getCompId()).toString(),
					objDeviceInfo.getCompUserName() + "_" + objDeviceInfo.getCompName());

		model.addAttribute("mapObjDeviceInfo", mapObjDeviceInfo);
		model.addAttribute("folderOperationRequest", objFolderOperationRequest);

		return "folderoperation/addfolder";
	}

	@RequestMapping(value = { "/addFolder" }, method = RequestMethod.POST)
	public String addFolderPost(FolderOperationRequest objFolderOperationRequest, BindingResult result,
			ModelMap redirectedModel, HttpSession session) {
		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";

		List<DeviceInfo> lstObjDeviceInfo = objDeviceInfoService.listDeviceInfo();
		Map<String, String> mapObjDeviceInfo = new HashMap<String, String>();
		for (DeviceInfo objDeviceInfo : lstObjDeviceInfo)
			mapObjDeviceInfo.put(((Integer) objDeviceInfo.getCompId()).toString(),
					objDeviceInfo.getCompUserName() + "_" + objDeviceInfo.getCompName());
		redirectedModel.addAttribute("mapObjDeviceInfo", mapObjDeviceInfo);
		try {
			if (objFolderOperationRequest != null) {
				if(!objFolderOperationRequestService.checkUniqueMoveOrDeleteRequest(objFolderOperationRequest)){
					result.addError(new FieldError("foldFullPath", "foldFullPath", "Request already taken."));
					return "folderoperation/addfolder";
				}
				objFolderOperationRequest.setFoldOperationRequestedBy(
						((EmployeeDetails) session.getAttribute("empDetails")).getEmpLoginName());
				objFolderOperationRequest.setObjDeviceInfo(objDeviceInfoService
						.getDeviceInfoById(objFolderOperationRequest.getObjDeviceInfo().getCompId()));
				
				objFolderOperationRequest.setFoldOperationRequestedDatetime(new Date());
				System.out.println("objFolderOperationRequest : " + objFolderOperationRequest);
				objFolderOperationRequestService.addFolderOperationRequest(objFolderOperationRequest);
				result.addError(new FieldError("foldFullPath", "foldFullPath", "Record saved successfully."));
			}
		} catch (Exception ex) {
			result.addError(new FieldError("foldFullPath", "foldFullPath", "Error While Saving."));
			ex.printStackTrace();
		}
		return "folderoperation/addfolder";
	}

	@RequestMapping(value = { "/viewFoldersOperation" }, method = RequestMethod.GET)
	public String viewFolderOperaqtionGet(FolderOperationRequest objFolderOperationRequest, BindingResult result,
			ModelMap model, HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";
		List<DeviceInfo> lstObjDeviceInfo = objDeviceInfoService.listDeviceInfo();
		Map<String, String> mapObjDeviceInfo = new HashMap<String, String>();
		for (DeviceInfo objDeviceInfo : lstObjDeviceInfo)
			mapObjDeviceInfo.put(((Integer) objDeviceInfo.getCompId()).toString(),
					objDeviceInfo.getCompUserName() + "_" + objDeviceInfo.getCompName());

		model.addAttribute("mapObjDeviceInfo", mapObjDeviceInfo);

		return "folderoperation/viewfolders";
	}

	@RequestMapping(value = { "/viewFoldersOperation" }, method = RequestMethod.POST)
	public String viewFolderOperaqtionPost(FolderOperationRequest objFolderOperationRequest, BindingResult result,
			ModelMap model, HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";
		List<DeviceInfo> lstObjDeviceInfo = objDeviceInfoService.listDeviceInfo();
		Map<String, String> mapObjDeviceInfo = new HashMap<String, String>();
		for (DeviceInfo objDeviceInfo : lstObjDeviceInfo)
			mapObjDeviceInfo.put(((Integer) objDeviceInfo.getCompId()).toString(),
					objDeviceInfo.getCompUserName() + "_" + objDeviceInfo.getCompName());
		model.addAttribute("mapObjDeviceInfo", mapObjDeviceInfo);

		model.addAttribute("lstObjFolderOperationsDetails",
				objFolderOperationRequestService.listFolderOperationRequestByDeviceInfoId(
						objFolderOperationRequest.getObjDeviceInfo().getCompId(),
						EnumFileFolderOperationStatus.ALL));

		return "folderoperation/viewfolders";
	}

	@RequestMapping(value = { "/afterComplition" }, method = RequestMethod.GET)
	public void performOperation(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		EmployeeDetails objEmployeeDetails = null;
		if (session.getAttribute("empDetails") == null) {
			try {
				response.getWriter().print("Error");
			} catch (Exception e) {
			}
			return;
		} else {
			objEmployeeDetails = (EmployeeDetails) session.getAttribute("empDetails");
		}

		String fileIds = "";
		String operation = "";
		if (request.getParameter("multipleFileOperationRequest") != null)
			fileIds = request.getParameter("multipleFileOperationRequest").toString();
		if (request.getParameter("operationRequest") != null)
			operation = request.getParameter("operationRequest").toString();
		try {
			EnumFileFolderOperationStatus enumEnumFileFolderOperationStatus = EnumFileFolderOperationStatus
					.valueOf(operation);
			// objFolderOperationRequestService.(fileIds,
			// enumEnumFileFolderOperationStatus,
			// objEmployeeDetails.getEmpLoginName());
			response.getWriter().print("Success");
		} catch (Exception ex) {
			try {
				response.getWriter().print("Error");
			} catch (Exception e) {
			}
		}

	}

}
