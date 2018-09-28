package com.capgemini.callforcode.services;


import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;
import com.jcabi.aspects.Async;

@Service
public class FirebaseServiceImpl implements FirebaseService {


	
	private static final String FIREBASE_SERVER_KEY = "AAAA0aIhvqc:APA91bEKgaPB0t8WRqdcrPj9ejaHEYEaYcUEEVwqEHHjbCLEUB5S1bCs9uo4ELa88IqGrIFOvGSOLtSEOKRZ1scdfM_HjztG95u5LvegYcvUkxtJAIFOHHIQv8vmFXSRiPsRKuA8EvDj";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	
	@Async
	public CompletableFuture<String> send(HttpEntity<String> entity) {
 
		RestTemplate restTemplate = new RestTemplate();
 
		/**
		https://fcm.googleapis.com/fcm/send
		Content-Type:application/json
		Authorization:key=FIREBASE_SERVER_KEY*/
		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);
 
		String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
 
		return CompletableFuture.completedFuture(firebaseResponse);
	}

	@Override
	public CompletableFuture<String> sendPushNotification(HttpEntity<String> entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
