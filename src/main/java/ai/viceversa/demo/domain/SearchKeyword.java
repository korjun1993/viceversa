package ai.viceversa.demo.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString(exclude = "searchKeywords")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchKeyword extends BaseTimeEntity implements Persistable<String> {
	@Id
	private String id;

	@OneToMany(mappedBy = "searchKeyword")
	private List<PhotoSearchKeyword> searchKeywords = new ArrayList<>();

	public SearchKeyword(String id) {
		this.id = id;
	}

	@Override
	public boolean isNew() {
		return getInsertedTime() == null;
	}
}
