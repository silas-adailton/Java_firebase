import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {


        try {
            FileInputStream serviceAccount = new FileInputStream("/path/Your account-service.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("http://.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }


        UserFirebaseRepository userFirebaseRepository = new UserFirebaseRepository();
        userFirebaseRepository.setUser();
//        userFirebaseRepository.getUser();

    }

}
