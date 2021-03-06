package com.zephyrus.wind.dao.oracleImp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zephyrus.wind.dao.factory.OracleDAOFactory;

public abstract class OracleDAO<T> {
    
    protected Class<T> itemClass;
    protected Connection connection;
    protected PreparedStatement stmt = null;
    protected CallableStatement cs = null;
    protected ResultSet rs = null;
    protected OracleDAOFactory daoFactory;
    
    public OracleDAO (Class<T> itemClass, Connection connection, OracleDAOFactory daoFactory) throws Exception{
        this.itemClass = itemClass;
        if(connection == null) throw new Exception("No connection found!");
        this.connection = connection;
        this.daoFactory = daoFactory;
    }
    
    protected T fetchSingleResult(ResultSet rs) 
            throws SQLException, 
            InstantiationException,
            IllegalAccessException, Exception{
        if (rs.next()) {
            T item = itemClass.newInstance();
            fillItem(item, rs);
            return item;
        } else {
        	rs.close();
        	if(stmt != null)
        		stmt.close();
        	if(cs != null)
        		cs.close();
            return null;
        }

    }

    protected ArrayList<T> fetchMultiResults(ResultSet rs)
            throws SQLException,
            InstantiationException,
            IllegalAccessException, Exception{
        ArrayList<T> resultList = new ArrayList<T>();
        T item = null;
        while ((item = fetchSingleResult(rs)) != null) 
            resultList.add(item);
        
        return resultList;
    }
    
    protected abstract void fillItem(T item, ResultSet rs) throws SQLException, Exception;
    protected abstract String getSelect();
    protected abstract String getDelete();
    
    public ArrayList<T> findAll() throws Exception {
        stmt = connection.prepareStatement(getSelect());
        rs = stmt.executeQuery();
        return fetchMultiResults(rs);
    }

    public T findById(int id) throws Exception {
        stmt = connection.prepareStatement(getSelect() + "WHERE ID=?");
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        return fetchSingleResult(rs);
    }
    
    public T findByRowId(String id) throws Exception {
        stmt = connection.prepareStatement(getSelect() + "WHERE ROWID=?");
        stmt.setString(1, id);
        rs = stmt.executeQuery();
        return fetchSingleResult(rs);
    }

    public int removeById(int id) throws Exception {
        stmt = connection.prepareStatement(getDelete() + "ID=?");
        stmt.setInt(1, id);
        int res = stmt.executeUpdate();
        stmt.close();
        return res;
    }
}

