package ai.viceversa.demo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo {
	@Id
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "photo_type_id")
	private PhotoType photoType;

	@Default
	@OneToMany(mappedBy = "photo")
	private List<PhotoSearchKeyword> searchKeywords = new ArrayList<>();

	private String title;

	private String imageUrl;

	private LocalDateTime createdTime;

	private LocalDateTime modifiedTime;

	@Embedded
	private YearMonth month;

	private String location;

	private String photographer;

	//== 연관관계 편의 메서드 ==//
	public void setPhotoType(PhotoType photoType) {
		if (this.photoType != null) {
			this.photoType.getPhotos().remove(this);
		}
		photoType.getPhotos().add(this);
		this.photoType = photoType;
	}

	//== 연관관계 편의메서드 ==//
	public void addSearchKeyword(final SearchKeyword searchKeyword) {
		PhotoSearchKeyword photoSearchKeyword = new PhotoSearchKeyword(this, searchKeyword);
		searchKeywords.remove(photoSearchKeyword);
		searchKeyword.getSearchKeywords().remove(photoSearchKeyword);
		searchKeywords.add(photoSearchKeyword);
		searchKeyword.getSearchKeywords().add(photoSearchKeyword);
	}

	public void addSearchKeywords(final List<SearchKeyword> searchKeywords) {
		searchKeywords.stream().distinct().forEach(this::addSearchKeyword);
	}
}
