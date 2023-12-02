package ai.viceversa.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ai.viceversa.demo.domain.Photo;
import ai.viceversa.demo.dto.PhotoFetchResponseDto;
import ai.viceversa.demo.mapper.PhotoMapper;
import ai.viceversa.demo.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoSaveService {
	private final PhotoFetchService photoFetchService;
	private final PhotoRepository photoRepository;

	public void saveList(int numOfRows, int pageNo) {
		List<PhotoFetchResponseDto> response = photoFetchService.fetchList(numOfRows, pageNo);
		saveAllFromDto(response);
	}

	public void saveDetail(String title, int numOfRows, int pageNo) {
		List<PhotoFetchResponseDto> response = photoFetchService.fetchDetail(title, numOfRows, pageNo);
		saveAllFromDto(response);
	}

	@Transactional
	public void saveAllFromDto(List<PhotoFetchResponseDto> dto) {
		List<PhotoFetchResponseDto> filtered = filterDuplicateById(dto);
		List<Photo> photos = toPhotoList(filtered);
		photoRepository.saveAll(photos);
	}

	private List<Photo> toPhotoList(List<PhotoFetchResponseDto> dto) {
		return dto.stream()
			.map(PhotoMapper::toPhoto)
			.toList();
	}

	private List<PhotoFetchResponseDto> filterDuplicateById(List<PhotoFetchResponseDto> dto) {
		return dto.stream().filter(d -> !photoRepository.existsByContentId(d.galContentId())).toList();
	}
}
