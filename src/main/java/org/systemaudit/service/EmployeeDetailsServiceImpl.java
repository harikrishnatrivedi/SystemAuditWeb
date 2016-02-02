package org.systemaudit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemaudit.dao.EmployeeDetailsDAO;
import org.systemaudit.model.EmployeeDetails;;

@Service("EmployeeDetailsServiceImpl")
@Transactional
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {
	
	@Autowired
	private EmployeeDetailsDAO objEmployeeDetailsDAO;

	public void addEmployeeDetails(EmployeeDetails paramObjEmployeeDetails) throws Exception {
		try {
			this.objEmployeeDetailsDAO.addEmployeeDetails(paramObjEmployeeDetails);
		}catch(Exception ex){
			throw ex;
		}
	}
	public void updateEmployeeDetails(EmployeeDetails paramObjEmployeeDetails) {
		this.objEmployeeDetailsDAO.updateEmployeeDetails(paramObjEmployeeDetails);
	}

	public List<EmployeeDetails> listEmployeeDetails() {
		return this.objEmployeeDetailsDAO.listEmployeeDetails();
	}

	public EmployeeDetails getEmployeeDetailsById(int paramIntEmployeeDetailsId) {
		return this.objEmployeeDetailsDAO.getEmployeeDetailsById(paramIntEmployeeDetailsId);
	}

	public void removeEmployeeDetails(int paramIntEmployeeDetailsId) {
		this.objEmployeeDetailsDAO.removeEmployeeDetails(paramIntEmployeeDetailsId);
	}
	
	public EmployeeDetails getEmployeeDetailsByUserPassword(String paramStrLoginName, String paramStrPassword) {
		return this.objEmployeeDetailsDAO.getEmployeeDetailsByUserPassword(paramStrLoginName, paramStrPassword);
	}
	
}
