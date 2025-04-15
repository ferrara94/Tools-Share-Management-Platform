package com.ferrara.tool.tool;

import org.springframework.data.jpa.domain.Specification;
public class ToolSpecification {
    public static Specification<Tool> withOwnerId(Integer ownerId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner").get("id"), ownerId);
    }
}
