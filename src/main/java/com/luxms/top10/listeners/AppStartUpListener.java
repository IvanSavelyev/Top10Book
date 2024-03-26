package com.luxms.top10.listeners;

import com.luxms.top10.domain.BookRecord;
import com.luxms.top10.repository.BookRecordStorage;
import com.luxms.top10.services.CsvReader;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppStartUpListener {

    private final BookRecordStorage bookRecordStorage;

    private final CsvReader csvReader;

    @EventListener(ApplicationStartedEvent.class)
    private void startUpEvent() throws IOException {
        bookRecordStorage.initStorage(csvReader.readCsv(BookRecord.class));
    }
}
