package todoMaster.Action;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import todoMaster.HibernateUtil.HibernateUtil;
import todoMaster.config.todoConfig;
import todoMaster.entities.Todos;
import todoMaster.entities.User;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Scanner;

public class Actions {
    static ApplicationContext context = new AnnotationConfigApplicationContext(todoConfig.class);
    static HibernateUtil hibernateUtil = context.getBean("hibernateUtil", HibernateUtil.class);
    static SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    static Session session = sessionFactory.openSession();
    static Transaction transaction = session.beginTransaction();

    public static void userRegister(){
        System.out.println("_______________________________________________________");
        System.out.println(" ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Email Id : ");
        String email = sc.nextLine();
        System.out.println("Enter your Password");
        String password = sc.nextLine();

        System.out.println("_______________________________________________________");
        System.out.println(" ");


        User user = context.getBean("user", User.class);
        // Setting User
        user.setEmail(email);
        user.setPassword(password);

        session.persist(user);
        transaction.commit();
        getLogin();
    }

    public static void getLogin(){
        System.out.println("_______________________________________________________");
        System.out.println(" ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Email Id");
        String email = sc.nextLine();
        System.out.println("Enter Your Password");
        String password = sc.nextLine();

        System.out.println("_______________________________________________________");
        System.out.println(" ");

        Query q = session.createQuery("from User where email=:email");
        q.setParameter("email", email);
        User user = (User) q.uniqueResult();

        if(user == null){
            System.out.println("user Not Registered in our System");
            System.exit(0);
        }
        if(user.getPassword().equals(password)){
            System.out.println("Successfully Login");
            System.out.println("_______________________________________________________");
            System.out.println(" ");
            afterLogin(user);
        }else {
            System.out.println("Wrong Credentials");
        }
    }


    public static void afterLogin(User user){
        if(transaction == null){
            transaction = session.beginTransaction();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Show My Todos");
        System.out.println("2. Add Todos");
        System.out.println("3. Exit Application");
        int choice = sc.nextInt();

        System.out.println("_______________________________________________________");
        System.out.println(" ");

        switch (choice){
            case 1:
                showTodos(user);
                break;
            case 2 :
                addTodo(user);
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Wrong Choice");
                afterLogin(user);
        }

    }
    public static void showTodos(User user){
        if(transaction == null){
            transaction = session.beginTransaction();
        }

        String query = "select t from User as u INNER JOIN u.todos as t where u.email=:email";
        Query q = session.createQuery( query);
        q.setParameter("email", user.getEmail());
        List<Todos> list =q.list();

        System.out.println("____________________ This is Todos _____________________");
        System.out.println(" ");

        for (Todos todo : list){
            System.out.println(todo.getTodoId() + " : " + todo.getTodo());
        }

        System.out.println("_______________________________________________________");
        System.out.println(" ");

        System.out.println("1. Update Todo");
        System.out.println("2 Delete Todo");
        System.out.println("3. Go to back Menu");
        System.out.println("4 Exit Program");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        System.out.println("_______________________________________________________");
        System.out.println(" ");

        switch (choice){
            case 1:
                updateTodo(user);
                break;

            case 2:
                deleteTodo(user);
                break;

            case 3:
                afterLogin(user);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Wrong choice");
                showTodos(user);
        }
    }

    public static void addTodo(User user){

        Todos todo = context.getBean("todos", Todos.class);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter our Todo :");
        String todoData = sc.nextLine();
        todo.setTodo(todoData);
        todo.setTodoOwner(user);

        session.persist(todo);
        transaction.commit();
        transaction = session.beginTransaction();
        afterLogin(user);
    }

    public static void updateTodo(User user){

        Query q = session.createQuery("update Todos as t set t.todo=:todo where todoId=:id");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter TotoId : ");
        int todoId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter new Todo");
        String todo = sc.nextLine();

        System.out.println("_______________________________________________________");
        System.out.println(" ");

        q.setParameter("todo", todo);
        q.setParameter("id", todoId );

        q.executeUpdate();
        transaction.commit();
        session.clear();
        transaction = session.beginTransaction();
        showTodos(user);

    }

    public static void deleteTodo(User user){


        Query q = session.createQuery("delete Todos t where t.todoId = :id");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Todo Id : ");
        int todoId = sc.nextInt();

        System.out.println("_______________________________________________________");
        System.out.println(" ");

        q.setParameter("id", todoId);
        q.executeUpdate();
        transaction.commit();
        session.clear();
        transaction = session.beginTransaction();
        showTodos(user);
    }
}
