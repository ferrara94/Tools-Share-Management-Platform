package com.ferrara.tool.feedback;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    @Query("""
           SELECT feedback
           FROM Feedback feedback
           WHERE feedback.tool.id = :toolId
           """)
    Page<Feedback> findAllByToolId(@Param("toolId") Integer toolId, Pageable pageable);
}
