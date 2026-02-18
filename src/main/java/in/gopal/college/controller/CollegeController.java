package in.gopal.college.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.gopal.college.dto.CollegeDto;
import in.gopal.college.service.CollegeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;


@RestController
@RequestMapping("/api/college")
@RequiredArgsConstructor
public class CollegeController {
	private final CollegeService collegeService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<CollegeDto> create(@Valid @RequestBody CollegeDto dto){
		System.out.println("I am college management");
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(collegeService.createCollege(dto));
				
	}
	
	@GetMapping("/pagination")
	public ResponseEntity<Page<CollegeDto>> getPage(
			@RequestParam int page,
			@RequestParam int size){
		return ResponseEntity.ok(collegeService.getCollegeWithPagination(page, size));		
	}
	@GetMapping("/{id}")
	public ResponseEntity<CollegeDto> getById(@PathVariable Long id){
		return ResponseEntity.ok(collegeService.getCollegeById(id));
	}

	@PreAuthorize("hasRole('ADMIN','USER')")
	@GetMapping
	public ResponseEntity<List<CollegeDto>> getAll(){
		return ResponseEntity.ok(collegeService.getAllColleges());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>delete(@PathVariable Long id){
		collegeService.deleteCollege(id);
		return ResponseEntity.ok("Delete Successfully");
	}
}
