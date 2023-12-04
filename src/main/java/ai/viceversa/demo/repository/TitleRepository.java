package ai.viceversa.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.viceversa.demo.domain.Title;

public interface TitleRepository extends JpaRepository<Title, String> {
}
