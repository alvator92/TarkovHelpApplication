package ru.parsing.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

@Slf4j
public class QuestIcon {

    private static String IMAGE_DESTINATION_FOLDER = "C:/Users/Public/Pictures";

    public void getImage(String questName, String questUrl) {
        try {
            StringBuilder s = new StringBuilder();
            s.append("https://tarkov.help");
            s.append(questUrl);
            log.info(s.toString());
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

                //download image one by one
                downloadImage(strImageURL);
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
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
