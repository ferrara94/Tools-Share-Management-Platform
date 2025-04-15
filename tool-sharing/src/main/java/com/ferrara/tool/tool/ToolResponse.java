package com.ferrara.tool.tool;

import com.ferrara.tool.user.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolResponse {

    private Integer id;
    private String name;
    private String description;
    private String condition;
    private String category;
    private boolean available;
    private boolean archived;

    private String owner;

    private byte[] cover; // picture of the tool
    private double rate; // avarage of the tool

}
