package com.operations;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(App.class);
	}

	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.h2.Driver");
		dataSourceBuilder.url("jdbc:h2:~/test");
		dataSourceBuilder.username("sa");
		dataSourceBuilder.password("");
		return dataSourceBuilder.build();
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {

		System.out.println("The data source is : " + dataSource.getConnection());

		Properties properties = new Properties();

		// See: application.properties
		properties.put("hibernate.enable_lazy_load_no_trans", true);
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("current_session_context_class", "org.h2.Driver");
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		// Package contain entity classes
		factoryBean.setPackagesToScan(new String[] { "com.operations" });
		factoryBean.setDataSource(dataSource);
		factoryBean.setMappingLocations(loadResources(factoryBean.getResourceLoader()));
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();

		SessionFactory sf = factoryBean.getObject();

		return sf;
	}

	public Resource[] loadResources(ResourceLoader loader) {
		Resource[] resources = null;
		try {
			resources = ResourcePatternUtils.getResourcePatternResolver(loader)
					.getResources("classpath:hibernate-queries.cfg.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resources;
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
