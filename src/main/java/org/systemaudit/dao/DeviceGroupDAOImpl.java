package org.systemaudit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.systemaudit.model.DeviceGroup;

@Repository("DeviceGroupDAO")
public class DeviceGroupDAOImpl extends GenericDAOImpl<DeviceGroup, Integer> implements DeviceGroupDAO {
	
	public void addDeviceGroup(DeviceGroup paramObjDeviceGroup) {
		getCurrentSession().persist(paramObjDeviceGroup);
	}

	public void updateDeviceGroup(DeviceGroup paramObjDeviceGroup) {
		getCurrentSession().update(paramObjDeviceGroup);
	}

	@SuppressWarnings("unchecked")
	public List<DeviceGroup> listDeviceGroup() {
		return getCurrentSession().createQuery("from DeviceGroup").list();
	}

	public DeviceGroup getDeviceGroupById(int paramIntId) {
		return (DeviceGroup) getCurrentSession().load(DeviceGroup.class, new Integer(paramIntId));
	}

	public void removeDeviceGroup(int paramIntId) {
		DeviceGroup ed = (DeviceGroup) getCurrentSession().load(DeviceGroup.class, new Integer(paramIntId));
		if (ed != null) {
			getCurrentSession().delete(ed);
		}
	}
	
	public DeviceGroup getDeviceGroupByGroupName(String paramStrGroupName) {
		return (DeviceGroup)getCurrentSession().createQuery("from DeviceGroup where grpName= :grpName").setParameter("grpName", paramStrGroupName).uniqueResult();		
	}
	
}
