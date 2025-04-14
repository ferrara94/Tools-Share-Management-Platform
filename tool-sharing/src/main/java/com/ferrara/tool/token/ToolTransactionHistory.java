package com.ferrara.tool.token;

import com.ferrara.tool.tool.Tool;
import com.ferrara.tool.user.User;
import com.ferrara.tool.utils.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    /*
        User can borrow many tools;
        1 tool can be borrowed by many Users
        MANY-TO-MANY RELATIONSHIP
    */

    @ManyToOne
    @JoinColumn(name = "user_id" )
    private User userId;

    @ManyToOne
    @JoinColumn(name = "tool_id" )
    private Tool toolId;

    private boolean returned;
    private boolean returnApproved;

}
