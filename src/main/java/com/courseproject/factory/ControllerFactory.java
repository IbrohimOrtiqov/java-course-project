package com.courseproject.factory;

import com.courseproject.controller.Controller;

public class ControllerFactory {
    private static Controller controller;

    private ControllerFactory(){}

    public static void init(Controller c) { controller = c; }
    public static Controller getInstance() { return controller; }
}
