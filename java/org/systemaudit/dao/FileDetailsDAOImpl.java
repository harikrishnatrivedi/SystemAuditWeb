package org.systemaudit.dao;

import java.util.List;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.systemaudit.model.FileDetails;
import org.systemaudit.model.FileDetailsExcludeGoodView;
import org.systemaudit.model.ScheduleMaster;
import org.systemaudit.model.EnumFileFolderOperationStatus;
import org.systemaudit.model.EnumScheduleStatus;

@Repository("FileDetailsDAO")
public class FileDetailsDAOImpl extends GenericDAOImpl<FileDetails, Integer> implements FileDetailsDAO {
	public void addFileDetails(FileDetails paramObjFileDetails) {
		getCurrentSession().persist(paramObjFileDetails);
	}

	public void updateFileDetails(FileDetails paramObjFileDetails) {
		getCurrentSession().update(paramObjFileDetails);
	}

	@SuppressWarnings("unchecked")
	public List<FileDetails> listFileDetails() {
		return getCurrentSession().createQuery("from FileDetails").list();
	}

	@SuppressWarnings("unchecked")
	public List<FileDetails> listFileDetailsByDeviceInfoId(int paramIntDeviceInfoId) {
		return getCurrentSession().createQuery("from FileDetails where objDeviceInfo.compId= :compId")
				.setParameter("compId", paramIntDeviceInfoId).list();
	}

	@SuppressWarnings("unchecked")
	public List<FileDetails> listFileDetailsByScheduleMasterId(int paramIntScheduleId) {
		return getCurrentSession().createQuery("from FileDetails where objScheduleMaster.schId= :schId")
				.setParameter("schId", paramIntScheduleId).list();
	}

	@SuppressWarnings("unchecked")
	public List<FileDetails> listFileDetailsByFileFilter(FileDetails objFileDetails) {
		Criteria criteria;
		if(objFileDetails.getFileStatus()!=EnumFileFolderOperationStatus.ALL && objFileDetails.getFileStatus()!=EnumFileFolderOperationStatus.GOOD)
			criteria = getCurrentSession().createCriteria(FileDetailsExcludeGoodView.class, "FileDetails");
		else
			criteria = getCurrentSession().createCriteria(FileDetails.class, "FileDetails");
				criteria.createAlias("objDeviceInfo", "objDeviceInfo")
				.createAlias("objScheduleMaster", "objScheduleMaster")
				.add(Restrictions.eq("objDeviceInfo.compId", objFileDetails.getObjDeviceInfo().getCompId()))
				.add(Restrictions.eq("objScheduleMaster.schId", objFileDetails.getObjScheduleMaster().getSchId()))
				.add(Restrictions.eq("objScheduleMaster.schStatus", EnumScheduleStatus.SUCCESS));
		if(objFileDetails.getFileStatus()!=EnumFileFolderOperationStatus.ALL)
				criteria.add(Restrictions.eq("fileStatus", objFileDetails.getFileStatus()));
		if (objFileDetails.getFileName() != null && !objFileDetails.getFileName().isEmpty())
			criteria.add(Restrictions.ilike("fileName", objFileDetails.getFileName(), MatchMode.ANYWHERE));

		if (objFileDetails.getFileDrive() != null && !objFileDetails.getFileDrive().isEmpty())
			criteria.add(Restrictions.ilike("fileDrive", objFileDetails.getFileDrive(), MatchMode.ANYWHERE));

		if (objFileDetails.getFileFullPath() != null && !objFileDetails.getFileFullPath().isEmpty())
			criteria.add(Restrictions.ilike("fileFullPath",
					objFileDetails.getFileFullPath().replaceAll("\\\\", "\\\\\\\\"), MatchMode.ANYWHERE));
		
		
		if (objFileDetails.getFileExtension() != null && !objFileDetails.getFileExtension().isEmpty()){
			Disjunction objDisjunction = Restrictions.disjunction();
			for(String extension : objFileDetails.getFileExtension().split(","))
				objDisjunction.add(Restrictions.disjunction().add(Restrictions.ilike("fileExtension", extension,MatchMode.ANYWHERE)));
			criteria.add(objDisjunction);
		}
		
			//criteria.add(Restrictions.ilike("fileExtension", objFileDetails.getFileExtension(), MatchMode.ANYWHERE));
		System.out.println(criteria.toString());

		return criteria.list();

	}
	
