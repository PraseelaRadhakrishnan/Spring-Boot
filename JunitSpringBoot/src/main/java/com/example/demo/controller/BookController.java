package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import com.example.demo.service.BookServiceImpl;

@RestController
public class BookController {
	@Autowired
	BookService bookService;

	@GetMapping("/Book")
	public List<Book> getAllData() {
		List<Book> list = bookService.findAll();
		return list;

	}

	@PostMapping("/Book")
	public Book saveData(@RequestBody Book book) {
		Book data = bookService.save(book);
		return data;
	}

	@PutMapping("/Book")
	public Book updateById(@RequestBody Book book) {
		Book data = bookService.update(book);
		return data;

	}

	@GetMapping("/Book/{name}")
	public Optional<Book> getByName(@PathVariable("name") String name) {
		System.out.println("Name " + name);
		Optional<Book> data = bookService.findByName(name);
		System.out.println("After name " + name);
		return data;

	}

	@GetMapping("/Books/{id}")
	public Book getById(@PathVariable("id") int id) {
		System.out.println("cc" + id);
		Book data = bookService.findById(id);
		System.out.println("controller" + data);
		return data;

	}

	@DeleteMapping("/Book/{id}")
	public void deleteById(@PathVariable("id") int id) {
		bookService.deleteById(id);

	}

}
