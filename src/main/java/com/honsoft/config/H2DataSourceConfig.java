package com.honsoft.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.InitBinder;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.honsoft.repository",transactionManagerRef = "h2JpaTransactionManager",entityManagerFactoryRef = "h2EntityManagerFactory")
public class H2DataSourceConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	@ConfigurationProperties(prefix = "h2.datasource")
	public DataSourceProperties h2DataSourceProperties() {
		DataSourceProperties properties = new DataSourceProperties();
		return properties;
	}
	
	@Bean
	public DataSource h2DataSource() {
		return h2DataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	
	@Bean
	public DataSourceInitializer h2DataSourceInitializer(@Qualifier("h2DataSource") DataSource dataSource) {
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setEnabled(env.getProperty("h2.datasource.initializer", Boolean.class, false));
		
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("sql/schema-h2.sql"));
		populator.addScript(new ClassPathResource("sql/data-h2.sql"));
		populator.setSqlScriptEncoding(env.getProperty("h2.datasource.encoding",String.class,"UTF-8"));
		
		initializer.setDatabasePopulator(populator);
		
		return initializer;
	}
	
	@Bean(name = "h2JpaTransactionManager")
	public PlatformTransactionManager h2JpaTransactionManager() {
		JpaTransactionManager jpaTxManager = new JpaTransactionManager(h2EntityManagerFactory().getObject());
		return jpaTxManager;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(h2DataSource());
		factory.setJpaDialect(new HibernateJpaDialect());
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		
		Properties properties = new Properties();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("h2.jpa.hbm2ddl.auto",String.class,"none"));
		properties.put("hibernate.show-sql", env.getProperty("h2.jpa.show-sql",Boolean.class,true));
		properties.put("hibernate.format_sql", env.getProperty("h2.jpa.format_sql",Boolean.class,true));
		properties.put("hibernate.dialect", env.getProperty("h2.jpa.dialect",String.class,"org.hibernate.dialect.H2Dialect"));
		
		factory.setJpaProperties(properties);
		factory.setPackagesToScan("com.honsoft.entity");
		
		return factory;
	}
}
