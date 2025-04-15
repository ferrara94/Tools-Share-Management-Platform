package com.ferrara.tool.tool;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowedToolResponse {

    private Integer id;
    private String name;
    private String description;
    private String condition;
    private String category;
    private boolean returnApproved;
    private boolean returned;

    private double rate; // avarage of the tool

}
