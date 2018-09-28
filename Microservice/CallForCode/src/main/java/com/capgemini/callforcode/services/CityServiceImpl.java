package com.capgemini.callforcode.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.callforcode.dtos.CityDTO;
import com.capgemini.callforcode.dtos.GetcityDTO;
import com.capgemini.callforcode.repositories.CityMasterRepository;
import com.capgemini.callforcode.repositories.CountryMasterRepository;
import com.capgemini.callforcode.repositories.StateMasterRepository;
import com.capgemini.callforcode.repositories.VolcanoCityDetailsRepository;
import com.capgemini.callforcode.repositories.VolcanoMasterRepository;
import com.capgemini.callforcode.reusable.transaction.entity.CityMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.CountryMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.StateMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.VolcanoCityDetails;
import com.capgemini.callforcode.reusable.transaction.entity.VolcanoMasterEntity;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;


@Service
public class CityServiceImpl implements CityService {

	private static final String PROXY_URL = "proxy-src.research.ge.com";
	private static final int PORT_NUMBER = 8080;
	private static final String HTTP_STRING = "http";

	@Autowired
	VolcanoMasterRepository volcanoMasterRepository;

	@Autowired
	CityMasterRepository cityMasterRepository;

	@Autowired
	StateMasterRepository stateMasterRepository;

	@Autowired
	CountryMasterRepository countryMasterRepository;

	@Autowired
	VolcanoCityDetailsRepository volcanoCityDetailsRepository;


	@Override
	public void getAllCities(GetcityDTO getcityDto) {
		String finalUrl = "http://gd.geobytes.com/GetNearbyCities?callback=?&radius="+getcityDto.getRadius()+"&Latitude="+getcityDto.getLat()+"&Longitude="+getcityDto.getLng();
		System.out.println("finalUrl"+finalUrl);
		String resultString = "";
        HttpGet get = new HttpGet(finalUrl);
		HttpHost proxy = new HttpHost(PROXY_URL, PORT_NUMBER, HTTP_STRING);
		HttpClientBuilder hcBuilder = HttpClients.custom();
		if (Boolean.parseBoolean("true")) { 
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
		hcBuilder.setRoutePlanner(routePlanner); 
		}
		  CloseableHttpClient client = hcBuilder.build();
		try {
			System.out.println("in try");
			HttpResponse response =  client.execute(get);
			InputStreamReader isr = new InputStreamReader(response.getEntity().getContent());
			try (BufferedReader rd = new BufferedReader(isr)) {
				StringBuilder result = new StringBuilder();

				Scanner sc = new Scanner(rd);
				while (sc.hasNext()) {
					result.append(sc.nextLine());
				}
				sc.close();
				isr.close();
				System.out.println("result1"+result.toString());
				if (!result.toString().isEmpty()) {
					System.out.println("result1"+result.toString());
					resultString = result.toString();	
				}
			}
		} catch (Exception e) {
			System.out.println("exception"+e);
		}
		if(resultString == null || resultString.length() <5) return;
		resultString = resultString.substring(2, resultString.length()-2);
		JSONArray jsObj = new JSONArray();
		JSONParser jsonParser = new JSONParser();
		try {
			jsObj = (JSONArray) jsonParser.parse(resultString);
		} catch (org.json.simple.parser.ParseException e1) {
		}
		VolcanoMasterEntity volcanoMasterEntity = volcanoMasterRepository.findOne(getcityDto.getVolcanoId());

		for(Object obj : jsObj){

			CountryMasterEntity countryMasterEntity = countryMasterRepository.findByCountryName(((JSONArray)obj).get(3).toString().toUpperCase());
			StateMasterEntity stateMasterEntity = stateMasterRepository.findByStateName(((JSONArray)obj).get(1).toString().toUpperCase());
			CityMasterEntity cityMasterEntity = cityMasterRepository.findBYCityName(((JSONArray)obj).get(12).toString().toUpperCase());
			if(countryMasterEntity == null){
				CountryMasterEntity countryMasterNew = new CountryMasterEntity();
				countryMasterNew.setCountryName(((JSONArray)obj).get(3).toString().toUpperCase());
				countryMasterEntity = countryMasterRepository.save(countryMasterNew);
			}

			if(stateMasterEntity == null){
				StateMasterEntity stateMasterNew = new StateMasterEntity();
				stateMasterNew.setStateName(((JSONArray)obj).get(1).toString().toUpperCase());
				stateMasterNew.setCountryMasterEntity(countryMasterEntity);
				stateMasterEntity = stateMasterRepository.save(stateMasterNew);
			}

			if(cityMasterEntity == null){
				CityMasterEntity cityMasterNew = new CityMasterEntity();
				cityMasterNew.setCityName(((JSONArray)obj).get(12).toString().toUpperCase());
				cityMasterNew.setStateMasterEntity(stateMasterEntity);
				cityMasterEntity = cityMasterRepository.save(cityMasterNew);
			}

			VolcanoCityDetails volcanoCityDetails = volcanoCityDetailsRepository.findByVolcanoAndCity(volcanoMasterEntity.getVolcanoMasterId(), cityMasterEntity.getCityId());
			if(volcanoCityDetails == null){
				VolcanoCityDetails volcanoCityNew = new VolcanoCityDetails();
				volcanoCityNew.setCityMasterEntity(cityMasterEntity);
				volcanoCityNew.setVolcanoMasterEntity(volcanoMasterEntity);
				volcanoCityDetailsRepository.save(volcanoCityNew);
			}

		}


	}

}
