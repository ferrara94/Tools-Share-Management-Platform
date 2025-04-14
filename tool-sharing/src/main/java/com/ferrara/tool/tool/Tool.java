package com.ferrara.tool.tool;

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
public class Tool extends BaseEntity {

    private Integer id;
    private String name;
    private String description;
    private String condition;
    private String category;
    private boolean available;
    private boolean archived;

}
