package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.mapper.BookMapper;
import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Operation(summary = "Get all books", description = "Retrieve a list of all books available in the bookstore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get a book by ID", description = "Retrieve a book by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);

        return book.map(b -> ResponseEntity.ok(bookMapper.toDto(b)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new book", description = "Add a new book to the bookstore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(bookMapper.toDto(savedBook));
    }
}
