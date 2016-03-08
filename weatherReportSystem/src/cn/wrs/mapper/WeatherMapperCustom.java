package cn.wrs.mapper;

import java.util.List;

import cn.wrs.po.WeatherCustom;

public interface WeatherMapperCustom {
	//根据城市查询天气
	public WeatherCustom selectWeatherByCity(String name) throws Exception;
}
