/**
 * 
 */
package org.mini.persist.modules.dao;

import org.mini.framework.dao.GenericDao;
import org.mini.model.Employee;

/**
 * @author Administrator
 *
 */
public interface EmployeeDao extends GenericDao<Employee> {
	/* 定义一个根据用户名密码查找数据的方法。  */
	Employee query(String loginName, String password) throws Exception;
}
