package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.polytec.vermeg.controller.BookController;
import org.polytec.vermeg.model.Book;
import org.polytec.vermeg.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class TestBookController {
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private BookService mockedBookService;
	@InjectMocks
	private BookController bookController;
	Book book;
	Book book1;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetBooks() throws Exception {

		java.sql.Date Date=new java.sql.Date(2020-01-02);
		java.sql.Date Date2=new java.sql.Date(2020-01-02);

		book = new Book(1,"aa","bb",45.2,Date);
		book1 = new Book(2,"aa","bb",45.2,Date2);
		List <Book>list= new ArrayList<Book>();
		list.add(book);
		list.add(book1);
		when(mockedBookService.getAllBooks()).thenReturn(list);
		this.mockMvc.perform(get("/api/book/getAll/"))
		.andExpect(status().isOk())
		.andDo(print());
	}

@Test
void testGetBookById() throws Exception {
	java.sql.Date Date=new java.sql.Date(2020-01-02);

	book = new Book(1,"aa","bb",45.2,Date);
	int bookid=book.getId();
	when(mockedBookService.getBook(bookid)).thenReturn(book);
	this.mockMvc.perform(get("/api/book/getBook/"+bookid))
	.andExpect(status().isOk())
	.andDo(print());

	
	
}

	@Test
	void testAddBook() throws Exception  {
		java.sql.Date Date=new java.sql.Date(2020-06-02);
book = new Book(3,"cc","dd",44.6,Date);
mockedBookService.addBook(book);
verify(mockedBookService,times(1)).addBook(book);
this.mockMvc.perform(post("/api/book/addBook"))

.andExpect(status().isOk())
.andDo(print());

	}
	

@Test
void testUpdateBook() throws Exception {
	java.sql.Date Date=new java.sql.Date(2020-06-02);
	book = new Book(3,"cc","dd",44.6,Date);
	int bookid = book.getId();
	mockedBookService.updateBook(book);
	verify(mockedBookService,times(1)).updateBook(book);
	this.mockMvc.perform(put("/api/book/updateBook/"+bookid))

	.andExpect(status().isOk())
	.andDo(print());
	
	
	}
//
@Test
void testDeleteBook() throws Exception {
	java.sql.Date Date=new java.sql.Date(2020-06-02);
	book = new Book(4,"cc","dd",44.6,Date);
	int bookid = book.getId();
	mockedBookService.deleteBook(bookid);;
	verify(mockedBookService,times(1)).deleteBook(bookid);
	this.mockMvc.perform(delete("/api/book/deleteBook/"+bookid))

	.andExpect(status().isOk())
	.andDo(print());	
}
@Test  
void testCalculatePrice () throws Exception {			
			int idbook = 5 ;
			java.sql.Date Date=new java.sql.Date(2020-06-02);

			Book book=new Book(5, "l'amour", "med bakk",47 ,Date); 
			when(mockedBookService.getBook(idbook)).thenReturn(book ) ;
			 mockMvc.perform(get("/api/book/calculprice/5/20")).andExpect(status().isOk()).andDo(print());
			
			
		}
@Test  
void testSommeTotale () throws Exception {			
			java.sql.Date Date=new java.sql.Date(2020-06-02);

			Book book=new Book(5, "ff", "bb",30 ,Date); 
			Book book1=new Book(6, "aa", "zz",31 ,Date); 
			Book book2=new Book(7, "nn", "qq",32,Date); 

			List <Integer>list= new ArrayList<Integer>();
			list.add(book.getId());
			list.add(book1.getId());
			list.add(book2.getId());

			this.mockMvc.perform(post("/api/book/SommeTotal").contentType(MediaType.APPLICATION_JSON)
				    .content("{\"json\":\"request to be send\"}"))
				    .andExpect(status().isOk());
				    assertEquals(bookController.calSommePrixTotal(list),93);

			
		}
}
