package com.courseproject.service.impl;

import com.courseproject.dao.ApplianceDao;
import com.courseproject.service.ApplianceService;
import com.courseproject.model.Appliance;

import java.util.List;

public class ApplianceServiceImpl<T extends Appliance> implements ApplianceService<T> {
    private final ApplianceDao<T> dao;

    public ApplianceServiceImpl(ApplianceDao<T> dao) {
        this.dao = dao;
    }

    @Override
    public List<T> listAll() {
        return dao.findAll();
    }
}
