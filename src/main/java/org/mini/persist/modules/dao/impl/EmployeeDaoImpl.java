/**
 * 
 */
package org.mini.persist.modules.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.mini.framework.dao.impl.GenericDAOImpl;
import org.mini.model.Employee;
import org.mini.persist.modules.dao.EmployeeDao;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 *
 */
/* 这个也没什么好解释，是为了spring的ioc，如果要问为什么，去找spring的作者。 */
@Repository
public class EmployeeDaoImpl extends GenericDAOImpl<Employee> implements EmployeeDao {
	
	//这里添加变量与配置文件相关对应
	private final String QUERY_EMPLOYEE = "queryEmployee";
	private final String QUERY_EMPLOYEE_SQL = "queryEmployeeSql";

	@Override
	public Employee query(String loginName, String password) throws Exception {
		// TODO Auto-generated method stub
		// 事实上具体你可以参考网上的学习，我只是就其维护性方面分了两大类出来
		// http://www.cnblogs.com/colder/archive/2012/02/28/2371887.html (多上网了解是个不错学习方式)
		
		// 第一种，硬编码
		/* 第一种方式开始  */
		//编写hibernate特有的hql语句，其中不同也就是它会根据实体类名找到相关联的表名,比如：Employee帮你替换成sys_employee,然后补齐语句。
		//其中带冒号的为参数，比如:loginName和:password
		String hql = "from Employee where login_name=:loginName and login_password=:password";
		Query query = getSession().createQuery(hql);
		
		//如果你实在不习惯，那么你可以编写原生态的sql语句。
		//只是将调用的方法createQuery改成createSQLQuery即可，结果是一样的。
		//也不要问为什么，这就是hibernate的牛逼之处了
		//String sql = "select * from sys_employee where login_name=:loginName and login_password=:password";
		//Query query = getSession().createSQLQuery(sql).addEntity(entityClass);
		
		query.setString("loginName", loginName);
		query.setString("password", password);
		
		List<Employee> list  = (List<Employee>)query.list();
		
		//Criteria criteria = getSession().createCriteria(entityClass);
		
		//这里要注意的地方是这里添加一定是类的属性名不是数据库字段名，hibernate会自己去查找转换的。
		//criteria.add(Restrictions.eq("loginName", loginName));
		//criteria.add(Restrictions.eq("password", password));
		
		//List<Employee> list  = (List<Employee>)criteria.list();
		
		if(list.size() < 1)
			return null;
		
		return list.get(0);
		/* 第一种方式结束  */
		
		/* 第二种方式开始  */
		// 使用命名查询的方式+配置文件
		
		//直接通过配置名取得，hibernate会通过节点的类型自己判断是hql还是sql的语句
		/*
		Query query = getSession().getNamedQuery(QUERY_EMPLOYEE_SQL);
		//Query query = getSession().getNamedQuery(QUERY_EMPLOYEE);
		
		//添加参数
		query.setString("loginName", loginName);
		query.setString("password", password);
		
		List<Employee> list  = (List<Employee>)query.list();
		
		if(list.size() < 1)
			return null;
		
		return list.get(0);
		*/
		
		//事实上如果你在配置文件添加了我实现一个配置的话，我可以将读取调用配置然后帮你完成参数的赋值。
		/*
		Employee employee = new Employee();
		employee.setLoginName(loginName);
		employee.setPassword(password);
		return super.query(QUERY_EMPLOYEE, employee);
		*/
		/* 第二种方式结束  */
	}

}
