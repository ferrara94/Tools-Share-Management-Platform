package com.ferrara.tool.tool;

import jakarta.validation.constraints.NotBlank;

/*
    This record has the role to update or
    create the Tool
*/
public record ToolRequest(
        Integer id,
        @NotBlank(message = "100") //100 means name is mandatory
        String name,
        @NotBlank(message = "101") //100 means name is mandatory
        String description,
        @NotBlank(message = "102") //100 means name is mandatory
        String condition,
        String category,
        boolean available
        //boolean archived
) {
}
