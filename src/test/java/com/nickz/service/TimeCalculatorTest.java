package com.nickz.service;

import com.nickz.entity.IntervalOfEmployee;
import com.nickz.service.TimeCalculator;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TimeCalculatorTest {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Test
    void testCalcWithValidData() throws ParseException {


        List<IntervalOfEmployee> intervalOfEmployees = Arrays.asList(
                new IntervalOfEmployee(1234, dateFormat.parse("2023-03-23T10:25:08"), dateFormat.parse("2023-03-23T15:35:18")),
                new IntervalOfEmployee(4535, dateFormat.parse("2023-03-23T10:25:08"), dateFormat.parse("2023-03-23T15:35:18")),
                new IntervalOfEmployee(1234, dateFormat.parse("2023-03-24T08:25:08"), dateFormat.parse("2023-03-24T19:00:18"))
        );

        String result = TimeCalculator.calc(intervalOfEmployees);

        assertEquals("{\"id\":1234,\"time\":\"15:45:20\"}", result);
    }


}