package todoMaster.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import todoMaster.entities.Todos;
import todoMaster.entities.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class HibernateUtil {
    private  static  SessionFactory sessionFactory;
    private static Session session;
    public  SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/todomaster");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "Rupam@mysql9301");
            configuration.setProperty("hibernate.show_sql", "false");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");

            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Todos.class);

            sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        }
        return sessionFactory;
    }
    public Session getSession(){
       if(sessionFactory != null){
           return sessionFactory.openSession();
       }else {
           System.out.println("Firstly Create a Session Factory");
       }
       return null;
    }

    @PostConstruct
    public void init(){
        System.out.println("Session Start from HibernateUtil");
    }

    @PreDestroy
    public void destroy(){
        session.close();
        sessionFactory.close();
        System.out.println("Successfully destroy");
    }
}
