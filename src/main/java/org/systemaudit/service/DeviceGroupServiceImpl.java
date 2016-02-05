package org.systemaudit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemaudit.dao.DeviceGroupDAO;
import org.systemaudit.model.DeviceGroup;


@Transactional
@Service("DeviceGroupService")
public class DeviceGroupServiceImpl implements DeviceGroupService {
	
	@Autowired
	private DeviceGroupDAO objDeviceGroupDAO;

	public void addDeviceGroup(DeviceGroup paramObjDeviceGroup) throws Exception {
		try {
			this.objDeviceGroupDAO.addDeviceGroup(paramObjDeviceGroup);
		}catch(Exception ex){
			throw ex;
		}
	}

	public void updateDeviceGroup(DeviceGroup paramObjDeviceGroup) {
		this.objDeviceGroupDAO.updateDeviceGroup(paramObjDeviceGroup);
	}

	public List<DeviceGroup> listDeviceGroup() {
		return this.objDeviceGroupDAO.listDeviceGroup();
	}
	
	public DeviceGroup getDeviceGroupById(int paramIntDeviceGroupId) {
		return this.objDeviceGroupDAO.getDeviceGroupById(paramIntDeviceGroupId);
	}

	public void removeDeviceGroup(int paramIntDeviceGroupId) {
		this.objDeviceGroupDAO.removeDeviceGroup(paramIntDeviceGroupId);
	}

	public DeviceGroup getDeviceGroupByGroupName(String paramStrGroupName) {
		return objDeviceGroupDAO.getDeviceGroupByGroupName(paramStrGroupName);
	}
	
}
