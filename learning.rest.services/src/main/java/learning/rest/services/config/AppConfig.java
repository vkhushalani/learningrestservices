package learning.rest.services.config;

import java.util.HashMap;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class AppConfig {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPersistenceUnitName("JPA-EclipseLink-SpringBoot");
		HashMap<String,String> jpa = new HashMap<String,String>();
		jpa.put("eclipselink.weaving", "false");
		emf.setJpaPropertyMap(jpa);
		return emf;
	}
    @Bean
    public PlatformTransactionManager transactionManager(){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(
        entityManagerFactoryBean().getObject() );
       return transactionManager;
    }
}

