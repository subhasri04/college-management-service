package in.gopal.college.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import in.gopal.college.dto.StudentDto;
import in.gopal.college.entity.Student;
import in.gopal.college.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	
	private final StudentRepository studentRepository;
	private final ModelMapper mapper;

	@Override
	public StudentDto createStudent(StudentDto dto) {
		Student student = mapper.map(dto, Student.class);
		Student saved = studentRepository.save(student);
		return mapper.map(saved, StudentDto.class);
	}

	@Override
	public StudentDto getStudentById(Long id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Student Id Not found"));
		return mapper.map(student, StudentDto.class);
	}

	@Override
	public List<StudentDto> getAllStudent() {
		
		return studentRepository.findAll()
				.stream()
				.map(student -> mapper.map(student, StudentDto.class))
				.toList();
	}

	@Override
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
		
	}

}
