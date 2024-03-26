package com.luxms.top10.repository;

import com.luxms.top10.domain.BookRecord;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@NoArgsConstructor
public class BookRecordStorage {

    private List<BookRecord> bookRecords;

    public void initStorage(List<BookRecord> bookRecords) {
        this.bookRecords = bookRecords;
    }
}
