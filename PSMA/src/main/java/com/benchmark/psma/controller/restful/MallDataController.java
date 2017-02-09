package com.benchmark.psma.controller.restful;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.benchmark.psma.dao.model.Event;
import com.benchmark.psma.dao.model.Offer;

@RestController
@RequestMapping(value = "/rest")
public class MallDataController extends BaseRestController{
	
	
	@RequestMapping(value = "/GetOffers", method = RequestMethod.GET, consumes="application/json", headers = {"content-type=application/json"})
	public List<Offer> getOffers () throws Exception{
	    return daoService.getOfferDaoImpl().getValidOffers();
	}
	
	@RequestMapping(value = "/GetEvents", method = RequestMethod.GET, consumes="application/json", headers = {"content-type=application/json"})
	public List<Event> getEvents () throws Exception{
	    return daoService.getEventDaoImpl().getValidEvents();
	}
	
	@RequestMapping(value = "/ShowPoints", method = RequestMethod.GET, consumes="application/json", headers = {"content-type=application/json"})
	public List<Event> getPoints () throws Exception{
	    return daoService.getEventDaoImpl().getValidEvents();
	}
}
