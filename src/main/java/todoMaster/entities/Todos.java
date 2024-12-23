package todoMaster.entities;

import jakarta.persistence.*;

@Entity
public class Todos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todoId;
    private String todo;

    @ManyToOne
    private User todoOwner;

    public Todos(int todoId, String todo, User todoOwner) {
        this.todoId = todoId;
        this.todo = todo;
        this.todoOwner = todoOwner;
    }

    public Todos() {
        super();
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public User getTodoOwner() {
        return todoOwner;
    }

    public void setTodoOwner(User todoOwner) {
        this.todoOwner = todoOwner;
    }

    @Override
    public String toString() {
        return "Todos{" +
                "todoId=" + todoId +
                ", todo='" + todo + '\'' +
                ", todoOwner=" + todoOwner +
                '}';
    }
}
