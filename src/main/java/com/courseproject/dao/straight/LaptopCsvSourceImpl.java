package com.courseproject.dao.straight;

import com.courseproject.model.Laptop;
import com.courseproject.service.csv.CsvSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LaptopCsvSourceImpl implements CsvSource<Laptop> {
    private final String resourceName;

    public LaptopCsvSourceImpl(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public List<Laptop> readAll() {
        List<Laptop> res = new ArrayList<>();
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line = br.readLine(); // header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    res.add(new Laptop(parts[0].trim(), parts[1].trim(), Double.parseDouble(parts[2].trim())));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading " + resourceName, e);
        }
        return res;
    }
}
