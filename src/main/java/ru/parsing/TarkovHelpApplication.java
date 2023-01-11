package ru.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import ru.parsing.configuration.DataBaseConfiguration;

import java.util.Iterator;
import java.util.Map;

@SpringBootApplication
public class TarkovHelpApplication {

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private DataBaseConfiguration hibernateConfig;

    public static void main (String... args) {
        SpringApplication.run(TarkovHelpApplication.class, args);
        try {
            var document = Jsoup.connect("https://tarkov.help/ru/quest/oruzhejnik-chast-3").get();

            var titleElements = document.select(".quest-description");
            System.out.println(titleElements.text());

            var titleElements2 = document.select(".quest-tab-grid");
            System.out.println(titleElements2.text());

            var titleElements3 = document.select(".quest-text-award");
            System.out.println(titleElements3.text());

            var titleElements4 = document.select(".quest-guide");
            System.out.println(titleElements4.text());

            var titleElements5 = document.select(".bb-block .bb-table, .quest-page .bb-table");
            System.out.println(titleElements5.select(".item-name").text());


        } catch (Exception e) {
            e.printStackTrace();
        }

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
