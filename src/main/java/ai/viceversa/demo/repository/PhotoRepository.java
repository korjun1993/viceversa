package ai.viceversa.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.viceversa.demo.domain.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
	boolean existsByContentId(Long contentId);
}
