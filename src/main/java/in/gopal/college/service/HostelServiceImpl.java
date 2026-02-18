package in.gopal.college.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import in.gopal.college.dto.HostelDto;
import in.gopal.college.entity.Hostel;
import in.gopal.college.repository.HostelRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HostelServiceImpl implements HostelService{

	private final HostelRepository hostelRepository;
	private final ModelMapper mapper;
	@Override
	public HostelDto createHostelEntry(HostelDto dto) {
		Hostel hostel = mapper.map(dto, Hostel.class);
		Hostel saved = hostelRepository.save(hostel);
		return mapper.map(saved, HostelDto.class);
	}

	@Override
	public HostelDto getHostelerById(Long id) {
		
		Hostel hostel = hostelRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Hosteler not found with this id"));
		return mapper.map(hostel, HostelDto.class);
	}

	@Override
	public List<HostelDto> getAllHosteler() {
		
		return hostelRepository.findAll()
				.stream()
				.map(hostel -> mapper.map(hostel, HostelDto.class))
				.toList();
	}

	@Override
	public void deleteHostelerById(Long id) {
		hostelRepository.deleteById(id);
		
	}

}
