package com.example.demo.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import javax.annotation.Generated;

import org.junit.Before;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.BookServiceImpl;

@Generated(value = "org.junit-tools-1.1.0")
@EnableJpaRepositories
public class BookControllerTest {

	@InjectMocks
	BookService bookService;

	@Mock
	BookRepository bookRepository;

	private BookController createTestSubject() {
		return new BookController();
	}

	@MethodRef(name = "getAllData", signature = "()QList<QBook;>;")
	@Test
	public void testGetAllData() throws Exception {
		BookController testSubject;
		List<Book> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getAllData();
	}

	@MethodRef(name = "saveData", signature = "(QBook;)QBook;")
	@Test
	public void testSaveData() throws Exception {
		BookController testSubject;
		Book book = null;
		Book result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.saveData(book);
	}

	@MethodRef(name = "updateById", signature = "(QBook;)QBook;")
	@Test
	public void testUpdateById() throws Exception {
		BookController testSubject;
		Book book = null;
		Book result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.updateById(book);
	}

	@MethodRef(name = "getByName", signature = "(QString;)QOptional<QBook;>;")
	@Test
	public void testGetByName() throws Exception {
		BookController testSubject;
		String name = "social";
		Optional<Book> result;

		bookRepository = mock(BookRepository.class);
		when(bookRepository.findByName(name));
		System.out.println(when(bookRepository.findByName(name)));
		// BookServiceImpl bookServiceImpl = new BookServiceImpl(bookRepository);

		// default test
		testSubject = createTestSubject();
		result = bookService.findByName(name);
		// result= bookRepository.findByName(name);
		System.out.println("Result " + result);
		// result = testSubject.getByName(name);
	}

	@MethodRef(name = "getById", signature = "(I)QBook;")
	@Test
	public void testGetById() throws Exception {
		BookController testSubject;
		int id = 0;
		Book result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getById(id);
	}

	@MethodRef(name = "deleteById", signature = "(I)V")
	@Test
	public void testDeleteById() throws Exception {
		BookController testSubject;
		int id = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.deleteById(id);
	}
}