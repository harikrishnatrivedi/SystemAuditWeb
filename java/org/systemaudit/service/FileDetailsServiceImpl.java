package org.systemaudit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemaudit.dao.FileDetailsDAO;
import org.systemaudit.model.FileDetails;
import org.systemaudit.model.EnumFileFolderOperationStatus;

@Service("FileDetailsServiceImpl")
@Transactional
public class FileDetailsServiceImpl implements FileDetailsService {
	
	@Autowired
	private FileDetailsDAO fileDetailsDAO;

	public void addFileDetails(FileDetails paramObjFileDetails) {
		this.fileDetailsDAO.addFileDetails(paramObjFileDetails);
	}

	public void updateFileDetails(FileDetails paramObjFileDetails) {
		this.fileDetailsDAO.updateFileDetails(paramObjFileDetails);
	}

	public List<FileDetails> listFileDetails() {
		return this.fileDetailsDAO.listFileDetails();
	}

	public List<FileDetails> listFileDetailsByDeviceInfoId(int paramIntDeviceInfoId){
		return this.fileDetailsDAO.listFileDetailsByDeviceInfoId(paramIntDeviceInfoId);
	}
	
	public List<FileDetails> listFileDetailsByScheduleMasterId(int paramIntScheduleMasterId) {
		return this.fileDetailsDAO.listFileDetailsByScheduleMasterId(paramIntScheduleMasterId);
	}
	
	public List<FileDetails> listFileDetailsByFileFilter(FileDetails objFileDetails) {
		return this.fileDetailsDAO.listFileDetailsByFileFilter(objFileDetails);
	}
	
	public List<FileDetails> listSuspiciousFolderByDeviceId(int paramIntDeviceInfoId){
		return this.fileDetailsDAO.listSuspiciousFolderByDeviceId(paramIntDeviceInfoId);
	}
	
	public int countSuspiciousSystem(){
		return this.fileDetailsDAO.countSuspiciousSystem();
	}
	
	public FileDetails getFileDetailsById(int paramIntFileDetailsId) {
		return this.fileDetailsDAO.getFileDetailsById(paramIntFileDetailsId);
	}

	public void removeFileDetails(int paramIntFileDetailsId) {
		this.fileDetailsDAO.removeFileDetails(paramIntFileDetailsId);
	}
	
	public void updateFileDetailsByOperationRequest(String paramStrComaSeparatedIds, EnumFileFolderOperationStatus enumFileFolderOperationStatusToUpdate, String paramStrUpdatedBy){
		this.fileDetailsDAO.updateFileDetailsByOperationRequest(paramStrComaSeparatedIds, enumFileFolderOperationStatusToUpdate, paramStrUpdatedBy);
	}
	
	public void removeFileDetailsByDeviceInfoId(int paramIntDeviceInfoId) {
		this.fileDetailsDAO.removeFileDetailsByDeviceInfoId(paramIntDeviceInfoId);
	}
	
	public List<FileDetails> getSuspiciousFileDetailsByDeviceInfoIdAndStatus(int paramIntDeviceInfoCompId, EnumFileFolderOperationStatus paramEnumFileFolderOperationStatus){
		return this.fileDetailsDAO.getSuspiciousFileDetailsByDeviceInfoIdAndStatus(paramIntDeviceInfoCompId, paramEnumFileFolderOperationStatus);
	}
	
}
