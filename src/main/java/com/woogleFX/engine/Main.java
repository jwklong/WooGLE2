package com.woogleFX.engine;

import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    private static String[] launchArguments;
    public static String[] getLaunchArguments() {
        return launchArguments;
    }
    public static void setLaunchArguments(String[] launchArguments) {
        Main.launchArguments = launchArguments;
    }


    @Override
    public void start(Stage stage) {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> logger.error("", throwable));
        Initializer.start(stage);
    }


    private static long secretHash(String text, int value) {

        long hashResult = 0;
        char path;
        boolean valuePositive;
        char[] chars = text.toCharArray();
        int index = 0;

        path = chars[index++];
        valuePositive = 0 < value;
        if ((0 < value) || (value == -1 && path != '\0')) {
            hashResult = 0xababababL;
            do {
                char tChar = (char) (path + 32);
                if (25 < (byte)(path + 191)) {
                    tChar = path;
                }
                long b = (hashResult ^ (int)tChar);
                if (b < 0) b += (1L << 32);
                b >>= 0x19L;
                hashResult = (hashResult ^ (long)tChar) << 7L | b;
                value -= valuePositive ? 1 : 0;
                if (index < chars.length) {
                    path = chars[index++];
                } else {
                    path = '\0';
                }
                valuePositive = 0 < value;
                System.out.println(hashResult);
            } while ((0 < value) || (path != '\0' && value == -1));
        }

        return hashResult;

    }

}