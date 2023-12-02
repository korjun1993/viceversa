package ai.viceversa.demo.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
	name = "PHOTO_SEARCH_KEYWORD_SEQ_GENERATOR",
	sequenceName = "PHOTO_SEARCH_KEYWORD_SEQ"
)
public class PhotoSearchKeyword {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "photo_id")
	private Photo photo;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "search_keyword_id")
	private SearchKeyword searchKeyword;

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public void setSearchKeyword(SearchKeyword searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
}
