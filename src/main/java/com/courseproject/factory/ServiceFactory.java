package com.courseproject.factory;

import com.courseproject.model.Appliance;
import com.courseproject.service.ApplianceService;

import java.util.Map;

public class ServiceFactory {
    private static Map<Class<? extends Appliance>, ApplianceService<? extends Appliance>> map;

    private ServiceFactory() {
    }

    public static void init(Map<Class<? extends Appliance>, ApplianceService<? extends Appliance>> svcMap) {
        map = svcMap;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Appliance> ApplianceService<T> get(Class<T> cls) {
        return (ApplianceService<T>) map.get(cls);
    }
}