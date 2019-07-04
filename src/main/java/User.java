import java.util.HashMap;
import java.util.Map;

public class User {
    private int age;
    private int id;
    private String imageProfile;
    private String name;

    public User() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Map<String, Object> getMapDefaultUser() {
        Map<String, Object> mapUser = new HashMap<>();
        User user = new User();
        user.setName("Teste Java 4");
        user.setId(1504);
        user.setImageProfile("kkkjkjk");
        user.setAge(4);

        mapUser.put("name", user.getName());
        mapUser.put("id", user.getId());
        mapUser.put("imageProfile", user.getImageProfile());
        mapUser.put("age", user.getAge());

        return mapUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", id=" + id +
                ", imageProfile='" + imageProfile + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
