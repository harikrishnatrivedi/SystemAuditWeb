package org.systemaudit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.systemaudit.model.EnumFileFolderOperationStatus;
import org.systemaudit.model.FolderOperationRequest;

@Repository("FolderOperationRequestDAO")
public class FolderOperationRequestDAOImpl extends GenericDAOImpl<FolderOperationRequest, Integer> implements FolderOperationRequestDAO {
	
	public void addFolderOperationRequest(FolderOperationRequest paramObjFolderOperationRequest) {
		getCurrentSession().persist(paramObjFolderOperationRequest);
	}
	
	public void updateFolderOperationRequest(FolderOperationRequest paramObjFolderOperationRequest) {
		getCurrentSession().update(paramObjFolderOperationRequest);
	}

	public boolean checkUniqueMoveOrDeleteRequest(FolderOperationRequest objFolderOperationRequest){
		if(getCurrentSession().createCriteria(FolderOperationRequest.class).setFetchMode("objDeviceInfo", FetchMode.JOIN)
				.add(Restrictions.eq("objDeviceInfo.id", objFolderOperationRequest.getObjDeviceInfo().getCompId()))
				.add(Restrictions.eq("foldFullPath", objFolderOperationRequest.getFoldFullPath()))
				.add(Restrictions.eq("foldStatus", objFolderOperationRequest.getFoldStatus()))
				.uniqueResult()==null)
				return true;
			else
				return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<FolderOperationRequest> listFolderOperationRequests(EnumFileFolderOperationStatus paramEnumFileFolderOperationStatus) {
		Criteria criteria=getCurrentSession().createCriteria(FolderOperationRequest.class);
		if(!paramEnumFileFolderOperationStatus.equals(EnumFileFolderOperationStatus.ALL))
			criteria.add(Restrictions.eq("foldStatus", paramEnumFileFolderOperationStatus));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<FolderOperationRequest> listFolderOperationRequestByDeviceInfoId(int paramIntDeviceInfoId,EnumFileFolderOperationStatus paramEnumFileFolderOperationStatus) {
		Criteria criteria = getCurrentSession().createCriteria(FolderOperationRequest.class).
				add(Restrictions.eq("objDeviceInfo.id", paramIntDeviceInfoId));
		if(!paramEnumFileFolderOperationStatus.equals(EnumFileFolderOperationStatus.ALL))
			criteria.add(Restrictions.eq("foldStatus", paramEnumFileFolderOperationStatus));
		return criteria.list();
	}

	public FolderOperationRequest getFolderOperationRequestById(int paramIntId) {
		return (FolderOperationRequest) getCurrentSession().load(FolderOperationRequest.class, new Integer(paramIntId));
	}

}
