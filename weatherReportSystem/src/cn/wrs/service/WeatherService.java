package cn.wrs.service;


import org.springframework.beans.factory.annotation.Autowired;

import cn.wrs.mapper.WeatherMapperCustom;
import cn.wrs.po.WeatherCustom;

public class WeatherService {
	@Autowired
	private WeatherMapperCustom weatherMapperCustom;
	
	public WeatherCustom selectWeatherByCity(String name) throws Exception{
		WeatherCustom weatherCustom = weatherMapperCustom.selectWeatherByCity(name);
		if(weatherCustom == null){
			weatherCustom = new WeatherCustom();
		}
		weatherCustom.setName(name);
		return weatherCustom;
	}
}
