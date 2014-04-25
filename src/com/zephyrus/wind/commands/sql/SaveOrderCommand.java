package com.zephyrus.wind.commands.sql;

import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zephyrus.wind.commands.interfaces.SQLCommand;
import com.zephyrus.wind.dao.interfaces.IServiceLocationDAO;
import com.zephyrus.wind.dao.interfaces.IServiceOrderDAO;
import com.zephyrus.wind.enums.ORDER_STATUS;
import com.zephyrus.wind.enums.ORDER_TYPE;
import com.zephyrus.wind.enums.PAGES;
import com.zephyrus.wind.model.OrderStatus;
import com.zephyrus.wind.model.OrderType;
import com.zephyrus.wind.model.ProductCatalog;
import com.zephyrus.wind.model.ProductCatalogService;
import com.zephyrus.wind.model.ServiceInstance;
import com.zephyrus.wind.model.ServiceLocation;
import com.zephyrus.wind.model.ServiceOrder;
import com.zephyrus.wind.model.User;

public class SaveOrderCommand extends SQLCommand {
	
	private ServiceOrder insertedOrder = null;

	@Override
	protected String doExecute(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		IServiceLocationDAO locationDAO = getOracleDaoFactory().getServiceLocationDAO();
		ServiceLocation location = (ServiceLocation) session.getAttribute("serviceLocation");
		location.setUser(user);
		location = locationDAO.insert(location);
		
		IServiceOrderDAO orderDAO = getOracleDaoFactory().getServiceOrderDAO();
		ProductCatalog service = (ProductCatalog) session.getAttribute("service");
		ServiceOrder order = new ServiceOrder();
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setId(ORDER_STATUS.ENTERING.getId());
		order.setOrderStatus(orderStatus);
		order.setOrderDate(new Date(new java.util.Date().getTime()));
		OrderType orderType = new OrderType();
		orderType.setId(ORDER_TYPE.NEW.getId());
		order.setOrderType(orderType);
		order.setProductCatalog(service);
		order.setServiceLocation(location);
		order.setServiceInstance(new ServiceInstance());
		insertedOrder = orderDAO.insert(order);
		
		session.setAttribute("serviceLocation", null);
		session.setAttribute("service", null);
		session.setAttribute("products", null);
		
		request.setAttribute("message", "Order successfuly saved!");
		return PAGES.MESSAGE_PAGE.getValue();
	}
	
	public ServiceOrder returnOrder(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, Exception{
		execute(request, response);
		return insertedOrder;
	}

}