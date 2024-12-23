package todoMaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import todoMaster.HibernateUtil.HibernateUtil;
import todoMaster.entities.Todos;
import todoMaster.entities.User;

@Configuration
@ComponentScan(basePackages = "todoMaster.HibernateUtil")
public class todoConfig{

    @Bean(name = {"user"})
    public User getUser(){
        return new User();
    }

    @Bean(name = {"todos"})
    public Todos getTodos(){
        return new Todos();
    }

    @Bean(name = {"hibernateUtil"})
    public HibernateUtil getHibernateUtil(){
        return new HibernateUtil();
    }

}