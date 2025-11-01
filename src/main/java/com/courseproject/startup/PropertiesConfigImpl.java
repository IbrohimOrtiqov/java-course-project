package com.courseproject.startup;

import com.courseproject.factory.*;
import com.courseproject.view.ConsoleViewImpl;
import com.courseproject.view.View;
import com.courseproject.dao.ApplianceDao;
import com.courseproject.dao.impl.ApplianceDaoImpl;
import com.courseproject.model.Laptop;
import com.courseproject.model.Oven;
import com.courseproject.dao.straight.LaptopCsvSourceImpl;
import com.courseproject.dao.straight.OvenCsvSourceImpl;
import com.courseproject.service.impl.ApplianceServiceImpl;
import com.courseproject.service.ApplianceService;
import com.courseproject.controller.SimpleControllerImpl;
import com.courseproject.controller.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class PropertiesConfigImpl implements Config {
    private final String propertiesName;

    public PropertiesConfigImpl(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    @Override
    public void init() {
        try (InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(propertiesName + ".properties")) {
            Properties properties = new Properties();
            properties.load(is);
            initView(properties);
            initController(properties);
            initService(properties);
            initDao(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initView(Properties properties) {
        String viewMode = properties.getProperty("view");
        View view = switch (viewMode) {
            case "console" -> new ConsoleViewImpl();
            default -> throw new IllegalStateException("Unexpected value: " + viewMode);
        };
        ViewFactory.init(view);
    }

    private void initController(Properties properties) {
        String controllerMode = properties.getProperty("controller");
        // simple controller uses the View instance and ServiceFactory
        Controller controller = switch (controllerMode) {
            case "simple" -> new SimpleControllerImpl(ViewFactory.getInstance());
            default -> throw new IllegalStateException("Unexpected value: " + controllerMode);
        };
        ControllerFactory.init(controller);
    }

    private void initService(Properties properties) {
        String serviceMode = properties.getProperty("service");
        if (!"simple".equals(serviceMode)) {
            throw new IllegalStateException("Unexpected value: " + serviceMode);
        }
        // create services using DAOs that will be created in initDao; therefore
        // services are initialized after dao in this implementation
        // to support that we keep a lazy init: we'll create real services in initDao
        // after DAOs are available.
    }

    private void initDao(Properties properties) {
        String sourceMode = properties.getProperty("source");
        if (!"csv".equals(sourceMode)) {
            throw new IllegalStateException("Unexpected value: " + sourceMode);
        }
        String laptopName = properties.getProperty("source.laptop") + ".csv";
        String ovenName = properties.getProperty("source.oven") + ".csv";
        String daoMode = properties.getProperty("dao");

        Map<Class<? extends com.courseproject.model.Appliance>, ApplianceDao<? extends com.courseproject.model.Appliance>> daoMap;

        switch (daoMode) {
            case "straight":
                daoMap = Map.of(
                        com.courseproject.model.Laptop.class, new ApplianceDaoImpl<>(
                                new com.courseproject.dao.straight.LaptopCsvSourceImpl(laptopName)),
                        com.courseproject.model.Oven.class, new ApplianceDaoImpl<>(
                                new com.courseproject.dao.straight.OvenCsvSourceImpl(ovenName)));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + daoMode);
        }

        DaoFactory.init(daoMap);

        // Initialize services using DAOFactory
        ApplianceService<com.courseproject.model.Laptop> laptopService = new ApplianceServiceImpl<>(
                DaoFactory.get(com.courseproject.model.Laptop.class));
        ApplianceService<com.courseproject.model.Oven> ovenService = new ApplianceServiceImpl<>(
                DaoFactory.get(com.courseproject.model.Oven.class));

        ServiceFactory.init(Map.of(
                com.courseproject.model.Laptop.class, laptopService,
                com.courseproject.model.Oven.class, ovenService));
    }
}
