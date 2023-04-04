package np.com.esewa.learn.sampleapplication.inventory.config;

import org.springframework.beans.factory.annotation.Autowired;
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
        entityManagerFactoryRef = "productEntityManagerFactoryBean",
        basePackages = {"np.com.esewa.learn.sampleapplication.inventory.repository"},
        transactionManagerRef = "productTransactionManager"
)
public class ProductDatabaseConfig {
    @Autowired
    private Environment environment;

    @Bean(name = "productDataSource")
    @Primary
    public DataSource dataSource(){
        return new DriverManagerDataSource(
                Objects.requireNonNull(environment.getProperty("product.datasource.url")),
                Objects.requireNonNull(environment.getProperty("product.datasource.username")),
                Objects.requireNonNull(environment.getProperty("product.datasource.password"))
        );
    }

    @Bean(name = "productEntityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());

        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("np.com.esewa.learn.sampleapplication.inventory.model");

        Map<String , String > dbConnProperties = new HashMap<>();
        dbConnProperties.put("hibernate.hbm2ddl.auto", "update");
        dbConnProperties.put("hibernate.show_sql", "true");
        entityManagerFactoryBean.setJpaPropertyMap(dbConnProperties);

        return entityManagerFactoryBean;
    }

    @Bean(name = "productTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }
}

