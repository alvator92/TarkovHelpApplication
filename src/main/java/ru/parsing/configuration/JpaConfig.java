package ru.parsing.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.parsing.service.*;

@Configuration
@EnableJpaRepositories( basePackages = {"ru.parsing.repository"},
        entityManagerFactoryRef = "apossEntityManager",
        transactionManagerRef = "apossTransactionManager")
public class JpaConfig {

    @Bean("QuestService")
    public QuestServices questService() {
        return new QuestServiceImpl();
    }

    @Bean("PhotoService")
    public PhotoService photoService() {return new PhotoServiceImpl();
    }

    @Bean("UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean("AdsService")
    public AdsService adsService() {
        return new AdsServiceImpl();
    }
}
