package com.example.exam2.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "Error: id is empty!")
    @Size(min = 4 ,message = "Error: id length more 4")
    private String id;
    @NotEmpty(message = "Error: name is empty!")
    @Size(min = 4 ,message = "Error: name length more 4")
    private String name;
    @NotNull(message = "Error: age is null")
    @Positive(message = "Error: age should be positive")
    private int age;
    @NotNull(message = "Error: balance is null")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private int balance;
    @NotEmpty(message = "Error: userRole is empty!")
    @Pattern(regexp = "customer|libraian" , message = "Error: only allowed enter customer or libraian")
    private String userRole;

}
