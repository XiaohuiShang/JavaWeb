package cn.wrs;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.wrs.controller.WeatherController;
import cn.wrs.po.Weather;
import cn.wrs.po.WeatherCustom;
import cn.wrs.service.WeatherService;


public class WeatherTest {
	private ApplicationContext context = null;
	@Before
	public void setUp(){
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}
	
	@Test
	public void testservice(){
		WeatherService WeatherService = (WeatherService) context.getBean("weatherService");
		try {
			WeatherCustom weatherCustom = WeatherService.selectWeatherByCity("±±¾©");
			System.out.println(weatherCustom.getName());
			for(Weather weather : weatherCustom.getWeathers()){
				System.out.println(weather.getTemperature());
				System.out.println(weather.getTips());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}		
	}
	
}
