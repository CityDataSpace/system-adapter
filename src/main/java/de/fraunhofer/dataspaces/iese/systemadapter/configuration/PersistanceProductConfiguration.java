package de.fraunhofer.dataspaces.iese.systemadapter.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:user.properties" })
@EnableJpaRepositories(
		basePackages = "de.fraunhofer.dataspaces.iese.systemadapter.repository.product",
		entityManagerFactoryRef = "productEntityManager",
		transactionManagerRef = "productTransactionManager"
)
public class PersistanceProductConfiguration {
	
	@Autowired
	private Environment env;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean productEntityManager() {
		
		LocalContainerEntityManagerFactoryBean em
        	= new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(productDataSource());
		em.setPackagesToScan(
        new String[] { "de.fraunhofer.dataspaces.iese.systemadapter.product" });
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
          env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("postgres.dialect",
          env.getProperty("postgres.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
		
	}
	
	@Bean
	public DataSource productDataSource() {
		DriverManagerDataSource dataSource 
			= new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("postgres.driverClassName"));
		dataSource.setUrl(env.getProperty("postgres.url"));
		dataSource.setUsername(env.getProperty("postgres.username"));
		dataSource.setPassword(env.getProperty("postgres.pass"));
		
		return dataSource;
		
	}
	
	 @Bean
	    public PlatformTransactionManager productTransactionManager() {
	 
	        JpaTransactionManager transactionManager
	          = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(
	          productEntityManager().getObject());
	        return transactionManager;
	    }
}
