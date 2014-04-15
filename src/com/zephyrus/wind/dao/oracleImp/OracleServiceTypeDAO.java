package com.zephyrus.wind.dao.oracleImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zephyrus.wind.dao.factory.OracleDAOFactory;
import com.zephyrus.wind.dao.interfaces.IServiceTypeDAO;
import com.zephyrus.wind.model.ServiceType;

public class OracleServiceTypeDAO extends OracleDAO<ServiceType> implements IServiceTypeDAO {
	private static final String TABLE_NAME = "SERVICE_TYPE";
    private static final String SQL_SELECT = "SELECT ID, SERVICE_TYPE_VALUE " + 
                                      "FROM " + 
                                       TABLE_NAME + " ";
    private static final String SQL_UPDATE = "UPDATE " + TABLE_NAME + 
                                      " SET SERVICE_TYPE_VALUE = ? " + 
                                      " WHERE " + 
                                      " ID = ?";
    private static final String SQL_INSERT = "INSERT INTO " + TABLE_NAME + 
                                      " (SERVICE_TYPE_VALUE) " + 
                                      
                                      "VALUES (?)";
    private static final String SQL_REMOVE = "DELETE FROM " + TABLE_NAME + "WHERE ";
    
    private static final int COLUMN_ID = 1;
    private static final int COLUMN_SERVICE_TYPE = 2;

	public OracleServiceTypeDAO(Connection connection, OracleDAOFactory daoFactory) throws Exception {
		super(ServiceType.class, connection, daoFactory);
	}

	@Override
	public void update(ServiceType record) throws Exception {
		stmt = connection.prepareStatement(SQL_UPDATE);
    	stmt.setString(COLUMN_SERVICE_TYPE, record.getServiceType());  	
    	stmt.setLong(COLUMN_ID, record.getId());
        stmt.executeUpdate();
		
	}

	@Override
	public int insert(ServiceType record) throws Exception {
		stmt = connection.prepareStatement(SQL_INSERT);
    	stmt.setString(COLUMN_SERVICE_TYPE, record.getServiceType());    	
        stmt.executeUpdate();		
		return stmt.executeUpdate();
	}

	@Override
	public int remove(ServiceType record) throws Exception {
		return removeById((int)record.getId());
	}

	@Override
	protected void fillItem(ServiceType item, ResultSet rs) throws SQLException {
		item.setId(rs.getLong(COLUMN_ID));
    	item.setServiceType(rs.getString(COLUMN_SERVICE_TYPE));
		
	}
	
	@Override
	protected String getSelect() {
		return SQL_SELECT;
	}

	@Override
	protected String getDelete() {
		return SQL_REMOVE;
	}

}