package org.mini.persist.modules.dao.impl;

import java.util.List;

import org.mini.framework.dao.impl.GenericDAOImpl;
import org.mini.model.Demo;
import org.mini.persist.modules.dao.DemoDao;
import org.springframework.stereotype.Repository;

@Repository
public class DemoDaoImpl extends GenericDAOImpl<Demo> implements DemoDao {
	private final String QUERY_DEMO = "queryDemo";
	
	@Override
	public List<Demo> queryAll() throws Exception {
		return super.queryAll(null);
	}

	@Override
	public Demo queryDemo(Demo demo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("queryDemo被调用了");
		return super.query(QUERY_DEMO, demo);
	}
	
}
