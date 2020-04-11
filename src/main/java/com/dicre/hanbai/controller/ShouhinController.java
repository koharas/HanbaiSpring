package com.dicre.hanbai.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dicre.hanbai.model.Shouhin;
import com.dicre.hanbai.model.ShouhinRepository;


@Controller
public class ShouhinController {

	 @Autowired
	 private ShouhinRepository repository;


	 @GetMapping("/slist")
	 public ModelAndView slist( ModelAndView mv) {

		List<Shouhin> list = repository.findAll();

		mv.addObject("list",list);

		mv.setViewName("slist");

		return mv;
	 }

	 @PostMapping("/slist")
	 public ModelAndView shouhinAdd(
			 @RequestParam("sname")String sname,
			 @RequestParam("tanka")int tanka,
			 ModelAndView mv) {

		 Shouhin shouhin = new Shouhin(sname,tanka);

		 repository.save(shouhin);

		 mv.setViewName("redirect:/slist");

		 return mv;
	 }


	@GetMapping("/del")
	 public ModelAndView del(
			 @RequestParam("sid")Integer sid,
			 ModelAndView mv) {

		Optional<Shouhin> s = repository.findById(sid);

		mv.addObject("shouhin",s.get());

		mv.setViewName("del");
		 return mv;
	 }

	 @PostMapping("/del")
	 public String shouhinDel(
			 @RequestParam("sid")Integer sid,
			 ModelAndView mv) {

		 Optional<Shouhin> s = repository.findById(sid);
		 repository.delete(s.get());

		 return "redirect:/slist";
	 }

	@GetMapping("/update")
	 public ModelAndView update(
			 @RequestParam("sid")Integer sid,
			 @ModelAttribute("formModel") Shouhin shouhin,
			 ModelAndView mv) {

		Optional<Shouhin> s = repository.findById(sid);
		mv.addObject("formModel",s.get());

		mv.setViewName("update");
		 return mv;
	 }

	 @PostMapping("/update")
	 public String shouhinUpdate(
			 @ModelAttribute("formModel") Shouhin shouhin,
			 ModelAndView mv) {

		 repository.save(shouhin);

		 return "redirect:/slist";
	 }



}
