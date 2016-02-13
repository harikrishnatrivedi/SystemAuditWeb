package org.systemaudit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.systemaudit.model.DeviceInfo;
import org.systemaudit.model.EnumScheduleStatus;
import org.systemaudit.model.FileDetails;
import org.systemaudit.model.ScheduleMaster;
import org.systemaudit.service.DeviceInfoService;
import org.systemaudit.service.FileDetailsService;
import org.systemaudit.service.ScheduleMasterService;

@Controller
public class ScheduleController {

	@Autowired
	private FileDetailsService objFileDetailsService;
	
	@Autowired
	private ScheduleMasterService objScheduleMasterService;

	@RequestMapping(value = { "/viewSchedules" }, method = RequestMethod.GET)
	public String viewSchedulesGet(ModelMap model, HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";

		model.addAttribute("viewScheduleMessage", "View All Schedules");
		model.addAttribute("lstObjScheduleMaster", objScheduleMasterService.listScheduleMaster());
		
		if(request.getParameter("schId")!=null)
			model.addAttribute("lstObjFileDetails", objFileDetailsService.listFileDetailsByScheduleMasterId(Integer.parseInt(request.getParameter("schId").toString())));
		
		return "schedule/viewschedule";
	}
	
	@RequestMapping(value = { "/viewPendingSchedules" }, method = RequestMethod.GET)
	public String viewPendingSchedulesGet(ModelMap model, HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";
		model.addAttribute("viewScheduleMessage", "View Pending Schedules");
		model.addAttribute("lstObjScheduleMaster", objScheduleMasterService.listScheduleMasterByStatus(EnumScheduleStatus.PENDING));
		
		return "schedule/viewschedule";
	}
	
	@RequestMapping(value = { "/viewSuccessSchedules" }, method = RequestMethod.GET)
	public String viewSuccessSchedulesGet(ModelMap model, HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";
		model.addAttribute("viewScheduleMessage", "View Success Schedules");
		model.addAttribute("lstObjScheduleMaster", objScheduleMasterService.listScheduleMasterByStatus(EnumScheduleStatus.SUCCESS));
		
		return "schedule/viewschedule";
	}
	
	@RequestMapping(value = { "/viewFailedSchedules" }, method = RequestMethod.GET)
	public String viewFailedSchedulesGet(ModelMap model, HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";
	
		model.addAttribute("viewScheduleMessage", "View Failed Schedules");
		model.addAttribute("lstObjScheduleMaster", objScheduleMasterService.listScheduleMasterByStatus(EnumScheduleStatus.FAILED));
		
		return "schedule/viewschedule";
	}
	
}
