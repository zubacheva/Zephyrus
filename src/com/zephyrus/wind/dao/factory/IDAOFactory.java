
package com.zephyrus.wind.dao.factory;

import com.zephyrus.wind.dao.interfaces.ICableDAO;
import com.zephyrus.wind.dao.interfaces.ICircuitDAO;
import com.zephyrus.wind.dao.interfaces.IDeviceDAO;
import com.zephyrus.wind.dao.interfaces.IOrderStatusDAO;
import com.zephyrus.wind.dao.interfaces.IOrderTypeDAO;
import com.zephyrus.wind.dao.interfaces.IPortDAO;
import com.zephyrus.wind.dao.interfaces.IProductCatalogDAO;
import com.zephyrus.wind.dao.interfaces.IProviderLocationDAO;
import com.zephyrus.wind.dao.interfaces.IReportDAO;
import com.zephyrus.wind.dao.interfaces.IServiceInstanceDAO;
import com.zephyrus.wind.dao.interfaces.IServiceInstanceStatusDAO;
import com.zephyrus.wind.dao.interfaces.IServiceLocationDAO;
import com.zephyrus.wind.dao.interfaces.IServiceOrderDAO;
import com.zephyrus.wind.dao.interfaces.IServiceTypeDAO;
import com.zephyrus.wind.dao.interfaces.ITaskDAO;
import com.zephyrus.wind.dao.interfaces.ITaskStatusDAO;
import com.zephyrus.wind.dao.interfaces.IUserDAO;
import com.zephyrus.wind.dao.interfaces.IUserRoleDAO;
import com.zephyrus.wind.dao.interfaces.IVSupportInstanceDAO;
import com.zephyrus.wind.dao.interfaces.IVSupportOrderDAO;
																							// REVIEW: documentation expected
public interface IDAOFactory {
																							// REVIEW: documentation on EVERY DAO method expected
   ICableDAO getCableDAO() throws Exception;												// REVIEW: Special DAOException class should be written 
   ICircuitDAO getCircuitDAO() throws Exception; 
   IDeviceDAO getDeviceDAO()  throws Exception;
   IOrderStatusDAO getOrderStatusDAO()  throws Exception;
   IOrderTypeDAO  getOrderTypeDAO()  throws Exception;
   IPortDAO  getPortDAO()  throws Exception;
   IProductCatalogDAO  getProductCatalogDAO()  throws Exception;
   IProviderLocationDAO  getProviderLocationDAO()  throws Exception;
   IServiceInstanceDAO  getServiceInstanceDAO()  throws Exception;
   IServiceInstanceStatusDAO  getServiceInstanceStatusDAO()  throws Exception;
   IServiceLocationDAO  getServiceLocationDAO()  throws Exception;
   IServiceOrderDAO getServiceOrderDAO() throws Exception;
   IServiceTypeDAO getServiceTypeDAO() throws Exception;
   ITaskDAO getTaskDAO() throws Exception;
   ITaskStatusDAO getTaskStatusDAO() throws Exception;
   IUserDAO getUserDAO() throws Exception;
   IUserRoleDAO getUserRoleDAO() throws Exception;
   IVSupportInstanceDAO getVSupportInstanceDAO() throws Exception;
   IVSupportOrderDAO getVSupportOrderDAO() throws Exception;
   IReportDAO getReportDAO() throws Exception;
}
