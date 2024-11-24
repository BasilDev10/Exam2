package com.example.exam2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    @NotEmpty(message = "Error: id is empty!")
    @Size(min = 4 ,message = "Error: id length more 4")
    private String id;
    @NotEmpty(message = "Error: name is empty!")
    @Size(min = 4 ,message = "Error: name length more 4")
    private String name;
    @NotNull(message = "Error: number pages is null")
    private int number_of_pages;
    @NotNull(message = "Error: price is null")
    private double price;
    @NotEmpty(message = "Error: name is empty!")
    @Pattern(regexp = "novel|academic" , message = "Error: category enter only novel or academic")
    private String category;
    @NotNull(message = "Error: isAvailable is null")
    private boolean isAvailable;
}
