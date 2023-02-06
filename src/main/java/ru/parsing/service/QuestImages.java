package ru.parsing.service;

import com.google.common.io.ByteStreams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import ru.parsing.dto.Images;
import ru.parsing.dto.QuestDtoOnce;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class QuestImages {

    public static String IMAGE_DESTINATION_FOLDER = "upload";
    private List<Images> list = new ArrayList<>();
    private Set<String> hashSet = new HashSet();

    @Value("${upload.directory}")
    private static String directory;

    @Value("${profileImage.type}")
    private String imageType;

    public List<Images> getImage(QuestDtoOnce questDtoOnce) {
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
                hashSet.add(strImageURL);
                //download image one by one
                downloadImage(strImageURL);
            }

        } catch(IOException e) {
            e.printStackTrace();
        };
        return getListFromHashSet(hashSet, questDtoOnce);
    }

    private void downloadImage(String strImageURL){

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
            in.close();
            os.close();

            System.out.println("Image saved");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<Images> getListFromHashSet(Set<String> hashSet, QuestDtoOnce questDtoOnce) {
        hashSet.forEach(url -> {
            Images photo = null;
            try {
                photo = new Images.Builder()
                        .withPhoto(url)
                        .withQuest(questDtoOnce)
                        .withImage(IOUtils.toByteArray(new URL(url).openStream()))
                        .build();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            list.add(photo);
        });
        return list;
    }
}
