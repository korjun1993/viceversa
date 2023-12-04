package ai.viceversa.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.viceversa.demo.domain.SearchKeyword;

public interface SearchKeywordRepository extends JpaRepository<SearchKeyword, String> {
}
