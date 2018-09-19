package com.openclassrooms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class PropSource {

    private static final Logger log = LoggerFactory.getLogger(PropSource.class);


    Properties prop = new Properties();
    InputStream input = null;
    OutputStream output = null;


    public PropSource() {

    }

    public Properties getProps() {


        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        URL url = classLoader.getResource("ctv.properties");

        try

        {
            input = new FileInputStream(url.toURI().getPath());

            // load a properties file
            prop.load(input);

            // get the property value and print it out
        } catch (IOException ex)

        {
            ex.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally

        {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return prop;

    }

    public void setProp(String extDur, String borDur){
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            URL url = classLoader.getResource("ctv.properties");
            File file = new File(url.toURI().getPath());


            String absolute = file.getCanonicalPath();

            ///Users/julien/IdeaProjects/biblioweb/src/main/resources/
            output = new FileOutputStream(url.toURI().getPath());

            // set the properties value
            prop.setProperty("extension-duration", extDur);
            prop.setProperty("borrowing-duration", borDur);

            // save properties to project root folder
            prop.store(output, null);
            log.info("fichier créé");

        } catch (IOException io) {
            io.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}



