package cn.wrs.mapper;

import java.util.List;

import cn.wrs.po.WeatherCustom;

public interface WeatherMapperCustom {
	//���ݳ��в�ѯ����
	public WeatherCustom selectWeatherByCity(String name) throws Exception;
}
