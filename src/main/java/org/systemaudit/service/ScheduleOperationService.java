package org.systemaudit.service;

import java.util.List;

import org.systemaudit.model.DeviceInfo;

public abstract interface ScheduleOperationService
{
	public abstract void saveAutoRunSchedule(DeviceInfo paramObjDeviceInfo);
  
  
}
