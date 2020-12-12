package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.polytec.vermeg.dao.BookDAO;
import org.polytec.vermeg.model.Book;


class TestBookService {
	Book book;
	Book book1;
	BookDAO bookdao = mock(BookDAO.class);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllBooks() {
		java.sql.Date Date=new java.sql.Date(2020-01-02);
		java.sql.Date Date1=new java.sql.Date(2020-01-02);

	

		 List<Book> books= new ArrayList<Book>();
			Book book =new Book(1,"aa","bb",45.2,Date);
			Book book1 = new Book(2,"cc","dd",45.2,Date1);
			 books.add(book);
			 books.add(book1);
			 when(bookdao.getAllBooks()).thenReturn(books);
			 assertEquals(2,books.size());
			 assertEquals("aa",books.get(0).getTitle());
		     assertEquals("cc",books.get(1).getTitle());
			 assertEquals(bookdao.getAllBooks() , books);
	}

	@Test
	void testGetBook() {
		java.sql.Date Date=new java.sql.Date(2020-01-02);

		Book book1 =new Book(1,"aa","bb",45.2,Date);
		int bookid = book1.getId();
		when(bookdao.getBook(bookid)).thenReturn(book1);
		assertEquals(1,book1.getId());
		assertTrue(bookdao.getBook(bookid)==book1);
		
	}

	@Test
	void testAddBook() {
		java.sql.Date Date=new java.sql.Date(2020-01-02);

		Book book1 =new Book(4,"aa","bb",45.2,Date);
		bookdao.addBook(book1);
		verify(bookdao,times(1)).addBook(book1)	;}

	@Test
	void testUpdateBook() {
		java.sql.Date Date=new java.sql.Date(2020-01-02);

		Book book1 =new Book(4,"hh","bb",43.2,Date);
		bookdao.updateBook(book1);
		verify(bookdao,times(1)).updateBook(book1);		}

	@Test
	void testDeleteBook() {
		java.sql.Date Date=new java.sql.Date(2020-01-02);

		Book book1 =new Book(4,"hh","bb",43.2,Date);
		int id = book1.getId();
		bookdao.deleteBook(id);
		verify(bookdao,times(1)).deleteBook(id);;		}

}
