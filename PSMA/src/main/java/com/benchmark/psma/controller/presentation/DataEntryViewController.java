package com.benchmark.psma.controller.presentation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.benchmark.psma.dao.model.Event;
import com.benchmark.psma.dao.model.Offer;

@Controller
public class DataEntryViewController  extends BaseViewController{

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public ModelAndView getEvents(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
			List<Event> events = daoService.getEventDaoImpl().findAll();
			model.addObject("events", events);
			model.setViewName("dataEnttry/events");
		return model;

	}
	
	@RequestMapping(value = "/addEventForm**", method = RequestMethod.GET)
	public ModelAndView addEventForm() {
		ModelAndView model = new ModelAndView();
		model.addObject("event", new Event());
		model.setViewName("dataEnttry/addEvent");
		return model;
	}
	
	@RequestMapping(value ="/addEvent", method = RequestMethod.POST)
	public String addEvent(@ModelAttribute("event") Event event, @RequestParam("file") MultipartFile file) throws Exception{
		byte[] image = file.getBytes();
		event.setImage(image);
		daoService.getEventDaoImpl().save(event);
		return "redirect:/events";
	}
	
	@RequestMapping(value = "/editEvent**", method = RequestMethod.GET)
	public ModelAndView editEvent(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		Event event = daoService.getEventDaoImpl().findOne(new Integer(id));
		model.addObject("event", event);
		model.setViewName("dataEnttry/addEvent");
		return model;
	}
	
	@RequestMapping("/deleteEvent")
	public ModelAndView deleteEvent(@RequestParam("id") String id) {
		daoService.getEventDaoImpl().delete(new Integer(id));
		ModelAndView model = new ModelAndView();
		List<Event> events = daoService.getEventDaoImpl().findAll();
		model.addObject("events", events);
		model.setViewName("dataEnttry/evrnts");
		return model;
	}
	
	@RequestMapping(value = "/offers", method = RequestMethod.GET)
	public ModelAndView getOffers(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
			List<Offer> offers = daoService.getOfferDaoImpl().findAll();
			model.addObject("offers", offers);
			model.setViewName("dataEnttry/offers");
		return model;

	}
	
	@RequestMapping(value = "/addOfferForm**", method = RequestMethod.GET)
	public ModelAndView addOfferForm() {
		ModelAndView model = new ModelAndView();
		model.addObject("offer", new Offer());
		model.setViewName("dataEnttry/addOffer");
		return model;
	}
	
	@RequestMapping(value ="/addOffer", method = RequestMethod.POST)
	public String addOffer(@ModelAttribute("offer") Offer offer, @RequestParam("file") MultipartFile file) throws Exception {
		byte[] image = file.getBytes();
		offer.setImage(image);
		daoService.getOfferDaoImpl().save(offer);
		return "redirect:/offers";
	}
	
	@RequestMapping(value = "/editOffer**", method = RequestMethod.GET)
	public ModelAndView editOffer(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		Offer offer = daoService.getOfferDaoImpl().findOne(new Integer(id));
		model.addObject("offer", offer);
		model.setViewName("dataEnttry/addOffer");
		return model;
	}
	
	@RequestMapping("/deleteOffer")
	public ModelAndView deleteOffer(@RequestParam("id") String id) {
		daoService.getOfferDaoImpl().delete(new Integer(id));
		ModelAndView model = new ModelAndView();
		List<Offer> offers = daoService.getOfferDaoImpl().findAll();
		model.addObject("offers", offers);
		model.setViewName("dataEnttry/offers");
		return model;
	}
}
