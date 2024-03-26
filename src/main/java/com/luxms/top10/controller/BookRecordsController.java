package com.luxms.top10.controller;

import com.luxms.top10.domain.BookRecord;
import com.luxms.top10.services.BookRecordsService;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BookRecordsController {

    private final BookRecordsService bookRecordsService;

    @GetMapping
    public List<BookRecord> filter(
            @RequestParam(required = false) String year,
            @RequestParam(name = "column") @NotNull @NotBlank String column,
            @RequestParam(name = "sort") @NotBlank @NotNull String sort) {
        return bookRecordsService.filter(year, column, sort);
    }
}
