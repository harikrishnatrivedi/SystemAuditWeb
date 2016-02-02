package org.systemaudit.service;

import java.util.List;

import org.systemaudit.model.EmployeeDetails;

public abstract interface EmployeeDetailsService
{
  
	public abstract void addEmployeeDetails(EmployeeDetails paramObjEmployeeDetails) throws Exception;
  
  public abstract void updateEmployeeDetails(EmployeeDetails paramObjEmployeeDetails);
  
  public abstract List<EmployeeDetails> listEmployeeDetails();
  
  public abstract EmployeeDetails getEmployeeDetailsById(int paramIntEmployeeDetailsId);
  
  public abstract void removeEmployeeDetails(int paramIntEmployeeDetailsId);
  
  public abstract EmployeeDetails getEmployeeDetailsByUserPassword(String paramStrLoginName, String paramStrPassword);
}
