package org.systemaudit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemaudit.dao.ScheduleMasterDAO;
import org.systemaudit.model.EnumScheduleStatus;
import org.systemaudit.model.ScheduleMaster;

@Service("ScheduleMasterService")
@Transactional
public class ScheduleMasterServiceImpl implements ScheduleMasterService {
	
	@Autowired
	private ScheduleMasterDAO scheduleMasterDAO;

	public void addScheduleMaster(ScheduleMaster paramObjScheduleMaster) {
		this.scheduleMasterDAO.addScheduleMaster(paramObjScheduleMaster);
	}

	public void updateScheduleMaster(ScheduleMaster paramObjScheduleMaster) {
		this.scheduleMasterDAO.updateScheduleMaster(paramObjScheduleMaster);
	}

	public List<ScheduleMaster> listScheduleMaster() {
		return this.scheduleMasterDAO.listScheduleMaster();
	}

	public List<ScheduleMaster> listScheduleMasterByDeviceIdAndScheduleStatus(int paramIntComputerId, EnumScheduleStatus objEnumScheduleStatus) {
		return this.scheduleMasterDAO.listScheduleMasterByDeviceIdAndScheduleStatus(paramIntComputerId, objEnumScheduleStatus);
	}

	public List<ScheduleMaster> listScheduleMasterByStatus(EnumScheduleStatus paramEnumScheduleStatus) {
		return this.scheduleMasterDAO.listScheduleMasterByStatus(paramEnumScheduleStatus);
	}
	
	public long countSchedulesByStatus(EnumScheduleStatus objEnumScheduleStatus){
		return this.scheduleMasterDAO.countSchedulesByStatus(objEnumScheduleStatus);
	}
	
	public ScheduleMaster getScheduleMasterById(int paramIntId) {
		return this.scheduleMasterDAO.getScheduleMasterById(paramIntId);
	}

	public void removeScheduleMaster(int paramIntId) {
		this.scheduleMasterDAO.removeScheduleMaster(paramIntId);
	}
	
}
