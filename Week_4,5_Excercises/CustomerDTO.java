package com.example.bookstoreapi.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;
import lombok.Data;

@Data
public class CustomerDTO extends RepresentationModel<CustomerDTO>
{
    private Long id;

    @NotNull(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    private Long id;
    private String name;
    private String email;
}
