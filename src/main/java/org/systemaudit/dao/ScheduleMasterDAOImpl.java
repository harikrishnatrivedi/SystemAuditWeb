package org.systemaudit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.systemaudit.model.ScheduleMaster;

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
	public List<ScheduleMaster> listSuccessScheduleMasterByDeviceId(int paramIntComputerId) {
		return getCurrentSession().createCriteria(ScheduleMaster.class).setFetchMode("objDeviceInfo", FetchMode.JOIN)
				.add(Restrictions.eq("objDeviceInfo.id", paramIntComputerId))
				.add(Restrictions.eq("schStatus", "S"))
				.list();
	}

	public List<ScheduleMaster> listScheduleMasterByStatus(String paramStrScheduleMasterStatus) {
		return getCurrentSession().createCriteria(ScheduleMaster.class).setFetchMode("objDeviceInfo", FetchMode.JOIN)
				.add(Restrictions.eq("schStatus", paramStrScheduleMasterStatus))
				.list();
	}
	
	public long countSchedulesByStatus(String paramStrScheduleStatus){
		return (long) getCurrentSession().createCriteria(ScheduleMaster.class).setProjection(Projections.rowCount())
				.add(Restrictions.eq("schStatus", paramStrScheduleStatus)).uniqueResult();
	}
	
	public ScheduleMaster getScheduleMasterByDeviceComputerId(int paramIntComputerId) {

		Criteria criteria = getCurrentSession().createCriteria(ScheduleMaster.class).setFetchMode("objDeviceInfo",
				FetchMode.JOIN);
		criteria.add(Restrictions.eq("objDeviceInfo.id", paramIntComputerId));
		criteria.add(Restrictions.eq("schStatus", "P"));
		return (ScheduleMaster) criteria.uniqueResult();
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
