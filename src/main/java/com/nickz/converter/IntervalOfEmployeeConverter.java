package com.nickz.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nickz.entity.IntervalOfEmployee;

import java.util.Arrays;
import java.util.List;

public class IntervalOfEmployeeConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<IntervalOfEmployee> convertJsonToList(String jsonString) {
        try {
            return Arrays.asList(objectMapper.readValue(jsonString, IntervalOfEmployee[].class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to List<IntervalOfEmployee>", e);
        }
    }
}