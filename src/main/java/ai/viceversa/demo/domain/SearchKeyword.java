package ai.viceversa.demo.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class SearchKeyword {
	@Id
	private String id;

	@OneToMany(mappedBy = "searchKeyword")
	private List<PhotoSearchKeyword> searchKeywords = new ArrayList<>();

	public SearchKeyword(String id) {
		this.id = id;
	}
}
