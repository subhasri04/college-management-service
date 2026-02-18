package in.gopal.college.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import in.gopal.college.dto.BookDto;
import in.gopal.college.entity.Book;
import in.gopal.college.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
	
	private final BookRepository bookRepository;
	private final ModelMapper mapper;
	
	@Override
	public BookDto createBook(BookDto dto) {
		Book book = mapper.map(dto, Book.class);
		Book saved = bookRepository.save(book);
		
		return mapper.map(saved, BookDto.class);
	}
	
	@Override
	public BookDto getBookById(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Book not found"));
		return mapper.map(book, BookDto.class);
	}
	
	@Override
	public List<BookDto> getAllBook() {
		
		return bookRepository.findAll()
				.stream()
				.map(book -> mapper.map(book, BookDto.class))
				.toList();
	}
	
	@Override
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
}
