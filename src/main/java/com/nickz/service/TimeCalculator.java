package com.nickz.service;

import com.nickz.entity.IntervalOfEmployee;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class TimeCalculator {

    public static String calc(List<IntervalOfEmployee> intervalOfEmployees) {

        Map<Integer, List<IntervalOfEmployee>> employees = new HashMap<>();
        Date currentDate = new Date();

        for (IntervalOfEmployee intervalOfEmployee : intervalOfEmployees) {
            if (intervalOfEmployee.getDateStart().after(currentDate)) {
                throw new IllegalArgumentException("wrong date");
            }

            employees.computeIfAbsent(intervalOfEmployee.getId(), k -> new ArrayList<>()).add(intervalOfEmployee);
        }

        if (employees.isEmpty()) {
            throw new IllegalArgumentException("No valid employee data");
        }

        long maxTotalTimeMillis = 0;
        int maxEmployeeId = 0;

        for (Map.Entry<Integer, List<IntervalOfEmployee>> entry : employees.entrySet()) {
            long totalTimeMillis = calculateTotalTime(entry.getValue());
            if (totalTimeMillis > maxTotalTimeMillis) {
                maxTotalTimeMillis = totalTimeMillis;
                maxEmployeeId = entry.getKey();
            }
        }
        Map<String, Object> jsonResult = new HashMap<>();
        jsonResult.put("id", maxEmployeeId);
        jsonResult.put("time", formatTime(maxTotalTimeMillis));


        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }


    public static long calculateTotalTime(List<IntervalOfEmployee> intervalOfEmployeeData) {
        long totalTimeMillis = 0;

        for (IntervalOfEmployee entry : intervalOfEmployeeData) {
            totalTimeMillis += entry.getDateEnd().getTime() - entry.getDateStart().getTime();
        }

        return totalTimeMillis;
    }

    private static String formatTime(long millis) {
        long hours = millis / 3600000;
        long minutes = (millis % 3600000) / 60000;
        long seconds = (millis % 60000) / 1000;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
