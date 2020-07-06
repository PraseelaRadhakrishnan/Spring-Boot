package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

@Generated(value = "org.junit-tools-1.1.0")
public class BookControllerTest {

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
		String name = "";
		Optional<Book> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getByName(name);
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