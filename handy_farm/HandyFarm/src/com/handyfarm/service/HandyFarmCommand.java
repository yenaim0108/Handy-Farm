package com.handyfarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandyFarmCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response);
}
