package ru.parsing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import ru.parsing.configuration.DataBaseConfiguration;
import ru.parsing.service.QuestClient;
import ru.parsing.service.QuestIcon;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TarkovHelpApplication {

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private DataBaseConfiguration hibernateConfig;

    public static void main (String... args) {
        SpringApplication.run(TarkovHelpApplication.class, args);
        List<QuestClient> lists = new ArrayList<>();
        QuestIcon questIcon = new QuestIcon();
        questIcon.getImage("Мокрое дело. Часть 3", "/ru/quest/mokroe-delo-chast-3");


    }

//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("DataSource : " + hibernateConfig);
//        System.out.println("DataSource : " + hibernateConfig.masterDataSource());
//        System.out.println("DataSource : " + hibernateConfig.masterHikariConfig());
//
//        // проверка пропертей
//        Iterator hmIterator = jpaProperties.getProperties().entrySet().iterator();
//
//        while (hmIterator.hasNext()) {
//            Map.Entry mapElement
//                    = (Map.Entry)hmIterator.next();
//            System.out.println(mapElement.getKey() + " : " + mapElement.getValue());
//        }
//    }

}
