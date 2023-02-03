package utils;

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

public class HandleNotif {
    public static void sendNotification(String content,String idutilisateur)throws Exception{
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("enchere-e4333-firebase-adminsdk-a8wcw-dd620ac387.json");

                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

            Notification notification = Notification
                    .builder()
                    .setTitle("Enchere")
                    .setBody(content)
                    .build();

            FirebaseApp firebaseApp=null;
            try{
                firebaseApp = FirebaseApp.getInstance();
            }catch(IllegalStateException e){
                firebaseApp = FirebaseApp.initializeApp(options);
            }
            
            
            Message message = Message.builder()
                    .putData("idutilisateur", idutilisateur)
                    .setNotification(notification)
                    .setTopic("over")
                    .build();

            try {
                String response = FirebaseMessaging.getInstance().send(message);
                System.out.println("Successful sent message: " + response);
            } catch (FirebaseMessagingException e) {
                System.out.println("Error sending message: " + e.getMessage());
                throw e;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Clée privée introuvable! Tout ce qui est Firebase Admin ne fonctionnera pas.");
            throw e;
        } catch (IOException e) {
            System.out.println("Erreur lors de l'initialisation de Firebase Admin! Tout ce qui est Firebase Admin ne fonctionnera pas.");
            throw e;
        }

    }
    
    public static void main(String[] gygy)throws Exception{
        HandleNotif.sendNotification("dfghjfghj fghjkgh","UT3");
    }
}
