package ru.parsing.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.parsing.dto.Photos;
import ru.parsing.dto.QuestDtoOnce;

import java.io.*;
import java.net.URL;

@Slf4j
public class QuestIcon {

    private static String IMAGE_DESTINATION_FOLDER = "C:/Users/Public/Pictures";
    private Photos photos = new Photos();

    public Photos getImage(QuestDtoOnce questDtoOnce) {
        try {
            StringBuilder s = new StringBuilder();
            s.append("https://tarkov.help");
            s.append(questDtoOnce.getUrl());
            log.info(questDtoOnce.getName() + " : " + s);
            var doc = Jsoup
                    .connect(s.toString())
                    .timeout(10*1000)
                    .get();
            Elements images =
                    doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

            //Iterate images and print image attributes.
            for (Element image : images) {
                log.info("Image Source: " + image.attr("src"));
                log.info("Image Height: " + image.attr("height"));
                log.info("Image Width: " + image.attr("width"));
                log.info("Image Alt Text: " + image.attr("alt"));
                log.info("");
                //make sure to get the absolute URL using abs: prefix
                String strImageURL = image.attr("abs:src");
                photos.setPhoto(strImageURL);
                photos.setQuest(questDtoOnce);

                //download image one by one
                downloadImage(strImageURL);
            }

        } catch(IOException e) {
            e.printStackTrace();
        };
        return photos;
    }

    private static void downloadImage(String strImageURL){

        //get file name from image path
        String strImageName =
                strImageURL.substring( strImageURL.lastIndexOf("/") + 1 );

        System.out.println("Saving: " + strImageName + ", from: " + strImageURL);

        try {

            //open the stream from URL
            URL urlImage = new URL(strImageURL);
            InputStream in = urlImage.openStream();

            byte[] buffer = new byte[4096];
            int n = -1;

            OutputStream os =
                    new FileOutputStream( IMAGE_DESTINATION_FOLDER + "/" + strImageName );

            //write bytes to the output stream
            while ( (n = in.read(buffer)) != -1 ){
                os.write(buffer, 0, n);
            }

            //close the stream
            os.close();

            System.out.println("Image saved");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
