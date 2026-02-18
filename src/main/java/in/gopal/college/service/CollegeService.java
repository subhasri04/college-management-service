package in.gopal.college.service;

import java.util.List;

import in.gopal.college.dto.CollegeDto;
import org.springframework.data.domain.Page;


public interface CollegeService {
	
	CollegeDto createCollege(CollegeDto dto);
	Page<CollegeDto> getCollegeWithPagination(int page, int size);
	CollegeDto getCollegeById(Long id);
	List<CollegeDto> getAllColleges();
	void deleteCollege(Long id);

}
