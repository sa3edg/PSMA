package com.benchmark.psma.controller.presentation;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.benchmark.psma.controller.BaseController;

public abstract class BaseViewController extends BaseController{
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat(
				"dd/MM/yyyy"), true);
		binder.registerCustomEditor(Date.class, editor);
	}
}
