package ru.parsing.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
//@EnableJpaRepositories( basePackages = {"webclient.backend.repositories"},
//                        entityManagerFactoryRef = "apossEntity")
public class DataBaseConfiguration {

    /**
     * Метод создает новый конфиг Hikari
     * @return
     */
    @Primary
    @Bean("masterHikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig masterHikariConfig() { return new HikariConfig();}

    @Primary
    @Bean("masterDataSource")
    public DataSource masterDataSource() { return new HikariDataSource(masterHikariConfig());}

    /**
     * Созааем коннект к БД с помощью Hikari и Hibernate
     * @param jpaProperties тянут из application.yml
     * @return entityManager
     */
    @Primary
    @Bean("apossEntityManager")
    @PersistenceUnit(name = "apossDataSource")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaProperties jpaProperties) {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setPackagesToScan(new String[] { "ru.parsing.*" });
        entityManager.setPersistenceUnitName("apossDataSource");
        entityManager.setDataSource(masterDataSource());
        entityManager.setJpaPropertyMap(jpaProperties.getProperties());
        entityManager.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        return entityManager;
    }

    /**
     * transactionManager
     * @param emf EntityManager
     * @return transactionManager
     */
    @Bean("apossTransactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
