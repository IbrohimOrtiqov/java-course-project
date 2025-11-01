package com.courseproject.view;

import com.courseproject.model.Appliance;

import java.util.List;

public interface View {
    void start();
    void crash();
    void showMessage(String msg);
    void showAppliances(List<? extends Appliance> list);
}
