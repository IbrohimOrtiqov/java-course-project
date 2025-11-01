package com.courseproject.controller;

import com.courseproject.factory.ServiceFactory;
import com.courseproject.model.Laptop;
import com.courseproject.model.Oven;
import com.courseproject.service.ApplianceService;
import com.courseproject.view.View;

import java.util.List;

public class SimpleControllerImpl implements Controller {
    private final View view;

    public SimpleControllerImpl(View view) {
        this.view = view;
    }

    @Override
    public void handle(String command) {
        switch (command) {
            case "list laptops" -> {
                ApplianceService<Laptop> svc = ServiceFactory.get(Laptop.class);
                List<Laptop> list = svc.listAll();
                view.showAppliances(list);
            }
            case "list ovens" -> {
                ApplianceService<Oven> svc = ServiceFactory.get(Oven.class);
                List<Oven> list = svc.listAll();
                view.showAppliances(list);
            }
            default -> view.showMessage("Unknown command: " + command);
        }
    }
}
