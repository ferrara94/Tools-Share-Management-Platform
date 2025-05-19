package com.ferrara.tool.tool;

import com.ferrara.tool.file.FileUtils;
import com.ferrara.tool.history.ToolTransactionHistory;
import org.springframework.stereotype.Service;

@Service
public class ToolMapper {
    public Tool toTool(ToolRequest request) {
        return Tool.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .category(request.category())
                .condition(request.condition())
                .archived(false)
                .available(request.available())
                .build();
    }

    public ToolResponse toToolResponse(Tool tool) {
        return ToolResponse.builder()
                .id(tool.getId())
                .name(tool.getName())
                .description(tool.getDescription())
                .condition(tool.getCondition())
                .category(tool.getCategory())
                .available(tool.isAvailable())
                .archived(tool.isArchived())
                .rate(tool.getRate())
                //.owner(tool.getOwner().fullName())
                .picture(FileUtils.readFileFromLocation(tool.getToolPicture()))
                .build();
    }

    public BorrowedToolResponse toBorrowedToolResponse(ToolTransactionHistory history) {
        return BorrowedToolResponse.builder()
                .id(history.getId())
                .name(history.getToolId().getName())
                .description(history.getToolId().getDescription())
                .condition(history.getToolId().getCondition())
                .category(history.getToolId().getCategory())
                .rate(history.getToolId().getRate())
                .returned(history.isReturned())
                .returnApproved(history.isReturnApproved())
                .build();
    }
}
