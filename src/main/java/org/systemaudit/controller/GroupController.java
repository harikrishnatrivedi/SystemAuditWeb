package org.systemaudit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.systemaudit.model.DeviceGroup;
import org.systemaudit.service.DeviceGroupService;

@Controller
public class GroupController {
	
	@Autowired
	private DeviceGroupService objDeviceGroupService;

	@RequestMapping(value = { "/addGroup" }, method = RequestMethod.GET)
	public String addGroupGet(DeviceGroup objDeviceGroup, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpSession session) {
		if(session.getAttribute("empDetails")==null){
			return "redirect:/login";
		}
		model.addAttribute("deviceGroup",new DeviceGroup());
		return "addgroup";
	}

	@RequestMapping(value = { "/addGroup" }, method = RequestMethod.POST)
	public String addGroupPost(DeviceGroup deviceGroup, BindingResult result, ModelMap redirectedModel, HttpSession session) {
		
		if(deviceGroup.getGrpName()==null || deviceGroup.getGrpName().isEmpty()){
			result.addError(new FieldError("grpName", "grpName", "Group name can not be blank."));
			return "addgroup";
		}
		try {
			System.out.println("deviceGroup : "+deviceGroup);
			objDeviceGroupService.addDeviceGroup(deviceGroup);
			result.addError(new FieldError("grpName", "grpName", "Record saved successfully."));
		} catch (Exception e) {
			result.addError(new FieldError("grpName", "grpName", e.getMessage()));
			e.printStackTrace();
		}
		
		return "addgroup";
	}

	
}
