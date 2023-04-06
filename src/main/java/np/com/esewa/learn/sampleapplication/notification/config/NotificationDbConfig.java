package np.com.esewa.learn.sampleapplication.notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "notificationEntityManagerFactoryBean",
        basePackages = {"np.com.esewa.learn.sampleapplication.notification.repository"},
        transactionManagerRef = "notificationTransactionManager"
)
public class NotificationDbConfig {
    public final Environment environment;

    public NotificationDbConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = "notificationDataSource")
    public DataSource dataSource(){
        return new DriverManagerDataSource(
                Objects.requireNonNull(environment.getProperty("spring.datasource.url")),
                Objects.requireNonNull(environment.getProperty("spring.datasource.username")),
                Objects.requireNonNull(environment.getProperty("spring.datasource.password"))
        );
    }

    @Bean(name = "notificationEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());

        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("np.com.esewa.learn.sampleapplication.notification.model");

        Map<String , String > dbConnProperties = new HashMap<>();
        dbConnProperties.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        dbConnProperties.put("hibernate.hbm2ddl.auto", "update");
        dbConnProperties.put("hibernate.show_sql", "true");

        entityManagerFactoryBean.setJpaPropertyMap(dbConnProperties);

        return entityManagerFactoryBean;
    }

    @Bean(name = "notificationTransactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
