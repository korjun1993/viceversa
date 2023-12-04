package ai.viceversa.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.viceversa.demo.domain.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
	List<Photo> findByTitle_Id(String titleId);
}
