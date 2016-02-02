package org.systemaudit.service;

import java.util.List;

import org.systemaudit.model.DeviceGroup;

public abstract interface DeviceGroupService
{
  public abstract void addDeviceGroup(DeviceGroup paramObjDeviceGroup) throws Exception;
  
  public abstract void updateDeviceGroup(DeviceGroup paramObjDeviceGroup);
  
  public abstract List<DeviceGroup> listDeviceGroup();
  
  public abstract DeviceGroup getDeviceGroupById(int paramIntDeviceGroupId);
  
  public abstract void removeDeviceGroup(int paramIntDeviceGroupId);
  
  
}
