package com.zephyrus.wind.dao.oracleImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

import com.zephyrus.wind.dao.factory.OracleDAOFactory;
import com.zephyrus.wind.dao.interfaces.IUserDAO;
import com.zephyrus.wind.model.User;
import com.zephyrus.wind.model.UserRole;

public class OracleUserDAO extends OracleDAO<User> implements IUserDAO{
	private static final String TABLE_NAME = "USERS";
    private static final String SQL_SELECT = "SELECT ID, FIRST_NAME, LAST_NAME, " + 
                                      "EMAIL, PASSWORD, REGISTRATION_DATA, STATUS, ROLE_ID FROM " + 
                                       TABLE_NAME + " ";
    private static final String SQL_UPDATE = "UPDATE " + TABLE_NAME + 
                                      " SET FIRST_NAME= ?, LAST_NAME = ?, " + 
                                      "EMAIL = ?, PASSWORD = ?, REGISTRATION_DATA = ?, STATUS = ?, ROLE_ID = ? WHERE " + 
                                      " ID = ?";
    private static final String SQL_INSERT = "BEGIN INSERT INTO " + TABLE_NAME + 
                                      " (FIRST_NAME, LAST_NAME, " + 
                                      "EMAIL, PASSWORD, REGISTRATION_DATA, STATUS, ROLE_ID) " +
                                      "VALUES (?,?,?,?,?,?,?) " + 
                                      "RETURN ROWID INTO ?;END;";
    private static final String SQL_REMOVE = "DELETE FROM " + TABLE_NAME + "WHERE ";
    
    private static final int COLUMN_ID = 1;
    private static final int COLUMN_FIRST_NAME = 2;
    private static final int COLUMN_LAST_NAME= 3;
    private static final int COLUMN_EMAIL = 4;
    private static final int COLUMN_PASSWORD = 5;
    private static final int COLUMN_REGISTRATION_DATA = 6;
    private static final int COLUMN_STATUS = 7;
    private static final int COLUMN_ROLE_ID = 8;

	public OracleUserDAO( Connection connection, OracleDAOFactory daoFactory)
			throws Exception {
		super(User.class, connection, daoFactory);
	}

	@Override
	public void update(User record) throws Exception {
		stmt = connection.prepareStatement(SQL_UPDATE);
    	stmt.setString(1, record.getFirstName());
    	stmt.setString(2, record.getLastName());
    	stmt.setString(3, record.getEmail());
    	stmt.setString(4, record.getPassword());
    	stmt.setDate(5, record.getRegistrationData());
    	stmt.setInt(6, record.getStatus());
    	stmt.setInt(7, record.getRole().getId());
    	stmt.setLong(8, record.getId());
        stmt.executeUpdate();
        stmt.close();
	}

	@Override
	public User insert(User record) throws Exception {
		cs = connection.prepareCall(SQL_INSERT);
    	cs.setString(1, record.getFirstName());
    	cs.setString(2, record.getLastName());
    	cs.setString(3, record.getEmail());
    	cs.setString(4, record.getPassword());
    	cs.setDate(5, record.getRegistrationData());
    	cs.setInt(6, record.getStatus());
    	cs.setInt(7, record.getRole().getId());
    	cs.registerOutParameter(8, OracleTypes.VARCHAR);
        cs.execute();
        String rowId = cs.getString(8);
        cs.close();
		return findByRowId(rowId);
	}

	@Override
	public int remove(User record) throws Exception {
		return removeById((int)record.getId());
	}

	@Override
	protected void fillItem(User item, ResultSet rs) throws Exception {
		item.setId(rs.getInt(COLUMN_ID));
		item.setFirstName(rs.getString(COLUMN_FIRST_NAME));
		item.setLastName(rs.getString(COLUMN_LAST_NAME));
		item.setEmail(rs.getString(COLUMN_EMAIL));
		item.setPassword(rs.getString(COLUMN_PASSWORD));
		item.setRegistrationData(rs.getDate(COLUMN_REGISTRATION_DATA));
		item.setStatus(rs.getInt(COLUMN_STATUS));
		UserRole role = daoFactory.getUserRoleDAO().findById(rs.getInt(COLUMN_ROLE_ID));
		item.setRole(role);
	}
	
	@Override
	protected String getSelect() {
		return SQL_SELECT;
	}

	@Override
	protected String getDelete() {
		return SQL_REMOVE;
	}
	

	@Override
	public ArrayList<User> getUsersByRoleId(int roleId) throws Exception {
		stmt = connection.prepareStatement(SQL_SELECT + "WHERE ROLE_ID = ?");
		stmt.setInt(1, roleId);
		rs = stmt.executeQuery();
		ArrayList<User> users = fetchMultiResults(rs);
		rs.close();
		return users;
	}

	@Override
	public User findByEmail(String email) throws Exception {
		stmt = connection.prepareStatement(SQL_SELECT + "WHERE EMAIL = ?");
		stmt.setString(1, email);
		rs = stmt.executeQuery();
		User user = fetchSingleResult(rs);
		rs.close();
		return user;
	}

}
