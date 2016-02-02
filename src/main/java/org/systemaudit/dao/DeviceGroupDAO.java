package org.systemaudit.dao;

import java.util.List;

import org.systemaudit.model.DeviceGroup;


public abstract interface DeviceGroupDAO
  extends GenericDAO<DeviceGroup, Integer>
{
  public abstract void addDeviceGroup(DeviceGroup paramObjDeviceGroup);
  
  public abstract void updateDeviceGroup(DeviceGroup paramObjDeviceGroup);
  
  public abstract List<DeviceGroup> listDeviceGroup();
  
  public abstract DeviceGroup getDeviceGroupById(int paramIntId);

  public abstract void removeDeviceGroup(int paramIntId);
  
}
