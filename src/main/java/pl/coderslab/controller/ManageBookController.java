package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.model.Book;
import pl.coderslab.repository.BookRepository;
import pl.coderslab.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/admin/books")
public class ManageBookController {


    private final BookService bookService;
    private final BookRepository bookRepository;

    public ManageBookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }


    @GetMapping("/all")
    public String showPosts(Model model) {
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "/books/all";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String getLandingPage(){

        System.out.println("test SPRING AND HIBERNATE");
        return "hello Spring MVC! :)";
    }





}

