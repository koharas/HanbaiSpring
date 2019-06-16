package com.dicre.hanbai.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dicre.hanbai.model.Shouhin;
import com.dicre.hanbai.model.ShouhinRepository;


@Controller
public class ShouhinController {

	 @Autowired
	 private ShouhinRepository repository;


	@RequestMapping(value="/slist", method=RequestMethod.GET)
	 public ModelAndView slist(
			 @ModelAttribute("formModel") Shouhin shouhin,
			 ModelAndView mv) {
		List<Shouhin> list = repository.findAll();

		mv.addObject("list",list);

		mv.setViewName("slist");
		 return mv;
	 }

	 @RequestMapping(value="/slist", method=RequestMethod.POST)
	 public String shouhinAdd(
			 @ModelAttribute("formModel") Shouhin shouhin,
			 ModelAndView mv) {

		 repository.save(shouhin);

		 return "redirect:/slist";
	 }


	@RequestMapping(value="/del", method=RequestMethod.GET)
	 public ModelAndView del(
			 @RequestParam("sid")Integer sid,
			 ModelAndView mv) {

		Optional<Shouhin> s = repository.findById(sid);

		mv.addObject("shouhin",s.get());

		mv.setViewName("del");
		 return mv;
	 }

	 @RequestMapping(value="/del", method=RequestMethod.POST)
	 public String shouhinDel(
			 @RequestParam("sid")Integer sid,
			 ModelAndView mv) {

		 Optional<Shouhin> s = repository.findById(sid);
		 repository.delete(s.get());

		 return "redirect:/slist";
	 }

	@RequestMapping(value="/update", method=RequestMethod.GET)
	 public ModelAndView update(
			 @RequestParam("sid")Integer sid,
			 @ModelAttribute("formModel") Shouhin shouhin,
			 ModelAndView mv) {

		Optional<Shouhin> s = repository.findById(sid);
		mv.addObject("formModel",s.get());

		mv.setViewName("update");
		 return mv;
	 }

	 @RequestMapping(value="/update", method=RequestMethod.POST)
	 public String shouhinUpdate(
			 @ModelAttribute("formModel") Shouhin shouhin,
			 ModelAndView mv) {

		 repository.save(shouhin);

		 return "redirect:/slist";
	 }



}
