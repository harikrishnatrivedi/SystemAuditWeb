package org.systemaudit.controller;

import java.util.Date;
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
import org.systemaudit.model.EmployeeDetails;
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
	
	@Autowired
	private DeviceInfoService objDeviceInfoService;
	
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
	
	@RequestMapping(value = { "/addNewSchedule" }, method = RequestMethod.GET)
	public String addNewScheduleGet(ScheduleMaster objScheduleMaster,ModelMap model, HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";
	
		model.addAttribute("lstObjDeviceInfo", objDeviceInfoService.listDeviceInfo());
		model.addAttribute("scheduleMaster",objScheduleMaster);
		return "schedule/addschedule";
	}
	
	@RequestMapping(value = { "/addNewSchedule" }, method = RequestMethod.POST)
	public String addNewSchedulePost(ScheduleMaster scheduleMaster, BindingResult result, ModelMap model, HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";
	
		if(scheduleMaster.getObjDeviceInfo()==null || scheduleMaster.getObjDeviceInfo().getCompId()==null){
			result.addError(new FieldError("objDeviceInfo.compId","objDeviceInfo.compId","Please select device to create schedule."));
			model.addAttribute("lstObjDeviceInfo", objDeviceInfoService.listDeviceInfo());
			return "schedule/addschedule";
		} else {
			System.out.println("scheduleMaster : "+scheduleMaster+" :::: \n objScheduleMaster.getObjDeviceInfo().getCompId() : "+scheduleMaster.getObjDeviceInfo().getCompId());
			
			List<ScheduleMaster> lstObjScheduleMasterPending=objScheduleMasterService.listScheduleMasterByDeviceIdAndScheduleStatus(scheduleMaster.getObjDeviceInfo().getCompId(), EnumScheduleStatus.PENDING);
			if(lstObjScheduleMasterPending.size()>0){
				result.addError(new FieldError("objDeviceInfo.compId","objDeviceInfo.compId","Schedule for this device is already pending."));
				model.addAttribute("lstObjDeviceInfo", objDeviceInfoService.listDeviceInfo());
				return "schedule/addschedule";
			}else{
				scheduleMaster.setSchStatus(EnumScheduleStatus.PENDING);
				scheduleMaster.setSchScheduledDateTime(new Date());
				scheduleMaster.setObjDeviceInfo(objDeviceInfoService.getDeviceInfoById(scheduleMaster.getObjDeviceInfo().getCompId()));
				scheduleMaster.setSchCreatedBy(((EmployeeDetails)session.getAttribute("empDetails")).getEmpCode());
				objScheduleMasterService.addScheduleMaster(scheduleMaster);
			}
		}
			
		
		return "schedule/viewschedule";
	}
}
