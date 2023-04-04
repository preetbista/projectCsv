package np.com.esewa.learn.sampleapplication.filedetails.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "productFileEntityManagerFactoryBean",
        basePackages = {"np.com.esewa.learn.sampleapplication.filedetails.repository"},
        transactionManagerRef = "productFileTransactionManager"
)
public class FileDetailsDatabaseConfig {
    public final Environment environment;

    public FileDetailsDatabaseConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @Primary
    public DataSource dataSource(){
        return new DriverManagerDataSource(
                Objects.requireNonNull(environment.getProperty("spring.datasource.url")),
                Objects.requireNonNull(environment.getProperty("spring.datasource.username")),
                Objects.requireNonNull(environment.getProperty("spring.datasource.password"))
        );
    }

    @Bean(name = "productFileEntityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());

        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("np.com.esewa.learn.sampleapplication.filedetails.model");

        Map<String , String > dbConnProperties = new HashMap<>();
        dbConnProperties.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        dbConnProperties.put("hibernate.hbm2ddl.auto", "update");
        dbConnProperties.put("hibernate.show_sql", "true");

        entityManagerFactoryBean.setJpaPropertyMap(dbConnProperties);

        return entityManagerFactoryBean;
    }

    @Bean(name = "productFileTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
