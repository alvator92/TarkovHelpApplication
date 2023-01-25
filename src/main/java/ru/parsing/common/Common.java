package ru.parsing.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Служебный класс для работы со строками, числами, Датами
 */
public class Common {

    private static Random  random = new Random();
    private static Calendar calendar = new GregorianCalendar();

    // 2022-07-16T16:22:13.000+03:00
    public static String getDataTimeZone() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000+03:00'");
        return dateFormat.format(calendar.getTime());
    }

    // 2022-07-16T16:22:13
    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return dateFormat.format(calendar.getTime());
    }

    // 16.07.2022
    public static String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(calendar.getTime());
    }

    public static String getRandomNumber(int length) {
        String characters = "12345679";
        char[] text = new char[length];
        for(int i = 0; i < text.length; i ++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }

    public static String getRandomNumberMount(int length) {
        String characters = "01";
        char[] text = new char[length];
        for(int i = 0; i < text.length; i ++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }

    public static String getRandomString(int length) {
        String characters = "12345679ABCDEF";
        char[] text = new char[length];
        for(int i = 0; i < text.length; i ++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }
}

