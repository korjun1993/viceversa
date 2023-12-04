package ai.viceversa.demo.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.CascadeType;
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
@ToString(exclude = "photos")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotoType extends BaseTimeEntity implements Persistable<Long> {
	@Id
	private Long id;

	@OneToMany(mappedBy = "photoType", cascade = CascadeType.PERSIST)
	private List<Photo> photos = new ArrayList<>();

	public PhotoType(Long id) {
		this.id = id;
	}

	@Override
	public boolean isNew() {
		return getInsertedTime() == null;
	}
}
