package de.fraunhofer.dataspaces.iese.systemadapter.configuration.database;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * This class serves as a configuration class for Mysql JPA Repository
 */
@Configuration
@PropertySource({ "classpath:database/database.properties", "classpath:database/mysql.properties" })
@EnableJpaRepositories(
		basePackages = "de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql",
		entityManagerFactoryRef = "userEntityManager",
		transactionManagerRef = "userTransactionManager"
)
public class PersistanceMysqlConfiguration {
	
	@Autowired
	private Environment env;
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean userEntityManager() {
		LocalContainerEntityManagerFactoryBean em 
			= new LocalContainerEntityManagerFactoryBean();
		
		em.setDataSource(userDataSource());
		em.setPackagesToScan(
				new String[] { "de.fraunhofer.dataspaces.iese.systemadapter.model.mysql" });
		
		HibernateJpaVendorAdapter vendorAdapter 
			= new HibernateJpaVendorAdapter();
		
		em.setJpaVendorAdapter(vendorAdapter);
		
		HashMap<String, Object> properties = new HashMap<>();
		
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		        
		em.setJpaPropertyMap(properties);
				
		return em;			
	}
	
	@Primary
    @Bean
    public DataSource userDataSource() {
 
        DriverManagerDataSource dataSource
          = new DriverManagerDataSource();
        dataSource.setDriverClassName(
          env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("user.jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager userTransactionManager() {
 
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
          userEntityManager().getObject());
        return transactionManager;
    }
}

