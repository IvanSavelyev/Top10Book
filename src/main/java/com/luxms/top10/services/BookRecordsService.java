package com.luxms.top10.services;

import com.luxms.top10.controller.exceptions.AppException;
import com.luxms.top10.domain.BookRecord;
import com.luxms.top10.repository.BookRecordStorage;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookRecordsService {

    private final BookRecordStorage storage;

    private final BookRecordsFieldsSelector selector;

    @Value("${app.dataset.limit}")
    private Integer limit;

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
        var operations = selector.getSelector().get(column);
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
        if (!selector.getSelector().containsKey(column)) {
            throw new AppException(
                    HttpStatus.BAD_REQUEST,
                    "This column not present in dataset.\n Use this set of columns: "
                            + String.join(",", selector.getFields())
            );
        }
        if (!selector.getSort().contains(sort.toLowerCase())) {
            throw new AppException(
                    HttpStatus.BAD_REQUEST,
                    "This sort type invalid.\n Use this set of types: "
                            + String.join(",", selector.getSort())
            );
        }
    }
}
