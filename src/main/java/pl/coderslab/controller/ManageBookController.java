package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Book;
import pl.coderslab.repository.BookRepository;
import pl.coderslab.service.BookService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/save")
    public String saveBook(Model model) {
        model.addAttribute("book", new Book());
        return "/books/save";
    }

    @PostMapping("/save")
    public String getForm(@Valid final Book book, final BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            return "books/save";
        }
        bookRepository.save(book);
        return "redirect:all";
    }

    @GetMapping("/get/{id}")
    public String showPosts(Model model, @PathVariable long id) {
        model.addAttribute("book", bookService.get(id).orElseThrow(EntityNotFoundException::new));
        return "/books/getone";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable long id) {
        bookService.delete(id);
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "/books/all";
    }

    @GetMapping("/update/{id}")
    public String editById(@PathVariable long id, Model model){
        model.addAttribute("book", bookService.get(id));
        return "books/update";
    }

    @PostMapping("/update")
    public String editBook(@Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "books/update";
        }
        bookService.add(book);
        return "redirect:/admin/books/all";
    }

}

