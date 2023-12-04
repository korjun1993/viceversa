package ai.viceversa.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.viceversa.demo.domain.PhotoType;

public interface PhotoTypeRepository extends JpaRepository<PhotoType, Long> {
}
