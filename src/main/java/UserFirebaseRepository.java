import com.google.firebase.database.*;

public class UserFirebaseRepository {

    public void setUser() {
        DatabaseReference db = FirebaseDatabase.getInstance()
                .getReference("user").push();

        new Thread(() -> {
            db.setValue(User.getMapDefaultUser(), (error, ref1) -> {
                if (error != null) {
                    System.out.println(error.getMessage());
                    return;
                }
                System.out.println(ref1.toString());
            });

            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }


    public void getUser() {

        DatabaseReference db = FirebaseDatabase.getInstance()
                .getReference("user");

        new Thread(() -> {
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);
                        System.out.println(user);
                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    System.out.println(error.getMessage());
                }
            });

            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
