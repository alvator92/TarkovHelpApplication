package ru.parsing.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.parsing.service.QuestServiceImpl;
import ru.parsing.service.QuestServices;

@Configuration
@EnableJpaRepositories( basePackages = {"ru.parsing.repository"},
        entityManagerFactoryRef = "apossEntityManager",
        transactionManagerRef = "apossTransactionManager")
public class JpaConfig {

    @Bean("QuestService")
    public QuestServices questService() {
        return new QuestServiceImpl();
    }

}
