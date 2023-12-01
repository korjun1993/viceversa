package ai.viceversa.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@IdClass(PhotoSearchKeywordId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotoSearchKeyword {
	@Id
	@ManyToOne
	@JoinColumn(name = "photo_id")
	private Photo photo;

	@Id
	@ManyToOne
	@JoinColumn(name = "search_keyword_id")
	private SearchKeyword searchKeyword;
}
