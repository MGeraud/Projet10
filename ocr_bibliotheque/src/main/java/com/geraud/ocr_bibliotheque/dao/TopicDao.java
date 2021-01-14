package com.geraud.ocr_bibliotheque.dao;

import com.geraud.ocr_bibliotheque.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicDao extends JpaRepository<Topic, Long> {

    List<Topic> findTopicByKeywordContains(String keyword);
}
