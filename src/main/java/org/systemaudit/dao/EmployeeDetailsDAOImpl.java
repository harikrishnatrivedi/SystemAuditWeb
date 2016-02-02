package org.systemaudit.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.systemaudit.model.EmployeeDetails;


@Repository("EmployeeDetailsDAO")
public class EmployeeDetailsDAOImpl
  extends GenericDAOImpl<EmployeeDetails, Integer>
  implements EmployeeDetailsDAO
{
  public void addEmployeeDetails(EmployeeDetails paramObjEmployeeDetails)
  {
	  getCurrentSession().persist(paramObjEmployeeDetails);
  }
  
  public void updateEmployeeDetails(EmployeeDetails paramObjEmployeeDetails)
  {
	  getCurrentSession().update(paramObjEmployeeDetails);
  }
  
  @SuppressWarnings("unchecked")
  public List<EmployeeDetails> listEmployeeDetails()
  {
    return getCurrentSession().createQuery("from EmployeeDetails").list();
  }
  
  public EmployeeDetails getEmployeeDetailsById(int paramIntId)
  {
	  return (EmployeeDetails)getCurrentSession().load(EmployeeDetails.class, new Integer(paramIntId));
  }
  
  public void removeEmployeeDetails(int paramIntId)
  {
	EmployeeDetails ed = (EmployeeDetails)getCurrentSession().load(EmployeeDetails.class, new Integer(paramIntId));
    if (ed != null) {
    	getCurrentSession().delete(ed);
    }
  }
  
  public EmployeeDetails getEmployeeDetailsByUserPassword(String paramStrLoginName, String paramStrPassword) {
		
		Query query = getCurrentSession().createQuery("from EmployeeDetails where empLoginName = :empLoginName AND empPassword = :empPassword");
		 query.setParameter("empLoginName", paramStrLoginName);
		 query.setParameter("empPassword", paramStrPassword);
		 return (EmployeeDetails)query.uniqueResult();
	}
  
}
