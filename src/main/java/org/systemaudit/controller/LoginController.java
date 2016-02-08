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
import org.systemaudit.model.EmployeeDetails;
import org.systemaudit.model.FileDetails;
import org.systemaudit.service.DeviceInfoService;
import org.systemaudit.service.EmployeeDetailsService;
import org.systemaudit.service.FileDetailsService;
import org.systemaudit.service.ScheduleMasterService;

@Controller
public class LoginController {
	
	@Autowired
	private EmployeeDetailsService objEmployeeDetailsService;
	
	@Autowired
	private DeviceInfoService objDeviceInfoService;

	@Autowired
	private ScheduleMasterService objScheduleMasterService;
	
	@Autowired
	private FileDetailsService objFileDetailsService;
	
	@RequestMapping(value = { "/","/login" }, method = RequestMethod.GET)
	public String login(EmployeeDetails objEmployeeDetails, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpSession session) {
		if(session.getAttribute("empDetails")!=null){
			return "redirect:/home";
		}
		System.out.println("checkRequestLogin");
		model.addAttribute("employeeDetails",new EmployeeDetails());
		return "login";
	}

	@RequestMapping(value = { "/","/login" }, method = RequestMethod.POST)
	public String loginCheck(EmployeeDetails objEmployeeDetails, BindingResult result, ModelMap redirectedModel, HttpSession session) {
		
		if(objEmployeeDetails.getEmpLoginName()==null || objEmployeeDetails.getEmpLoginName().isEmpty() || objEmployeeDetails.getEmpPassword()==null || objEmployeeDetails.getEmpPassword().isEmpty()){
			result.addError(new FieldError("empLoginName","empLoginName","Username or password can not be blank."));
			return "login";
		}
		
		EmployeeDetails objEmpChkLogin=objEmployeeDetailsService.getEmployeeDetailsByUserPassword(objEmployeeDetails.getEmpLoginName(), objEmployeeDetails.getEmpPassword());
		
		if(objEmpChkLogin==null){
	          result.addError(new FieldError("empLoginName","empLoginName","Username or password is wrong."));
		}else {
			session.setAttribute("empDetails", objEmpChkLogin);
			return "redirect:/home";
		}
		return "login";
	}

	@RequestMapping(value = { "/logout" }, method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(EmployeeDetails objEmployeeDetails, BindingResult result, ModelMap redirectedModel, HttpSession session) {
		
		if(session.getAttribute("empDetails")!=null){
			session.removeAttribute("empDetails");
		}
		return "redirect:/login";
	}
	
	@RequestMapping(value = { "/home" }, method = {RequestMethod.GET, RequestMethod.POST})
	public String home(EmployeeDetails objEmployeeDetails, BindingResult result, ModelMap redirectedModel, HttpSession session) {
		
		if(session.getAttribute("empDetails")==null)
			return "redirect:/login";
		
		redirectedModel.addAttribute("totalSystems", ((Long)objDeviceInfoService.countTotalDevice()).toString());
		redirectedModel.addAttribute("pendingSchedules", ((Long)objScheduleMasterService.countSchedulesByStatus("P")).toString());
		redirectedModel.addAttribute("failedSchedules", ((Long)objScheduleMasterService.countSchedulesByStatus("F")).toString());
		redirectedModel.addAttribute("suspiciousSystems", ((Integer)objFileDetailsService.countSuspiciousSystem()).toString());
		
		return "home";
	}
	
}
