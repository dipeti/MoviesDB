package com.dinya.peter.moviedb.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final String TAG = PropertiesReader.class.getSimpleName();
    private static final String FILE_NAME =  "application.properties";

    public static String getProperty(String key,Context context) {
        Properties properties = new Properties();;
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open(FILE_NAME);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, FILE_NAME + "could not be located. Please make sure to include the file!");
        }
        return properties.getProperty(key);
    }
    private PropertiesReader() {
    }
}
