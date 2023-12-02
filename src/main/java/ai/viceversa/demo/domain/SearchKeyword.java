package ai.viceversa.demo.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
	name = "SEARCH_KEYWORD_SEQ_GENERATOR",
	sequenceName = "SEARCH_KEYWORD_SEQ"
)
public class SearchKeyword {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "searchKeyword")
	private List<PhotoSearchKeyword> searchKeywords = new ArrayList<>();

	public SearchKeyword(String name) {
		this.name = name;
	}
}
