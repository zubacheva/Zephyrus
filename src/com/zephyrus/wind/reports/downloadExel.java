package com.zephyrus.wind.reports;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * Servlet implementation class downloadExel
 */
@WebServlet("/downloadExel")
public class downloadExel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public downloadExel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/vnd.ms-excel");
		Workbook wb = null;
		if(request.getSession().getAttribute("routerUtil")!=null){
			RouterUtilReport report = (RouterUtilReport) request.getSession().getAttribute("routerUtil");
			wb = report.convertToExel();
		}
		if(request.getSession().getAttribute("disconnRepo")!=null){
			DisconnectOrdersPerPeriodReport report = (DisconnectOrdersPerPeriodReport) request.getSession().getAttribute("routerUtil");
			wb = report.convertToExel();
		}
		if(request.getSession().getAttribute("newRepo")!=null){
			NewOrdersPerPeriodReport report = (NewOrdersPerPeriodReport) request.getSession().getAttribute("routerUtil");
			wb = report.convertToExel();
		}
		if(request.getSession().getAttribute("profitReport")!=null){
			ProfitabilityByMonthReport report = (ProfitabilityByMonthReport) request.getSession().getAttribute("routerUtil");
			wb = report.convertToExel();
		}
		if(request.getSession().getAttribute("profitRouter")!=null){
			MostProfitableRouterReport report = (MostProfitableRouterReport) request.getSession().getAttribute("routerUtil");
			wb = report.convertToExel();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
