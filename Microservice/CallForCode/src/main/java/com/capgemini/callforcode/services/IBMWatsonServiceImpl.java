package com.capgemini.callforcode.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.language_translator.v3.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslationResult;
import com.ibm.watson.developer_cloud.language_translator.v3.util.Language;
import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ProfileOptions;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.mobilefirstplatform.serversdk.java.push.Message;
import com.ibm.mobilefirstplatform.serversdk.java.push.Notification;
import com.ibm.mobilefirstplatform.serversdk.java.push.PushNotifications;
import com.ibm.mobilefirstplatform.serversdk.java.push.PushNotificationsResponseListener;
import com.ibm.mobilefirstplatform.serversdk.java.push.Target;
import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.assistant.v1.model.RuntimeIntent;

@Service
public class IBMWatsonServiceImpl implements IBMWatsonService {

	@Override
	public void languageTranslator() {
		LanguageTranslator service = new LanguageTranslator("2018-05-01");
		IamOptions iamOptions = new IamOptions.Builder()
		  .apiKey("NVM9DO0ZJWAUI4jNVmXE4jHDZndrqRy1C9kPnATEYdu9")
		  .build();
		service.setIamCredentials(iamOptions);

		TranslateOptions translateOptions = new TranslateOptions.Builder()
		  .addText("hello")
		  .source(Language.ENGLISH)
		  .target(Language.SPANISH)
		  .build();
		TranslationResult translationResult = service.translate(translateOptions).execute();

		System.out.println(translationResult); 


	}

	@Override
	public String chatbotResponse(String inputText) {
		Assistant service = new Assistant("2018-02-16");
	    service.setUsernameAndPassword("93790f5b-2b2b-49e2-a48f-d9c10902c00c", // replace with service username
	                                   "XELZLibOpPgP"); // replace with service password
	    String workspaceId = "7431fc28-4a4f-4807-9767-d49a98e4f3d9"; // replace with workspace ID


	    // Initialize with empty value to start the conversation.
	    MessageOptions options = new MessageOptions.Builder(workspaceId).build();

	    // Main input/output loop
	      // Send message to Assistant service.
	      MessageResponse response = service.message(options).execute();  
	      
	      System.out.println("*-*- "+response.getOutput().getText());
	      String responseText = response.getOutput().getText().get(0);
	      List<RuntimeIntent> responseIntents = response.getIntents();

	      // If an intent was detected, print it to the console.
	      if(responseIntents.size() > 0) {
	        System.out.println("Detected intent: #" + responseIntents.get(0).getIntent());
	      }

	      // Print the output from dialog, if any.
	      InputData input = new InputData.Builder(inputText).build();
	      options = new MessageOptions.Builder(workspaceId).input(input).build();

		  response = service.message(options).execute();  
		      
		  responseText = response.getOutput().getText().get(0);
		   responseIntents = response.getIntents();
		return responseText;
		}

	
}
