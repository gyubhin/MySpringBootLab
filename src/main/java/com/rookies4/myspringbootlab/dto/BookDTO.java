package com.rookies4.myspringbootlab.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;


public class BookDTO {

    @Getter @Setter
    public static class BookCreateRequest {
        @NotBlank
        private String title;
        @NotBlank
        private String author;
        @NotBlank
        private String isbn;
        @NotNull
        private Integer price;
        @NotNull
        private LocalDate publishDate;
    }

    @Getter @Setter
    public static class BookUpdateRequest {
        private String title;
        private String author;
        private String isbn;
        private Integer price;
        private LocalDate publishDate;
    }

    @Getter @Setter @AllArgsConstructor
    public static class BookResponse {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private Integer price;
        private LocalDate publishDate;
    }
}
