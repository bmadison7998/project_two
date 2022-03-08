import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


// @Entity(name="tablename")
@Entity(name="user")
public class User {
    // @ID sets primary key
    @Id
    private int id;
    @Column
    private String username;
    private String email;
    private String password;
    private boolean isManager;

    // insert data through the constructor
    public User(int id, String username, String email, String password, boolean isManager) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isManager = isManager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}
