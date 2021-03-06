package com.dicre.hanbai.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dicre.hanbai.model.Shouhin;
import com.dicre.hanbai.model.ShouhinRepository;
import com.dicre.hanbai.model.Uriage;
import com.dicre.hanbai.model.UriageRepository;


@Controller
public class UriageController {

	 @Autowired
	 private ShouhinRepository srepository;

	 @Autowired
	 private UriageRepository urepository;


	@GetMapping("/uriage")
	 public ModelAndView uriage(
			 @RequestParam("sid")Integer sid,
			 ModelAndView mv) {

		Optional<Shouhin> s = srepository.findById(sid);
		List<Uriage> list = urepository.findBySid(sid);

		mv.addObject("shouhin",s.get());
		mv.addObject("list",list);

		mv.setViewName("uriage");
		 return mv;
	 }

	@PostMapping("/uriage")
	 public String uriageAdd(
			 @RequestParam("sid")Integer sid,
			 @RequestParam("kosu")Integer kosu,
			 ModelAndView mv) {

		urepository.save( new Uriage(sid,kosu));

		return "redirect:/uriage?sid=" + sid;
	 }


}
