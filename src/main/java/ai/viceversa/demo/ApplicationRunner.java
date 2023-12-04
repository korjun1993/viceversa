package ai.viceversa.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ai.viceversa.demo.config.OpenApiFetchProperties;
import ai.viceversa.demo.repository.PhotoRepository;
import ai.viceversa.demo.service.PhotoSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {
	private final PhotoSaveService photoSaveService;
	private final OpenApiFetchProperties openApiFetchProperties;
	private final PhotoRepository photoRepository;

	@Override
	public void run(String... args) {
		log.info("[시작] 공공데이터 포탈로부터 사진 제목 정보를 수신하여 데이터베이스에 저장합니다");
		log.info("[알림] 공공데이터 포탈 서버의 상태에 따라 시간에 시간이 소요될 수 있습니다");
		log.info("[알림] 현재 저장된 데이터 갯수 = " + photoRepository.findAll().size());
		if (openApiFetchProperties.disable()) {
			log.info("[알림] 설정으로 인해 종료됩니다. 데이터 패칭을 활성화하려면 application.yml 설정을 확인해주세요");
			return;
		}

		photoSaveService.saveTitleAndDetails(openApiFetchProperties);
	}
}
