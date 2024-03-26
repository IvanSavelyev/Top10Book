package com.luxms.top10.services;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class CsvReader {

    @Value("${app.dataset.filename}")
    private String path;

    public <T> List<T> readCsv(Class<T> clazz) throws IOException {
        try (Reader reader = new InputStreamReader(new ClassPathResource(path).getInputStream())) {
            return new CsvToBeanBuilder<T>(reader)
                    .withType(clazz)
                    .withSkipLines(1)
                    .build()
                    .parse();
        }
    }
}
