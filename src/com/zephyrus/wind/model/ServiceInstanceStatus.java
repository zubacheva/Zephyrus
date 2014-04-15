package com.zephyrus.wind.model;

import java.io.Serializable;


/**
 * The persistent class for the SERVICE_INSTANCE_STATUS database table.
 * 
 */

public class ServiceInstanceStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;


	private String servInstanceStatusValue;

	public ServiceInstanceStatus() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getServInstanceStatusValue() {
		return this.servInstanceStatusValue;
	}

	public void setServInstanceStatusValue(String servInstanceStatusValue) {
		this.servInstanceStatusValue = servInstanceStatusValue;
	}

}