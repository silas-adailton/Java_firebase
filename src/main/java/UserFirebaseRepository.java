import com.google.firebase.database.*;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.plugins.RxJavaPlugins;

public class UserFirebaseRepository {

    public Completable setUser() {
        DatabaseReference db = FirebaseDatabase.getInstance()
                .getReference("user").push();

        return Completable.create(emitter -> {
            db.setValue(User.getMapDefaultUser(), (error, ref1) -> {
                if (error != null) {
                    emitter.onError(error.toException());
                    return;
                }
                emitter.onComplete();
            });

        }).subscribeOn(RxJavaPlugins.createIoScheduler(Thread::new));
    }


    public Maybe<Object> getUser() {

        DatabaseReference db = FirebaseDatabase.getInstance()
                .getReference("user");

        return Maybe.create(emitter -> {
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);
                        emitter.onSuccess(user);
                        emitter.onComplete();
                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    System.out.println(error.getMessage());
                    emitter.onError(error.toException());
                    emitter.onComplete();
                }
            });

        }).subscribeOn(RxJavaPlugins.createIoScheduler(Thread::new));

    }
}
