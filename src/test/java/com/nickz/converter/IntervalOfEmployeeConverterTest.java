package com.nickz.converter;

import com.nickz.converter.IntervalOfEmployeeConverter;
import com.nickz.entity.IntervalOfEmployee;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntervalOfEmployeeConverterTest {

    private static final IntervalOfEmployeeConverter converter = new IntervalOfEmployeeConverter();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private Date parseDate(String dateString) throws ParseException {
        return dateFormat.parse(dateString);
    }

    @Test
    void testConvertJsonToListWithValidJson() throws ParseException {
        String jsonString = "[{\"id\":1234,\"dateStart\":\"2023-03-23T10:25:08\",\"dateEnd\":\"2023-03-23T15:35:18\"},{\"id\":4535,\"dateStart\":\"2023-03-23T10:25:08\",\"dateEnd\":\"2023-03-23T15:35:18\"}]";

        List<IntervalOfEmployee> result = IntervalOfEmployeeConverter.convertJsonToList(jsonString);

        assertEquals(2, result.size());
        assertEquals(1234, result.get(0).getId());
        assertEquals(parseDate("2023-03-23T13:25:08"), result.get(0).getDateStart());
        assertEquals(parseDate("2023-03-23T18:35:18"), result.get(0).getDateEnd());
        assertEquals(4535, result.get(1).getId());
        assertEquals(parseDate("2023-03-23T13:25:08"), result.get(1).getDateStart());
        assertEquals(parseDate("2023-03-23T18:35:18"), result.get(1).getDateEnd());
    }


    @Test
    void testConvertJsonToListWithInvalidJson() {

        String jsonString = "invalidJson";
        assertThrows(RuntimeException.class, () -> IntervalOfEmployeeConverter.convertJsonToList(jsonString));
    }

    @Test
    void testConvertJsonToListWithEmptyJsonArray() {

        String jsonString = "[]";
        List<IntervalOfEmployee> result = IntervalOfEmployeeConverter.convertJsonToList(jsonString);
        assertEquals(0, result.size());
    }
}