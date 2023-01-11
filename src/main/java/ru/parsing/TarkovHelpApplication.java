package ru.parsing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import ru.parsing.configuration.DataBaseConfiguration;

import java.util.Iterator;
import java.util.Map;

@SpringBootApplication
public class TarkovHelpApplication implements CommandLineRunner {

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private DataBaseConfiguration hibernateConfig;

    public static void main (String... args) {
        SpringApplication.run(TarkovHelpApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DataSource : " + hibernateConfig);
        System.out.println("DataSource : " + hibernateConfig.masterDataSource());
        System.out.println("DataSource : " + hibernateConfig.masterHikariConfig());

        // проверка пропертей
        Iterator hmIterator = jpaProperties.getProperties().entrySet().iterator();

        while (hmIterator.hasNext()) {
            Map.Entry mapElement
                    = (Map.Entry)hmIterator.next();
            System.out.println(mapElement.getKey() + " : " + mapElement.getValue());
        }
    }

}
