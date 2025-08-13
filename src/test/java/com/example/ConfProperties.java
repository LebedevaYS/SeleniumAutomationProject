package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfProperties {
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;

    static {
        try {
            // Указываем путь к файлу и кодировку UTF-8
            fileInputStream = new FileInputStream("src/test/resources/conf.properties");
            PROPERTIES = new Properties();
            // Читаем файл в UTF-8
            PROPERTIES.load(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
