package in.gopal.college.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.gopal.college.dto.CollegeDto;
import in.gopal.college.entity.College;
import in.gopal.college.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;


@Service
@RequiredArgsConstructor
public class CollegeServiceImpl implements CollegeService{

	private final CollegeRepository collegeRepository;
	private final ModelMapper mapper;
	//private static final Logger logger = LoggerFactory.getLogger(CollegeServiceImpl.class);
	
	@Override
	public CollegeDto createCollege(CollegeDto dto) {
		College college = mapper.map(dto,College.class);
		College saved = collegeRepository.save(college);
		
		return mapper.map(saved, CollegeDto.class);
	}
	
	@Override
	public Page<CollegeDto> getCollegeWithPagination(int page, int size){
		Page<College> colleges = collegeRepository.findAll(PageRequest.of(page, size));
		return colleges.map(college -> mapper.map(college, CollegeDto.class));
		
	}
	
	@Override
	public CollegeDto getCollegeById(Long id) {
		College college = collegeRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("College not found"));
		return mapper.map(college, CollegeDto.class);
	}
	@Override
	public List<CollegeDto> getAllColleges() {
		
		return collegeRepository.findAll()
				.stream()
				.map(college -> mapper.map(college, CollegeDto.class))
				.toList();
	}
	@Override
	public void deleteCollege(Long id) {
		collegeRepository.deleteById(id);
		
	}
	
	
}
