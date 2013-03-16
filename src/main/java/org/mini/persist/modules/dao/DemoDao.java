/**
 * 
 */
package org.mini.persist.modules.dao;

import java.util.List;

import org.mini.framework.dao.GenericDao;
import org.mini.model.Demo;

/**
 * @author Administrator
 *
 */
public interface DemoDao extends GenericDao<Demo> {
	List<Demo> queryAll() throws Exception;
	
	Demo queryDemo(Demo demo) throws Exception;
}
