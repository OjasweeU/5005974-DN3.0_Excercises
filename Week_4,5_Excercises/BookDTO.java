package com.example.bookstoreapi.dto;

import org.springframework.hateoas.RepresentationModel;
import lombok.Data;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BookDTO extends RepresentationModel<BookDTO> {

    private Long id;

    @NotNull(message = "Title is mandatory")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotNull(message = "Author is mandatory")
    @Size(min = 2, max = 50, message = "Author must be between 2 and 50 characters")
    private String author;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private double price;

    @NotNull(message = "ISBN is mandatory")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
    private String isbn;

    package com.example.bookstoreapi.dto;




    private Long id;
    private String title;
    private String author;
    private double price;
    private String isbn;
}

