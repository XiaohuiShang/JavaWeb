package cn.wrs.controller;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wrs.po.WeatherCustom;
import cn.wrs.service.WeatherService;

@Controller
@RequestMapping("/weather")
public class WeatherController {
	
	//@Autowired
	private WeatherService weatherService;

	@Resource(name = "weatherService")
	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}
	
	@RequestMapping("/selectByCity")
	public String selectWeatherByCity(Model model,String name){		
		try {
			if(name == null){
				name = "±±¾©";
			}
			WeatherCustom weatherCustom = weatherService.selectWeatherByCity(name);
			model.addAttribute("weatherCustom", weatherCustom);
			
		} catch (Exception e) {		
			e.printStackTrace();
		}		
		return "weatherList";
	}

	
	
	
}
