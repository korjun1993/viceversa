package ai.viceversa.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ai.viceversa.demo.config.OpenApiFetchProperties;
import ai.viceversa.demo.dto.ItemDto;
import ai.viceversa.demo.dto.PhotoFetchResponseDto;
import ai.viceversa.demo.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoSaveService {
	private final PhotoFetchService photoFetchService;
	private final PhotoRepository photoRepository;
	private final PhotoFactoryService photoFactoryService;

	@Transactional
	public void saveTitleAndDetails(OpenApiFetchProperties openApiFetchProperties) {
		List<PhotoFetchResponseDto> photoDetails = photoFetchService.fetchDetailsJoinWithTitles(
				openApiFetchProperties.titleFetchCount(), 0,
				openApiFetchProperties.detailFetchCount(), 0)
			.block();

		if (photoDetails == null || photoDetails.isEmpty()) {
			log.info("데이터베이스에 저장할 사진 목록이 존재하지 않습니다");
			return;
		}

		photoDetails.forEach(this::savePhotoFromPhotoFetchResponseDto);
	}

	private void savePhotoFromPhotoFetchResponseDto(PhotoFetchResponseDto photoFetchResponseDto) {
		photoFetchResponseDto.items().forEach(this::savePhotoFromItemDto);
	}

	private void savePhotoFromItemDto(ItemDto itemDto) {
		if (!photoRepository.existsById(itemDto.galContentId())) {
			photoRepository.save(photoFactoryService.toPhoto(itemDto));
		}
	}
}
