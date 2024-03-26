package com.luxms.top10.domain;

import com.luxms.top10.converters.IntegerConverter;
import com.luxms.top10.converters.LocalDateConverter;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import java.time.LocalDate;
import lombok.Data;

@Data
public class BookRecord {
    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByPosition(position = 1)
    private String isbn;

    @CsvBindByPosition(position = 2)
    private String title;

    @CsvBindByPosition(position = 3)
    private String seriesTitle;

    @CsvBindByPosition(position = 4)
    private String seriesReleaseNumber;

    @CsvBindByPosition(position = 5)
    private String authors;

    @CsvBindByPosition(position = 6)
    private String publisher;

    @CsvBindByPosition(position = 7)
    private String language;

    @CsvBindByPosition(position = 8)
    private String description;
//    @CsvBindByPosition(position = 9)

    @CsvCustomBindByPosition(position = 9, converter = IntegerConverter.class)
    private Integer numPages;

    @CsvBindByPosition(position = 10)
    private String format;

    @CsvBindByPosition(position = 11)
    private Object genres;

    @CsvCustomBindByPosition(position = 12, converter = LocalDateConverter.class)
    private LocalDate publicationDate;

    @CsvBindByPosition(position = 13)
    private Float ratingScore;

    @CsvBindByPosition(position = 14)
    private Float numRatings;

    @CsvBindByPosition(position = 15)
    private Float numReviews;

    @CsvBindByPosition(position = 16)
    private Float currentReaders;

    @CsvBindByPosition(position = 17)
    private Float wantToRead;

    @CsvBindByPosition(position = 18)
    private Float price;

    @CsvBindByPosition(position = 19)
    private String url;

    public BookRecord() {
    }
}
