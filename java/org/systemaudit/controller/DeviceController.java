package org.systemaudit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.systemaudit.model.DeviceInfo;
import org.systemaudit.service.DeviceInfoService;

@Controller
public class DeviceController {

	@Autowired
	private DeviceInfoService objDeviceInfoService;

	@RequestMapping(value = { "/viewDevices" }, method = RequestMethod.GET)
	public String viewGroupGet(ModelMap modelMap, HttpSession session) {

		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";

		try {
			List<DeviceInfo> lstObjDeviceInfo = objDeviceInfoService.listDeviceInfo();
			modelMap.addAttribute("lstObjDeviceInfo", lstObjDeviceInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "device/viewdevices";
	}
	
	@RequestMapping(value = { "/viewSuspiciousDevices" }, method = RequestMethod.GET)
	public String viewSuspiciousDevicesGet(ModelMap modelMap, HttpSession session) {

		if (session.getAttribute("empDetails") == null)
			return "redirect:/login";
		
		try {
			List<DeviceInfo> lstObjDeviceInfo = objDeviceInfoService.listSuspiciousSytemByLatestSchedule();
			System.out.println("lstObjDeviceInfo ::: "+lstObjDeviceInfo);
			modelMap.addAttribute("lstObjDeviceInfo", lstObjDeviceInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "device/viewdevices";
	}

}
