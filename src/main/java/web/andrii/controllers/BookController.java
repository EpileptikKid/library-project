package web.andrii.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.andrii.dao.BookDAO;
import web.andrii.models.Book;
import web.andrii.models.Person;


@Controller
@RequestMapping("/library")
public class BookController {
    private final BookDAO bookDAO;
    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping("/books")
    public String index(Model model) {
        model.addAttribute("books", bookDAO.selectAllBook());
        return "library/books/index";
    }

    @GetMapping("books/new-book")
    public String newBook(@ModelAttribute("book") Book book) {
        return "library/books/new";
    }

    @PostMapping("/create-book")
    public String create(@ModelAttribute("book") Book book) {
        bookDAO.save(book);
        return "redirect:/library/books";
    }

    @GetMapping("/books/{id}")
    public String personPage(@PathVariable("id") int id, Model model, @ModelAttribute("reader") Person reader) {
        model.addAttribute("book", bookDAO.getBookById(id));
        model.addAttribute("people_id_list",bookDAO.getPeopleIdList());
        return "library/books/book";
    }

    @PostMapping("/books/{id}/set-free")
    public String setStatusBookIsFree(@PathVariable("id") int id) {
        bookDAO.setStatusBookIsFreeById(id);
        return "redirect:/library/books/" + id;
    }

    @PostMapping("/books/{id}/set-reader")
    public String setReader(@PathVariable("id") int id, @ModelAttribute("reader") Person reader) {
        bookDAO.setReader(id, reader);
        return "redirect:/library/books/" + id;
    }

    @PostMapping("/books/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "library/books/edit";
    }

    @PostMapping("/books/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/library/books";
    }

    @PostMapping("/books/{id}")
    public String setNewDataPerson(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        bookDAO.update(id, book);
        return "redirect:/library/books";
    }
}
