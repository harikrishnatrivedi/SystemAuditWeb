package org.systemaudit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.systemaudit.model.DeviceInfo;
import org.systemaudit.model.EnumFileFolderOperationStatus;
import org.systemaudit.model.EnumScheduleStatus;

@Repository("DeviceInfoDAOImpl")
public class DeviceInfoDAOImpl extends GenericDAOImpl<DeviceInfo, Integer> implements DeviceInfoDAO {
	public void addDeviceInfo(DeviceInfo paramObjDeviceInfo) {
		getCurrentSession().persist(paramObjDeviceInfo);
	}

	public void updateDeviceInfo(DeviceInfo paramObjDeviceInfo) {
		getCurrentSession().update(paramObjDeviceInfo);
	}

	@SuppressWarnings("unchecked")
	public List<DeviceInfo> listDeviceInfo() {
		return getCurrentSession().createQuery("from DeviceInfo").list();
	}

	public long countTotalDevice() {
		return (long) getCurrentSession().createCriteria(DeviceInfo.class).setProjection(Projections.rowCount())
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<DeviceInfo> listSuspiciousSytemByLatestSchedule() {

		return getCurrentSession()
				.createCriteria(DeviceInfo.class,
						"DeviceInfo")
				.createAlias("lstObjFileDetails", "FileDetails")
				.add(Restrictions.eq("FileDetails.fileStatus", EnumFileFolderOperationStatus.SUSPICIOUS))
				.setProjection(Projections.projectionList()
						.add(Projections.groupProperty("compName").as("compName"))
						.add(Projections.groupProperty("compUserName").as("compUserName"))
						.add(Projections.groupProperty("compOsName").as("compOsName"))
						.add(Projections.groupProperty("compProcessorType").as("compProcessorType"))
						//.add(Projections.groupProperty("objDeviceGroup"))
					).setResultTransformer(Transformers.aliasToBean(DeviceInfo.class))
				.list();
	}

	public DeviceInfo getDeviceInfoByDeviceComputerName(String paramStringComputerName) {
		return (DeviceInfo) getCurrentSession().createQuery("from DeviceInfo where compName = :compName")
				.setParameter("compName", paramStringComputerName).uniqueResult();
	}

	public DeviceInfo getDeviceInfoById(int paramIntId) {
		return (DeviceInfo) getCurrentSession().load(DeviceInfo.class, new Integer(paramIntId));
	}

	public void removeDeviceInfo(int paramIntId) {
		DeviceInfo ed = (DeviceInfo) getCurrentSession().load(DeviceInfo.class, new Integer(paramIntId));
		if (ed != null) {
			getCurrentSession().delete(ed);
		}
	}
}
