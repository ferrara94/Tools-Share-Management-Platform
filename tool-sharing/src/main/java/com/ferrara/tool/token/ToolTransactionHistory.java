package com.ferrara.tool.token;

import com.ferrara.tool.utils.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter
@Getter
@Entity
public class ToolTransactionHistory extends BaseEntity {

    // user relationship
    // tool relationship

    private boolean returned;
    private boolean returnApproved;

}
