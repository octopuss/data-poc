package cz.generali.gef.poc.controller;

import cz.generali.gef.poc.domain.Partner;
import cz.generali.gef.poc.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Ivan Dolezal(T911552) on 3.1.2015.
 *
 * @Author Ivan Dolezal
 */
@Controller
public class IndexController {


	@Autowired
	private PartnerRepository partnerRepository;


	@RequestMapping(value = { "/list", "/" })
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "Partners list");
		mav.addObject("viewName", "list");
		mav.addObject("modelUrl", "listModel");
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/{id}")
	public @ResponseBody Partner find(@PathVariable("id") Long id) {
		return partnerRepository.findOne(id);
	}

	@RequestMapping("/new")
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("partner", new Partner());
		mav.setViewName("new");
		return mav;
	}
}
