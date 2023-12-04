package ai.viceversa.demo;

import ai.viceversa.demo.api.OpenApiClient;
import reactor.core.publisher.Flux;

public class OpenApiMock {
	public static final OpenApiClient server = new OpenApiClient() {
		@Override
		public Flux<String> getPhotoList(int numOfRows, int pageNo) {
			return Flux.just(
				"""
						{
							"response": {
								"header": {"resultCode":"0000","resultMsg":"OK"},
								"body": {
									"items": {
										"item":[
											{
												"galContentId":"1002144",
												"galContentTypeId":"17",
												"galTitle":"청설모",
												"galWebImageUrl":"http://tong.visitkorea.or.kr/cms2/website/44/1002144.jpg",
												"galCreatedtime":"20100420024817",
												"galModifiedtime":"20150818231341",
												"galPhotographyMonth":"201004",
												"galPhotographyLocation":"서울 경복궁",
												"galPhotographer":"한국관광공사 김지호",
												"galSearchKeyword":"청설모, 동물"
											},
											{
												"galContentId":"1007757",
												"galContentTypeId":"17",
												"galTitle":"이태원 거리",
												"galWebImageUrl":"http://tong.visitkorea.or.kr/cms2/website/44/1007757.jpg",
												"galCreatedtime":"20130420123211",
												"galModifiedtime":"20191014211530",
												"galPhotographyMonth":"201304",
												"galPhotographyLocation":"이태원",
												"galPhotographer":"김호준",
												"galSearchKeyword":"서울, 용산, 이태원"
											}
										]
									},
									"numOfRows":10,
									"pageNo":1,
									"totalCount":5455
								}
							}
						}
					"""
			);
		}

		@Override
		public Flux<String> getPhotoDetail(String title, int numOfRows, int pageNo) {
			if (title.equals("청설모")) {
				return Flux.just(
					"""
							{
								"response": {
									"header": {"resultCode":"0000","resultMsg":"OK"},
									"body": {
										"items": {
											"item":[
												{
													"galContentId":"1002144",
													"galContentTypeId":"17",
													"galTitle":"청설모",
													"galWebImageUrl":"http://tong.visitkorea.or.kr/cms2/website/44/1002144.jpg",
													"galCreatedtime":"20100420024817",
													"galModifiedtime":"20150818231341",
													"galPhotographyMonth":"201004",
													"galPhotographyLocation":"서울 경복궁",
													"galPhotographer":"한국관광공사 김지호",
													"galSearchKeyword":"청설모, 동물"
												},
												{
													"galContentId":"1002145",
													"galContentTypeId":"17",
													"galTitle":"청설모",
													"galWebImageUrl":"http://tong.visitkorea.or.kr/cms2/website/44/1002145.jpg",
													"galCreatedtime":"20100420024817",
													"galModifiedtime":"20150818231341",
													"galPhotographyMonth":"201004",
													"galPhotographyLocation":"수리산",
													"galPhotographer":"직장인동호회 김창윤",
													"galSearchKeyword":"청설모, 동물"
												},
												{
													"galContentId":"1002146",
													"galContentTypeId":"17",
													"galTitle":"청설모",
													"galWebImageUrl":"http://tong.visitkorea.or.kr/cms2/website/44/1002146.jpg",
													"galCreatedtime":"20100420024817",
													"galModifiedtime":"20150818231341",
													"galPhotographyMonth":"201004",
													"galPhotographyLocation":"관악산",
													"galPhotographer":"경기관광공사 김호준",
													"galSearchKeyword":"청설모, 동물"
												}
											]
										},
										"numOfRows":3,
										"pageNo":1,
										"totalCount":10
									}
								}
							}
						"""
				);
			} else if (title.equals("이태원 거리")) {
				return Flux.just(
					"""
							{
								"response": {
									"header": {"resultCode":"0000","resultMsg":"OK"},
									"body": {
										"items": {
											"item":[
												{
													"galContentId":"1007757",
													"galContentTypeId":"17",
													"galTitle":"이태원 거리",
													"galWebImageUrl":"http://tong.visitkorea.or.kr/cms2/website/44/1007757.jpg",
													"galCreatedtime":"20130420123211",
													"galModifiedtime":"20191014211530",
													"galPhotographyMonth":"201304",
													"galPhotographyLocation":"이태원",
													"galPhotographer":"김호준",
													"galSearchKeyword":"서울, 용산, 이태원"
												},
												{
													"galContentId":"1007758",
													"galContentTypeId":"17",
													"galTitle":"이태원 거리",
													"galWebImageUrl":"http://tong.visitkorea.or.kr/cms2/website/44/1007758.jpg",
													"galCreatedtime":"20130420123211",
													"galModifiedtime":"20191014211530",
													"galPhotographyMonth":"201304",
													"galPhotographyLocation":"이태원",
													"galPhotographer":"김호준",
													"galSearchKeyword":"서울, 용산, 이태원"
												},
												{
													"galContentId":"1007759",
													"galContentTypeId":"17",
													"galTitle":"이태원 거리",
													"galWebImageUrl":"http://tong.visitkorea.or.kr/cms2/website/44/1007759.jpg",
													"galCreatedtime":"20130420123211",
													"galModifiedtime":"20191014211530",
													"galPhotographyMonth":"201304",
													"galPhotographyLocation":"이태원",
													"galPhotographer":"김호준",
													"galSearchKeyword":"서울, 용산, 이태원"
												}
											]
										},
										"numOfRows":3,
										"pageNo":1,
										"totalCount":10
									}
								}
							}
						"""
				);
			} else {
				throw new IllegalArgumentException("openApi mock 서버는 청설모, 이태원거리에 대한 상세보기 정보만 제공합니다");
			}
		}
	};
}
