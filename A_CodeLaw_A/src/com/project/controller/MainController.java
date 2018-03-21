package com.project.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.model.ForwardService;
import com.project.service.CommandProcess;

public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Map<String, CommandProcess> commandMap = new HashMap<String, CommandProcess>();

	public void init() throws ServletException {
		String uriCommand = getInitParameter("uriCommand");
		ServletContext sc = getServletContext();
		String uploadDir = sc.getInitParameter("uploadDir");
		String realPath = sc.getRealPath(uploadDir);
		File parentFile = new File(realPath);

		if (!(parentFile.exists() && parentFile.isDirectory())) {
			parentFile.mkdir();
		}

		Properties prop = new Properties();

		FileInputStream fis = null;
		BufferedInputStream bis = null;

		try {
			String propPath = sc.getRealPath(uriCommand);

			fis = new FileInputStream(propPath);
			bis = new BufferedInputStream(fis);

			prop.load(bis);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("메인컨트롤러1");
			// TODO: handle exception
		} finally {
			try {
				if (bis != null)
					bis.close();
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("메인컨트롤러2");
			}
		}
		Iterator<Object> keyIter = prop.keySet().iterator();
		while (keyIter.hasNext()) {
			String cmd = (String) keyIter.next();
			String className = prop.getProperty(cmd);
			try {
				Class<?> commandClass = Class.forName(className);
				CommandProcess service = (CommandProcess) commandClass.newInstance();
				commandMap.put(cmd, service);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("메인컨트롤러3");
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.out.println("메인컨트롤러4");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				System.out.println("메인컨트롤러5");
			}
		}
		sc.setAttribute("uploadDir", uploadDir);
		sc.setAttribute("parentFile", parentFile);
	}

	public void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		System.out.println("uri : " + requestURI + " ctxPath : " + contextPath);
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : " + command);
		CommandProcess service = commandMap.get(command);
		System.out.println("service : " + service);
		ForwardService forward = service.requestProcess(request, response);
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
				System.out.println("리다이렉트");
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
				System.out.println("forward");
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}

}
