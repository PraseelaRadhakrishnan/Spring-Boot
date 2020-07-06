package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@Service
@Transactional
public interface BookService {

	public List<Book> findAll();

	public Book save(Book book);

	public Optional<Book> findByName(String name);

	public Book findById(int id);

	public void deleteById(int id);

	public Book update(Book book);

}
