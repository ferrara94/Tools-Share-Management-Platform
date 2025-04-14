package com.ferrara.tool.feedback;

import com.ferrara.tool.tool.Tool;
import com.ferrara.tool.utils.common.BaseEntity;
import jakarta.persistence.*;
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
public class Feedback extends BaseEntity {

    private Integer stars;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "tool_id")
    private Tool tool;

}
