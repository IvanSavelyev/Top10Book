package com.luxms.top10.services;

import com.luxms.top10.domain.BookRecord;
import com.luxms.top10.domain.Operations;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BookRecordsFieldsSelector {

    private final Map<String, Operations<BookRecord>> selector = new HashMap<>();

    private final List<String> sort = new ArrayList<>();

    public BookRecordsFieldsSelector() {
        sort.add("asc");
        sort.add("desc");
        selector.put("title", new Operations<>(it -> it.getTitle() != null, Comparator.comparing(BookRecord::getTitle)));
        selector.put("authors", new Operations<>(it -> it.getAuthors() != null, Comparator.comparing(BookRecord::getAuthors)));
        selector.put("numPages", new Operations<>(it -> it.getNumPages() != null, Comparator.comparing(BookRecord::getNumPages)));
        selector.put("publicationDate", new Operations<>(it -> it.getPublicationDate() != null, Comparator.comparing(BookRecord::getPublicationDate)));
        selector.put("ratingScore", new Operations<>(it -> it.getRatingScore() != null, Comparator.comparing(BookRecord::getRatingScore)));
        selector.put("numRatings", new Operations<>(it -> it.getNumRatings() != null, Comparator.comparing(BookRecord::getNumRatings)));
    }

    public Set<String> getFields() {
        return selector.keySet();
    }
}