	@SuppressWarnings("unchecked")
	public List<FileDetails> listSuspiciousFolderByDeviceId(int paramIntDeviceInfoId){
		
		return getCurrentSession().createCriteria(FileDetailsExcludeGoodView.class, "FileDetails")
				.createAlias("objDeviceInfo", "objDeviceInfo")
				.add(Restrictions.eq("objDeviceInfo.compId", paramIntDeviceInfoId))
				.add(Restrictions.eq("fileStatus", EnumFileFolderOperationStatus.SUSPICIOUS))
				.setProjection(Projections.groupProperty("fileStatus").as("fileStatus"))
				.setProjection(Projections.groupProperty("fileFolderPath").as("fileFolderPath"))
				.setProjection(Projections.groupProperty("objDeviceInfo").as("objDeviceInfo"))
				.setProjection(Projections.count("fileStatus")).setResultTransformer(Transformers.aliasToBean(FileDetails.class)).list();
	}
	
	public void updateFileDetailsByOperationRequest(String paramStrComaSeparatedIds, EnumFileFolderOperationStatus enumFileFolderOperationStatusToUpdate, String paramStrUpdatedBy){
		Query query=getCurrentSession().createQuery("update FileDetails set fileStatus = :fileStatus, fileUpdatedBy = :fileUpdatedBy where fileId in ("+paramStrComaSeparatedIds+")");
		query.setParameter("fileStatus", enumFileFolderOperationStatusToUpdate);
		query.setParameter("fileUpdatedBy", paramStrUpdatedBy);
		query.executeUpdate();
		
		query=getCurrentSession().createQuery("update FileDetailsExcludeGoodView set fileStatus = :fileStatus, fileUpdatedBy = :fileUpdatedBy where fileId in ("+paramStrComaSeparatedIds+")");
		query.setParameter("fileStatus", enumFileFolderOperationStatusToUpdate);
		query.setParameter("fileUpdatedBy", paramStrUpdatedBy);
		query.executeUpdate();
	}

	public int countSuspiciousSystem(){
		return (int)getCurrentSession().createCriteria(FileDetailsExcludeGoodView.class)
		.add(Restrictions.eq("fileStatus", EnumFileFolderOperationStatus.SUSPICIOUS))
		.setProjection(Projections.groupProperty("objDeviceInfo.id")).list().size();
	}

	public FileDetails getFileDetailsById(int paramIntId) {
		return (FileDetails) getCurrentSession().load(FileDetails.class, new Integer(paramIntId));
	}

	@SuppressWarnings("unchecked")
	public List<FileDetails> getSuspiciousFileDetailsByDeviceInfoIdAndStatus(int paramIntDeviceInfoId,
			EnumFileFolderOperationStatus paramEnumFileFolderOperationStatus) {
		return getCurrentSession()
				.createQuery("from FileDetailsExcludeGoodView where objDeviceInfo.compId= :compId and fileStatus= :fileStatus")
				.setParameter("compId", paramIntDeviceInfoId)
				.setParameter("fileStatus", paramEnumFileFolderOperationStatus).list();
	}

	public void removeFileDetails(int paramIntId) {
		FileDetails ed = (FileDetails) getCurrentSession().load(FileDetails.class, new Integer(paramIntId));
		if (ed != null) {
			getCurrentSession().delete(ed);
		}
	}

	public void removeFileDetailsByDeviceInfoId(int paramIntDeviceInfoId) {
		Query query = getCurrentSession().createQuery("delete FileDetails where objDeviceInfo.compId= :compId");
		query.setInteger("compId", paramIntDeviceInfoId);
		query.executeUpdate();
	}
}
