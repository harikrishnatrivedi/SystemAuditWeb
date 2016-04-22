package org.systemaudit.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

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
import org.systemaudit.model.KeyValue;
import org.systemaudit.service.DeviceInfoService;
import org.systemaudit.service.FolderOperationRequestService;
import org.systemaudit.service.KeyValueService;

@Controller
public class KeyValueController {

	@Autowired
	private KeyValueService objKeyValueService;

		
	@RequestMapping(value = { "/updateKeyValue" }, method = RequestMethod.GET)
	public String updateKeyValueGet(KeyValue objKeyValue, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";
		
		
		Map<String, String> mapObjKeyValue=new HashMap<String, String>();
		for(KeyValue objKeyValue4Map : objKeyValueService.listKeyValue())
			mapObjKeyValue.put(objKeyValue4Map.getKvalId(), objKeyValue4Map.getKvalValue());
		model.addAttribute("mapObjKeyValue",mapObjKeyValue);
		
		return "folderoperation/addfolder";
	}

	@RequestMapping(value = { "/updateKeyValue" }, method = RequestMethod.POST)
	public String addFolderPost(FolderOperationRequest objFolderOperationRequest, BindingResult result,
			ModelMap redirectedModel, HttpSession session) {
		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";

		Map<String, String> mapObjKeyValue=new HashMap<String, String>();
		for(KeyValue objKeyValue4Map : objKeyValueService.listKeyValue())
			mapObjKeyValue.put(objKeyValue4Map.getKvalId(), objKeyValue4Map.getKvalValue());
		redirectedModel.addAttribute("mapObjKeyValue",mapObjKeyValue);

		
		return "folderoperation/addfolder";
	}


	@RequestMapping(value = { "/getValueForKey" }, method = RequestMethod.GET)
	public void viewFolderOperaqtionPost(KeyValue objKeyValue, BindingResult result,
			ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try{
		if (session.getAttribute("empDetails") == null)
			response.getWriter().print("Error");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
	}

	

}
