package org.systemaudit.dao;

import java.util.List;

import org.systemaudit.model.ScheduleMaster;


public abstract interface ScheduleMasterDAO
  extends GenericDAO<ScheduleMaster, Integer>
{
  public abstract void addScheduleMaster(ScheduleMaster paramObjScheduleMaster);
  
  public abstract void updateScheduleMaster(ScheduleMaster paramObjScheduleMaster);
  
  public abstract List<ScheduleMaster> listScheduleMaster();

  public abstract List<ScheduleMaster> listSuccessScheduleMasterByDeviceId(int paramIntComputerId);
  
  public abstract long countSchedulesByStatus(String paramStrScheduleStatus);
  
  public abstract List<ScheduleMaster> listScheduleMasterByStatus(String paramStrScheduleMasterStatus);
  
  public abstract ScheduleMaster getScheduleMasterByDeviceComputerId(int paramIntComputerId);
  
  public abstract ScheduleMaster getScheduleMasterById(int paramIntId);
  
  public abstract void removeScheduleMaster(int paramIntId);
  
}
