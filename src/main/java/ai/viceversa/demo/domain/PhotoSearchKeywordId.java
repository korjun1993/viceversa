package ai.viceversa.demo.domain;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotoSearchKeywordId implements Serializable {
	private String photo;

	private String searchKeyword;
}
