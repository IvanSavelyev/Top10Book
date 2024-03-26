package com.luxms.top10.services;

import com.luxms.top10.controller.exceptions.AppException;
import com.luxms.top10.domain.BookRecord;
import com.luxms.top10.domain.Operations;
import com.luxms.top10.repository.BookRecordStorage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BookRecordsService {

    private final BookRecordStorage storage;

    @Value("${app.dataset.limit}")
    private Integer limit;

    private static final Map<String, Operations<BookRecord>> SELECTOR = new HashMap<>();

    private static final List<String> SORT = new ArrayList<>();

    static {
        SORT.add("asc");
        SORT.add("desc");
        SELECTOR.put("title", new Operations<>(it -> it.getTitle() != null, Comparator.comparing(BookRecord::getTitle)));
        SELECTOR.put("authors", new Operations<>(it -> it.getAuthors() != null, Comparator.comparing(BookRecord::getAuthors)));
        SELECTOR.put("numPages", new Operations<>(it -> it.getNumPages() != null, Comparator.comparing(BookRecord::getNumPages)));
        SELECTOR.put("publicationDate", new Operations<>(it -> it.getPublicationDate() != null, Comparator.comparing(BookRecord::getPublicationDate)));
        SELECTOR.put("ratingScore", new Operations<>(it -> it.getRatingScore() != null, Comparator.comparing(BookRecord::getRatingScore)));
        SELECTOR.put("numRatings", new Operations<>(it -> it.getNumRatings() != null, Comparator.comparing(BookRecord::getNumRatings)));
    }

    public BookRecordsService(BookRecordStorage storage) {
        this.storage = storage;
    }

    public List<BookRecord> filter(String year, String column, String sort) {
        preconditions(column, sort);
        var bookRecordStream = storage.getBookRecords().stream();
        if (year != null && !year.isBlank()) {
            checkIsDigit(year);
            Predicate<BookRecord> bookRecordPredicate = it -> it.getPublicationDate() != null
                    && it.getPublicationDate().getYear() == Integer.parseInt(year);
            bookRecordStream = bookRecordStream
                    .filter(bookRecordPredicate);
        }
        var operations = SELECTOR.get(column);
        var bookComparator = operations.getBookComparator();
        return bookRecordStream
                .filter(operations.getFilterPredicate())
                .sorted(sort.equalsIgnoreCase("asc") ? bookComparator : bookComparator.reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    private void checkIsDigit(String year) {
        if (!year.chars().allMatch(Character::isDigit)) {
            throw new AppException(
                    HttpStatus.BAD_REQUEST,
                    "Year parameter must be a digit"
            );
        }
    }

    private void preconditions(String column, String sort) {
        if (!SELECTOR.containsKey(column)) {
            throw new AppException(
                    HttpStatus.BAD_REQUEST,
                    "This column not present in dataset.\n Use this set of columns: "
                            + String.join(",", SELECTOR.keySet())
            );
        }
        if (!SORT.contains(sort.toLowerCase())) {
            throw new AppException(
                    HttpStatus.BAD_REQUEST,
                    "This sort type invalid.\n Use this set of types: "
                            + String.join(",", SORT)
            );
        }
    }
}
