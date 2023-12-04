package ai.viceversa.demo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.CascadeType;
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
import lombok.ToString;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo extends BaseTimeEntity implements Persistable<Long> {
	@Id
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "photo_type_id")
	private PhotoType photoType;

	@Default
	@OneToMany(mappedBy = "photo", cascade = CascadeType.PERSIST)
	private List<PhotoSearchKeyword> searchKeywords = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "title_id")
	private Title title;

	private String imageUrl;

	private LocalDateTime createdTime;

	private LocalDateTime modifiedTime;

	@Embedded
	private YearMonth yearMonth;

	private String location;

	private String photographer;

	@Override
	public boolean isNew() {
		return getInsertedTime() == null;
	}

	//== 연관관계 편의 메서드 ==//
	public void setPhotoType(PhotoType photoType) {
		if (this.photoType != null) {
			this.photoType.getPhotos().remove(this);
		}
		photoType.getPhotos().add(this);
		this.photoType = photoType;
	}

	public void setTitle(Title title) {
		if (this.title != null) {
			this.title.getPhotos().remove(this);
		}
		title.getPhotos().add(this);
		this.title = title;
	}

	public void addSearchKeyword(final SearchKeyword searchKeyword) {
		PhotoSearchKeyword photoSearchKeyword = new PhotoSearchKeyword();
		photoSearchKeyword.setPhoto(this);
		photoSearchKeyword.setSearchKeyword(searchKeyword);
		searchKeywords.remove(photoSearchKeyword);
		searchKeyword.getSearchKeywords().remove(photoSearchKeyword);
		searchKeywords.add(photoSearchKeyword);
		searchKeyword.getSearchKeywords().add(photoSearchKeyword);
	}

	public void addSearchKeywords(final List<SearchKeyword> searchKeywords) {
		searchKeywords.stream().distinct().forEach(this::addSearchKeyword);
	}
}
