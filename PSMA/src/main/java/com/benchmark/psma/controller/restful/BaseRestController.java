package com.benchmark.psma.controller.restful;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benchmark.psma.controller.BaseController;

@RestController
public class BaseRestController extends BaseController{

	@RequestMapping(value = "/restError")
	public RestError handleGenericError() {
		return new RestError();
	}
}
