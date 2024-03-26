package com.luxms.top10.converters;

import com.opencsv.bean.AbstractBeanField;

public class IntegerConverter extends AbstractBeanField<Integer, String> {

    @Override
    protected Integer convert(String s) {
        if (s == null || s.isEmpty() || s.isBlank()) {
            return null;
        }
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }
}
