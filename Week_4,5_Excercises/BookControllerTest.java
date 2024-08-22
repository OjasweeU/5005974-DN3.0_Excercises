package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.repository.BookRepository;
import com.example.bookstoreapi.mapper.BookMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookMapper bookMapper;

    private Book book;
    private BookDTO bookDTO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setId(1L);
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setPrice(45.0);
        book.setIsbn("9780134685991");

        bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Effective Java");
        bookDTO.setAuthor("Joshua Bloch");
        bookDTO.setPrice(45.0);
        bookDTO.setIsbn("9780134685991");
    }

    @Test
    public void testGetBookById() throws Exception {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(bookDTO);

        mockMvc.perform(get("/books/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Effective Java"))
                .andExpect(jsonPath("$.author").value("Joshua Bloch"));
    }

    @Test
    public void testCreateBook() throws Exception {
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toEntity(bookDTO)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDTO);

        String bookJson = "{\"title\":\"Effective Java\",\"author\":\"Joshua Bloch\",\"price\":45.0,\"isbn\":\"9780134685991\"}";

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Effective Java"))
                .andExpect(jsonPath("$.author").value("Joshua Bloch"));
    }
}
