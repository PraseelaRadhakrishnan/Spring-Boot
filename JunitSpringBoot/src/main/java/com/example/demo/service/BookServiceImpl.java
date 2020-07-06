package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository2) {
		this.bookRepository = bookRepository2;
	}

	public List<Book> findAll() {
		List<Book> list = bookRepository.findAll();
		System.out.println(list);
		return list;
	}

	public Book save(Book book) {
		Book data = bookRepository.save(book);
		return data;
	}

	public Optional<Book> findByName(String name) {
		System.out.println(name);
		Optional<Book> data = bookRepository.findByName(name);
		System.out.println("After data " + data);
		return data;
	}

	public Book findById(int id) {
		System.out.println("service" + id);
		Book data = bookRepository.findById(id);
		System.out.println("DB" + data);
		return data;
	}

	public void deleteById(int id) {
		bookRepository.deleteById(id);
	}

	public Book update(Book book) {
		Book data = bookRepository.save(book);
		return data;
	}

}
