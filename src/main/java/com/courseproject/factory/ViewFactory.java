package com.courseproject.factory;

import com.courseproject.view.View;

public class ViewFactory {
    private static View view;

    private ViewFactory(){}

    public static void init(View v) { view = v; }
    public static View getInstance() { return view; }
}
