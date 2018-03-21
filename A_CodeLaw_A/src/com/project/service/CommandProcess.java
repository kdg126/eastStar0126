package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.model.ForwardService;

public interface CommandProcess {
	public abstract ForwardService requestProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException; 
}
