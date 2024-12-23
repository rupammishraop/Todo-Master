package todoMaster.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class User {
    @Id
    private String email;
    private String password;

    @OneToMany(mappedBy = "todoOwner")
    private List<Todos> todos;

    public User(String email, String password, List<Todos> todos) {
        this.email = email;
        this.password = password;
        this.todos = todos;
    }

    public User() {
        super();
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

    public List<Todos> getTodos() {
        return todos;
    }

    public void setTodos(List<Todos> todos) {
        this.todos = todos;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", todos=" + todos +
                '}';
    }
}
