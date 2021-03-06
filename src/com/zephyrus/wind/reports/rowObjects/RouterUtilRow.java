package com.zephyrus.wind.reports.rowObjects;
/**
 * This class needed to save data for reports row
 * @author Kostya Trukhan
 */		
public class RouterUtilRow {
	private String routerSN;
	private double capacity;
	private double routerUtil;

	public String getRouterSN() {
		return routerSN;
	}

	public void setRouterSN(String routerSN) {
		this.routerSN = routerSN;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public double getRouterUtil() {
		return routerUtil;
	}

	public void setRouterUtil(double routerUtil) {
		this.routerUtil = routerUtil;
	}
}
