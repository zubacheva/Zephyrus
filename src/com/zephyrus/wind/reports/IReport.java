package com.zephyrus.wind.reports;

import java.io.IOException;
import org.apache.poi.ss.usermodel.Workbook;

public interface IReport {
	public  Workbook convertToExel() throws IOException ;
}
