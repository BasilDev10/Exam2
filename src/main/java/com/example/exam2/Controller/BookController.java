package com.example.exam2.Controller;


import com.example.exam2.ApiResponse.ApiResponse;
import com.example.exam2.Model.Book;
import com.example.exam2.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping("/get")
    public ResponseEntity getBooks(){

        return ResponseEntity.ok(bookService.getBooks());
    }
    @GetMapping("/get-by-name/{name}")
    public ResponseEntity getBooks(@PathVariable String name){

        Book book = bookService.getBooks(name);
        if(book != null) return ResponseEntity.ok(book);
        else return ResponseEntity.status(400).body(new ApiResponse("Book not found"));
    }
    @GetMapping("/get-by-category/{category}")
    public ResponseEntity getBooksByCategory(@PathVariable String category){

       return ResponseEntity.ok(bookService.getBooksCategory(category));
    }

    @GetMapping("/get-by-number-pages/{numPages}")
    public ResponseEntity getBooksByCategory(@PathVariable int numPages){

        return ResponseEntity.ok(bookService.getBooksByNumPages(numPages));
    }
    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody @Valid Book book , Errors errors){

        if (errors.hasErrors()) return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        bookService.addBook(book);

        return ResponseEntity.status(201).body(new ApiResponse("book is added"));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(@PathVariable String id ,@RequestBody @Valid Book book , Errors errors){
        if (errors.hasErrors()) return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        if(bookService.updateBook(id,book)) return ResponseEntity.ok(new ApiResponse("Book is updated"));
        else return ResponseEntity.status(400).body(new ApiResponse("book not found"));

    }
    @PutMapping("/update-status/{id}/{userID}")
    public ResponseEntity updateBookStatus(@PathVariable String id , @PathVariable String userID){

        int Result = bookService.updateBookStatus(id,userID);
        if( Result== 1) return ResponseEntity.ok(new ApiResponse("Book is status updated"));
        else if(Result == 2) return ResponseEntity.status(400).body(new ApiResponse("Error: user doesn't have role to update"));
        else if(Result == 4) return ResponseEntity.status(400).body(new ApiResponse("Error: user not found"));
        else return ResponseEntity.status(400).body(new ApiResponse("book not found"));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable String id ){


        if(bookService.deleteBook(id)) return ResponseEntity.ok(new ApiResponse("Book is deleted"));
        else return ResponseEntity.status(400).body(new ApiResponse("book not found"));

    }

}
