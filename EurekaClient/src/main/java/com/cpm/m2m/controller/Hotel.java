package com.cpm.m2m.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Hotel service", description = "Hotel service")
public class Hotel {

	static Logger logger = LoggerFactory.getLogger(Hotel.class);

	@GetMapping(value = "/main")
	@ApiOperation(value = "sendInfo", notes = "sendInfo")
	public String dispo(Model model) {
		Random r = new Random();
		String[] list = { "hotel con id: " + r.nextInt() + "3 habitaciones dobles disponibles",
				"hotel con id: " + r.nextInt() + "2 habitaciones deluxe" };

		String ret = list[r.nextInt(list.length)];
		model.addAttribute("hotels", list);
		return "welcome";
	}

	@GetMapping(value = "/getDispo/{idHotel}")
	public String main(@PathVariable int idHotel, Model model) {
		return "welcome";
	}

}
