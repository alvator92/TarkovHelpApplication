package ru.parsing.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories( basePackages = {"ru.parsing.repository"},
        entityManagerFactoryRef = "apossEntityManager",
        transactionManagerRef = "apossTransactionManager")
public class JpaConfig {

//    @Bean("QuestService")
//    public ClientsService clientsService() {
//        return new ClientsServiceImpl();
//    }

}
