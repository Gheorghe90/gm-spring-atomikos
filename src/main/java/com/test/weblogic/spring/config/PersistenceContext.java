package com.test.weblogic.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by Gheorghe on 12/31/2015.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(value = {"com.test.weblogic.spring"})
public class PersistenceContext {

    @Bean
    public DataSource dataSource() throws NamingException {
        JndiTemplate jndi = new JndiTemplate();
        DataSource dataSource = (DataSource) jndi.lookup("jdbc/testJndi");

        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setPersistenceUnitName("testPU");
        factory.setPersistenceXmlLocation("classpath:META-INF/app-persistence.xml");
        factory.setPackagesToScan("com.test.weblogic.spring");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Throwable {
        return new JtaTransactionManager();
    }

}