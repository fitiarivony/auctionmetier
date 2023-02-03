package com.example.enchere;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnchereApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnchereApplication.class, args);
	//initializeFirebaseAdmin();
	}

	private static void initializeFirebaseAdmin() {
		try {
			FileInputStream serviceAccount =
					new FileInputStream("enchere-e4333-firebase-adminsdk-a8wcw-dd620ac387.json");
                              
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
                          Notification notification = Notification
                .builder()
                .setTitle("Mon sujet")
                .setBody("Mon corps")
                .build();
			FirebaseApp.initializeApp(options);
			Message message = Message.builder()
					.putData("key1", "value1")
					.putData("key2", "value2")
                                         .setNotification(notification)
					
                                .setTopic("over")
					.build();

			try {
				String response = FirebaseMessaging.getInstance().send(message);
				System.out.println("Successful sent message: " + response);
			} catch (FirebaseMessagingException e) {
				System.out.println("Error sending message: " + e.getMessage());
			}
		} catch (FileNotFoundException e) {
			System.out.println("Clée privée introuvable! Tout ce qui est Firebase Admin ne fonctionnera pas.");
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("Erreur lors de l'initialisation de Firebase Admin! Tout ce qui est Firebase Admin ne fonctionnera pas.");
		}
	}


}
