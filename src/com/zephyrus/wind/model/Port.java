package com.zephyrus.wind.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.zephyrus.wind.dao.interfaces.ICableDAO;
import com.zephyrus.wind.dao.interfaces.IPortDAO;



/**
 * The persistent class for the PORTS database table.
 * 																								// REVIEW: author expected
 */

public class Port implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer id;

	private Device device;

	private Integer portNumber;

	public Port() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Integer getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(Integer portNumber) {
		this.portNumber = portNumber;
	}
	

}
