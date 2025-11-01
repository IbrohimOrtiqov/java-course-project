package com.courseproject;

import com.courseproject.startup.Config;
import com.courseproject.startup.PropertiesConfigImpl;
import com.courseproject.view.View;
import com.courseproject.factory.ViewFactory;

public class Main {
    public static void main(String[] args) {
        String propertiesName = args.length == 0 ? "app" : args[0];
        View view = new com.courseproject.view.ConsoleViewImpl();
        try {
            Config config = new PropertiesConfigImpl(propertiesName);
            config.init();
            view = ViewFactory.getInstance();
            view.start();
        } catch (RuntimeException e) {
            view.crash();
            e.printStackTrace();
        }
    }
}
