package in.gopal.college.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import in.gopal.college.dto.RoomDto;
import in.gopal.college.entity.Room;
import in.gopal.college.repository.RoomRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

	private final RoomRepository roomRepository;
	private final ModelMapper mapper;
	@Override
	public RoomDto createRoom(RoomDto dto) {
		Room room = mapper.map(dto, Room.class);
		Room saved = roomRepository.save(room);
		return mapper.map(saved, RoomDto.class);
	}
	@Override
	public RoomDto getRoomById(Long id) {
		Room room = roomRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Room not found"));
		return mapper.map(room, RoomDto.class);
	}
	@Override
	public List<RoomDto> getAllRooms() {
		
		return roomRepository.findAll()
				.stream()
				.map(room-> mapper.map(room, RoomDto.class))
				.toList();
	}
	@Override
	public void deleteRoomById(Long id) {
		roomRepository.deleteById(id);
		
	}
	

}
