import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {


        try {
            FileInputStream serviceAccount = new FileInputStream("path/your serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }


        UserFirebaseRepository userFirebaseRepository = new UserFirebaseRepository();
        userFirebaseRepository.setUser().subscribe(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e);
            }
        });


        userFirebaseRepository.getUser()
                .subscribe(new DisposableMaybeObserver<Object>() {

                    @Override
                    public void onSuccess(Object o) {
                        User user = ((User) o);
                        System.out.println(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

}
