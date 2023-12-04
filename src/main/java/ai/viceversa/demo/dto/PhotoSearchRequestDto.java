package ai.viceversa.demo.dto;

import lombok.Builder;

@Builder
public record PhotoSearchRequestDto(String photoTitle, String photographer, String keyword) {
}
