package com.zephyrus.wind.managers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.zephyrus.wind.commands.interfaces.Command;
import com.zephyrus.wind.commands.nosql.HomeCommand;
import com.zephyrus.wind.commands.nosql.LoginCommand;
import com.zephyrus.wind.commands.nosql.LogoutCommand;
import com.zephyrus.wind.commands.nosql.MappingCommand;
import com.zephyrus.wind.commands.nosql.NoCommand;
import com.zephyrus.wind.commands.sql.AdminCommand;
import com.zephyrus.wind.commands.sql.RegisterCommand;

public class CommandManager {

	HashMap<String, Command> commands = new HashMap<String, Command>();

	private static final String HOME_COMMAND = "home";
	private static final String ADMIN_COMMAND = "admin";
	private static final String LOGIN_COMMAND = "login";
	private static final String LOGOUT_COMMAND = "logout";
	private static final String MAPPING_COMMAND = "mapping";
	private static final String REGISTER_COMMAND = "register";

	public CommandManager() {

		// filling the table with commands
		commands.put(HOME_COMMAND, new HomeCommand());
		commands.put(ADMIN_COMMAND, new AdminCommand());
		commands.put(LOGIN_COMMAND, new LoginCommand());
		commands.put(LOGOUT_COMMAND, new LogoutCommand());
		commands.put(MAPPING_COMMAND, new MappingCommand());
		commands.put(REGISTER_COMMAND, new RegisterCommand());

	}

	public Command getCommand(HttpServletRequest request) {

		String action = null;
		// geting command out if request
		if (request.getParameter("command") != null) {
			action = request.getParameter("command");
		} else {
			action = request.getServletPath();
			action = action.substring(1);
		}
		// to know what action was performed
		request.getSession().setAttribute("view", action);
		// receiving the object of Command
		return getCommand(action);
	}

	public Command getCommand(String action) {
		Command command = commands.get(action);
		if (command == null) {
			// if command doesn't exist
			command = new NoCommand();
		}
		return command;
	}

}
