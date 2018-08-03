package com.rest.async.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import sun.nio.ch.ThreadPool;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.rest.async")
@PropertySource({ "classpath:persistence-mysql.properties" })
@EnableAsync
@EnableWebSocket
public class AppConfig implements WebMvcConfigurer {
    final private int BUFFER_SIZE = 16384;

    @Autowired
    private Environment env;

    private int getIntProperty(String propName) {
        String propVal = env.getProperty(propName);
        return Integer.parseInt(propVal);
    }

    @Bean
    public DataSource myDataSource() {
        // configure data source with conn pooling
        ComboPooledDataSource myDataSource = new ComboPooledDataSource();

        try {
            myDataSource.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }

        myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        myDataSource.setUser(env.getProperty("jdbc.user"));
        myDataSource.setPassword(env.getProperty("jdbc.password"));

        // conn poll properties
        myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return myDataSource;
    }

    private Properties getHibernateProperties() {
        Properties props = new Properties();

        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        return props;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(myDataSource());
        sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);

        return executor;
    }

    @Bean
    public ServletServerContainerFactoryBean configureWebSocketContainer() {
        ServletServerContainerFactoryBean factory = new ServletServerContainerFactoryBean();

        factory.setMaxBinaryMessageBufferSize(BUFFER_SIZE);
        factory.setMaxTextMessageBufferSize(BUFFER_SIZE);
        factory.setMaxSessionIdleTimeout(TimeUnit.MINUTES.convert(30, TimeUnit.MILLISECONDS));
        factory.setAsyncSendTimeout(TimeUnit.SECONDS.convert(5, TimeUnit.MILLISECONDS));

        return factory;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
