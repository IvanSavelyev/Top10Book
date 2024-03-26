package com.luxms.top10.domain;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Operations<T> {

    private Predicate<T> filterPredicate;

    private Comparator<T> bookComparator;

    public Stream<T> asStream(boolean isReverse, List<T> from) {
        if (isReverse) {
            bookComparator = bookComparator.reversed();
        }
        return from.stream().filter(filterPredicate).sorted(bookComparator);
    }

    public Operations<BookRecord> getDefault() {
        return new Operations<>(it -> it.getId() != null, Comparator.comparing(BookRecord::getId));
    }

    public Stream<T> asStream(boolean isReverse, Stream<T> from) {
        if (isReverse) {
            bookComparator = bookComparator.reversed();
        }
        return from.filter(filterPredicate).sorted(bookComparator);
    }
}
