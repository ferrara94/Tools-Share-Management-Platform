package com.ferrara.tool.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolTransactionHistoryRepository extends JpaRepository<ToolTransactionHistory, Integer> {

    @Query("""
             SELECT history
             FROM ToolTransactionHistory history
             WHERE history.user.id = :userId
            """)
    Page<ToolTransactionHistory> findAllBorrowedTools(Pageable pageable, Integer userId);
}
