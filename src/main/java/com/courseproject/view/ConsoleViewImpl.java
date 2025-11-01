package com.courseproject.view;

import com.courseproject.factory.ControllerFactory;
import com.courseproject.controller.Controller;
import com.courseproject.model.Appliance;

import java.util.List;
import java.util.Scanner;

public class ConsoleViewImpl implements View {

    @Override
    public void start() {
        showMessage("Application started. Type commands: 'list laptops', 'list ovens', 'exit'");
        Scanner scanner = new Scanner(System.in);
        Controller controller = ControllerFactory.getInstance();
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if (line == null) break;
            if ("exit".equalsIgnoreCase(line.trim())) {
                showMessage("Bye!");
                break;
            }
            controller.handle(line.trim().toLowerCase());
        }
    }

    @Override
    public void crash() {
        System.err.println("Application crashed during startup.");
    }

    @Override
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void showAppliances(List<? extends Appliance> list) {
        if (list == null || list.isEmpty()) {
            showMessage("No items.");
            return;
        }
        for (Appliance a : list) {
            System.out.println(a);
        }
    }
}
