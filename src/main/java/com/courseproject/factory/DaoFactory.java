package com.courseproject.factory;

import com.courseproject.dao.ApplianceDao;
import com.courseproject.model.Appliance;

import java.util.Map;

public class DaoFactory {
    private static Map<Class<? extends Appliance>, ApplianceDao<? extends Appliance>> map;

    private DaoFactory() {
    }

    public static void init(Map<Class<? extends Appliance>, ApplianceDao<? extends Appliance>> daoMap) {
        map = daoMap;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Appliance> ApplianceDao<T> get(Class<T> cls) {
        return (ApplianceDao<T>) map.get(cls);
    }
}