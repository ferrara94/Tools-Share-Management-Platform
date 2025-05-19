package com.ferrara.tool.tool;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Integer>, JpaSpecificationExecutor<Tool> {

    @Query("""
            SELECT tool
            FROM Tool tool
            WHERE tool.archived = false
            AND tool.available = true
            AND tool.createdBy != :userId
          """)
    Page<Tool> findAllAvailableTools(Pageable pageable, String userId);

    Optional<Tool> findByName(String name);
}
