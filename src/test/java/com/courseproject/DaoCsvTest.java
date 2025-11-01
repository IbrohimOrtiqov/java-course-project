package com.courseproject;

import com.courseproject.dao.ApplianceDao;
import com.courseproject.dao.impl.ApplianceDaoImpl;
import com.courseproject.dao.straight.LaptopCsvSourceImpl;
import com.courseproject.model.Laptop;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DaoCsvTest {
    @Test
    public void testLaptopCsvRead() {
        LaptopCsvSourceImpl src = new LaptopCsvSourceImpl("laptops.csv");
        List<Laptop> list = src.readAll();
        assertFalse(list.isEmpty(), "Laptop list should not be empty");
    }
}
