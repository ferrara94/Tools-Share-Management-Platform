package com.ferrara.tool.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToolTransactionHistoryRepository extends JpaRepository<ToolTransactionHistory, Integer> {

    @Query("""
             SELECT history
             FROM ToolTransactionHistory history
             WHERE history.user.id = :userId
            """)
    Page<ToolTransactionHistory> findAllBorrowedTools(Pageable pageable, Integer userId);

    @Query("""
           SELECT (COUNT(*) > 0) AS isBorrowed
           FROM ToolTransactionHistory history
           WHERE history.user.id = :userId
           AND history.tool.id = :toolId
           AND history.returnApproved = false
           """)
    boolean isAlreadyBorrowedByUser(Integer toolId, Integer userId);

    @Query("""
           SELECT transaction
           FROM ToolTransactionHistory history
           WHERE history.user.id = :userId
           AND history.tool.id = :toolId
           AND history.returned = false
           AND history.returnApproved = false
           """)
    Optional<ToolTransactionHistory> findByToolIdAndUserId(Integer toolId, Integer userId);

    @Query("""
           SELECT transaction
           FROM ToolTransactionHistory history
           WHERE history.tool.owner.id = :userId
           AND history.tool.id = :toolId
           AND history.returned = true
           AND history.returnApproved = false
           """)
    Optional<ToolTransactionHistory> findByToolIdAndOwnerId(Integer toolId, Integer userId);
}
