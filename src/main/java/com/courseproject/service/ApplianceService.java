package com.courseproject.service;

import com.courseproject.model.Appliance;

import java.util.List;

public interface ApplianceService<T extends Appliance> {
    List<T> listAll();
}
