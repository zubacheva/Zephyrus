package com.zephyrus.wind.commands.nosql;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zephyrus.wind.commands.interfaces.Command;
import com.zephyrus.wind.enums.PAGES;
																									// REVIEW: documentation expected
public class LogoutCommand implements Command {
																									// REVIEW: documentation expected
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.logout();
		request.getSession().invalidate();
		return PAGES.HOME_PAGE.getValue();
	}

}
