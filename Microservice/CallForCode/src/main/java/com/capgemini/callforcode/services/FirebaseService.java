package com.capgemini.callforcode.services;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;

public interface FirebaseService {

	CompletableFuture<String> sendPushNotification(HttpEntity<String> entity);

}
