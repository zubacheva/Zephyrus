package com.zephyrus.wind.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.zephyrus.wind.dao.factory.OracleDAOFactory;
import com.zephyrus.wind.dao.interfaces.IReportDAO;
import com.zephyrus.wind.reports.rowObjects.RouterUtilRow;

/**
 * This class provides functionality for create and convert "Router util report"
 * @author Kostya Trukhan
 */
public class RouterUtilReport implements IReport{
	
	static String path = "E:\\reports\\"; // REVIEW: hardcode path
	private ArrayList<RouterUtilRow> report = new ArrayList<RouterUtilRow>();
	
	public ArrayList<RouterUtilRow> getReport() {
		return report;
	}
	
	public void setReport(ArrayList<RouterUtilRow> report) {
		this.report = report;
	}
    /**
     * This constructor generate report into private parameter report 
     * @throws If get some trouble with connection to DB
     */
	public RouterUtilReport() throws Exception{
		OracleDAOFactory factory = new OracleDAOFactory();
		try {
			factory.beginConnection();
			IReportDAO dao = factory.getReportDAO();
			setReport(dao.getRouterUtilReport());

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			factory.endConnection();
		}
	}
	
	public Workbook convertToExel()
			throws IOException {
		Workbook workbook = null;
		Row row = null;
		Cell cell = null;
		// Read template file
		FileInputStream template = null;
		try {
			template = new FileInputStream(new File(path+"_RouterUtil.xls"));
		} catch (FileNotFoundException e) {

		}
		workbook = new HSSFWorkbook(template);
		Sheet sheet = workbook.getSheetAt(0);
		// Write data to workbook
		Iterator<RouterUtilRow> iterator = report.iterator();
		RouterUtilRow item = null;
		int rowIndex = 1;
		while (iterator.hasNext()) {
			item = iterator.next();
			row = sheet.createRow(rowIndex++);
			cell = row.createCell(0);
			cell.setCellValue(item.getRouterSN());
			cell = row.createCell(1);
			cell.setCellValue(item.getRouterUtil());
			cell = row.createCell(2);
			cell.setCellValue(item.getCapacity());
		}
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		return workbook;
	}



}
