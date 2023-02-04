package ru.parsing.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.parsing.configuration.JpaConfig;
import ru.parsing.dto.Ads;
import ru.parsing.dto.User;

@Component
@Slf4j
public class ScheduledService {

    @Autowired
    private JpaConfig jpaConfig;
    @Autowired
    private ExecutionService executionService;

    @Scheduled(cron = "0 * * * * *")
    public void sendAds() {

        var ads = jpaConfig.adsService().findAll();
        var users = jpaConfig.userService().findAll();

        for (Ads ad : ads) {
            for (User user : users) {
                executionService.prepareAndSendMessage(user.getChatId(), ad.getAdvertise());

            }
        }
    }
}

