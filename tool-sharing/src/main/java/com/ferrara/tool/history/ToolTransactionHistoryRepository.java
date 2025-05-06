package com.ferrara.tool.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToolTransactionHistoryRepository extends JpaRepository<ToolTransactionHistory, Integer> {

    @Query("""
              SELECT history
              FROM ToolTransactionHistory history
              WHERE history.userId.id = :userId
            """)
    Page<ToolTransactionHistory> findAllBorrowedTools(Pageable pageable, @Param("userId") Integer userId);


    @Query("""
            SELECT (COUNT(*) > 0) AS isBorrowed
            FROM ToolTransactionHistory history
            WHERE history.userId.id = :userId
            AND history.toolId.id = :toolId
            AND history.returnApproved = false
            """)
    boolean isAlreadyBorrowedByUser(@Param("toolId") Integer toolId, @Param("userId") Integer userId);

    @Query("""
            SELECT history
            FROM ToolTransactionHistory history
            WHERE history.userId.id = :userId
            AND history.toolId.id = :toolId
            AND history.returned = false
            AND history.returnApproved = false
            """)
    Optional<ToolTransactionHistory> findByToolIdAndUserId(@Param("toolId") Integer toolId, @Param("userId") Integer userId);

    @Query("""
            SELECT history
            FROM ToolTransactionHistory history
            WHERE history.toolId.owner.id = :userId
            AND history.toolId.id = :toolId
            AND history.returned = true
            AND history.returnApproved = false
            """)
    Optional<ToolTransactionHistory> findByToolIdAndOwnerId(@Param("toolId") Integer toolId, @Param("userId") Integer userId);

    @Query("""
            SELECT history
            FROM ToolTransactionHistory history
            WHERE history.toolId.createdBy = :userId
            """)
    Page<ToolTransactionHistory> findAllReturnedTools(Pageable pageable, @Param("userId") Integer userId);
}
