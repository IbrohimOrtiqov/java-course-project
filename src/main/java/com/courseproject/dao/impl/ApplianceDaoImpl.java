package com.courseproject.dao.impl;

import com.courseproject.dao.ApplianceDao;
import com.courseproject.model.Appliance;
import com.courseproject.service.csv.CsvSource;

import java.util.List;
import java.util.stream.Collectors;

public class ApplianceDaoImpl<T extends Appliance> implements ApplianceDao<T> {
    private final CsvSource<T> source;

    public ApplianceDaoImpl(CsvSource<T> source) {
        this.source = source;
    }

    @Override
    public List<T> findAll() {
        return source.readAll();
    }
}
