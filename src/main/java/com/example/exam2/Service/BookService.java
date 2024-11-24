package com.example.exam2.Service;

import com.example.exam2.Model.Book;
import com.example.exam2.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BookService {


    ArrayList<Book> books = new ArrayList<>();

    private final UserService userService;


    public ArrayList<Book> getBooks(){
        return books;
    }
    public ArrayList<Book> getBooksCategory( String category){
        ArrayList<Book> bookFound = new ArrayList<>();

        for (Book book : books){
            if (book.getCategory().equalsIgnoreCase(category)){
                bookFound.add(book);
            }
        }
        return bookFound;
    }
    public ArrayList<Book> getBooksByNumPages( int numPages){
        ArrayList<Book> bookFound = new ArrayList<>();

        for (Book book : books){
            if (book.getNumber_of_pages() >= numPages){
                bookFound.add(book);
            }
        }
        return bookFound;
    }

    public Book getBooks(String name){
        for (Book book : books){
            if (book.getName().equalsIgnoreCase(name)){
                return book;
            }
        }
        return null;
    }



    public void addBook(Book book){
        books.add(book);
    }

    public boolean updateBook(String id , Book book){

        for (int i = 0 ; i < books.size(); i++){
            if(books.get(i).getId().equalsIgnoreCase(id)){
                books.set(i,book);
                return true;
            }
        }
        return false;
    }

    public int updateBookStatus(String id ,String userId){


        User user = userService.getByUserId(userId);

        if(user != null){
            if(!user.getUserRole().equalsIgnoreCase("libraian"))return 2;
        }else return 4;

        for (int i = 0 ; i < books.size(); i++){
            if(books.get(i).getId().equalsIgnoreCase(id)){
                books.get(i).setAvailable(false);
                return 1;
            }
        }
        return 0;
    }

    public boolean deleteBook(String id ){

        for (int i = 0 ; i < books.size(); i++){
            if(books.get(i).getId().equalsIgnoreCase(id)){
                books.remove(i);
                return true;
            }
        }
        return false;
    }
}
