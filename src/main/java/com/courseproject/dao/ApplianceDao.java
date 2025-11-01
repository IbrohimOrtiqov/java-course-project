package com.courseproject.dao;

import com.courseproject.model.Appliance;
import java.util.List;

public interface ApplianceDao<T extends Appliance> {
    List<T> findAll();
}
