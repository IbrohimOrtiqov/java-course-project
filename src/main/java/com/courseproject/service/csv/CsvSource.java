package com.courseproject.service.csv;

import java.util.List;

public interface CsvSource<T> {
    List<T> readAll();
}
