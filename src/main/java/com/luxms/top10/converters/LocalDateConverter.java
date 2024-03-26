package com.luxms.top10.converters;

import com.opencsv.bean.AbstractBeanField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateConverter extends AbstractBeanField<LocalDate, String> {

    @Override
    protected LocalDate convert(String s) {
        if (s == null || s.isEmpty() || s.isBlank()) {
            return null;
        }
        var dateFormatter = DateTimeFormatter.ofPattern("MMMM d, u", Locale.ENGLISH);
        try {
            return LocalDate.parse(s, dateFormatter);
        } catch (Exception e) {
            return null;
        }
    }
}
