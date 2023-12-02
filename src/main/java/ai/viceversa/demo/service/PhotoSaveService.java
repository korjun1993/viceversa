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

	@Transactional
	public void saveList(int numOfRows, int pageNo) {
		List<PhotoFetchResponseDto> response;
		response = photoFetchService.fetchList(numOfRows, pageNo);
		List<PhotoFetchResponseDto> filtered = filterDuplicateById(response);
		List<Photo> photos = toPhotoList(filtered);
		photoRepository.saveAll(photos);
	}

	private List<Photo> toPhotoList(List<PhotoFetchResponseDto> dto) {
		return dto.stream()
			.map(PhotoMapper::toPhoto)
			.toList();
	}

	private List<PhotoFetchResponseDto> filterDuplicateById(List<PhotoFetchResponseDto> dto) {
		return dto.stream().filter(d -> !photoRepository.existsById(d.galContentId())).toList();
	}
}
