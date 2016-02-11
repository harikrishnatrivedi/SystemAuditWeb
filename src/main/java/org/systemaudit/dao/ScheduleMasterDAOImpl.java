package org.systemaudit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.systemaudit.model.ScheduleMaster;
import org.systemaudit.model.EnumScheduleStatus;

@Repository("ScheduleMasterDAO")
public class ScheduleMasterDAOImpl extends GenericDAOImpl<ScheduleMaster, Integer> implements ScheduleMasterDAO {
	public void addScheduleMaster(ScheduleMaster paramObjScheduleMaster) {
		getCurrentSession().persist(paramObjScheduleMaster);
	}

	public void updateScheduleMaster(ScheduleMaster paramObjScheduleMaster) {
		getCurrentSession().update(paramObjScheduleMaster);
	}

	@SuppressWarnings("unchecked")
	public List<ScheduleMaster> listScheduleMaster() {
		return getCurrentSession().createQuery("from ScheduleMaster").list();
	}

	@SuppressWarnings("unchecked")
	public List<ScheduleMaster> listScheduleMasterByDeviceIdAndScheduleStatus(int paramIntComputerId, EnumScheduleStatus objEnumScheduleStatus) {
		return getCurrentSession().createCriteria(ScheduleMaster.class).setFetchMode("objDeviceInfo", FetchMode.JOIN)
				.add(Restrictions.eq("objDeviceInfo.id", paramIntComputerId))
				.add(Restrictions.eq("schStatus", objEnumScheduleStatus))
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<ScheduleMaster> listScheduleMasterByStatus(EnumScheduleStatus paramEnumScheduleStatus) {
		return getCurrentSession().createCriteria(ScheduleMaster.class).setFetchMode("objDeviceInfo", FetchMode.JOIN)
				.add(Restrictions.eq("schStatus", paramEnumScheduleStatus))
				.list();
	}
	
	public long countSchedulesByStatus(EnumScheduleStatus objEnumScheduleStatus){
		return (long) getCurrentSession().createCriteria(ScheduleMaster.class).setProjection(Projections.rowCount())
				.add(Restrictions.eq("schStatus", objEnumScheduleStatus)).uniqueResult();
	}
	
	public ScheduleMaster getScheduleMasterById(int paramIntId) {
		return (ScheduleMaster) getCurrentSession().load(ScheduleMaster.class, new Integer(paramIntId));
	}

	public void removeScheduleMaster(int paramIntId) {
		ScheduleMaster ed = (ScheduleMaster) getCurrentSession().load(ScheduleMaster.class, new Integer(paramIntId));
		if (ed != null) {
			getCurrentSession().delete(ed);
		}
	}
}
