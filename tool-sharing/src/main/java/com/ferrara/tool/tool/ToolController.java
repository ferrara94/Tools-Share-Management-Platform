package com.ferrara.tool.tool;

import com.ferrara.tool.utils.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tools")
@RequiredArgsConstructor
@Tag(name = "Tool")
public class ToolController {

    private final ToolService service;

    /*
        The tool has an owner which is the User.
        To fetch the User we don't need to pass the user ID as parameter
        since the user who creates the tool should be the connected user itself
    */
    @PostMapping
    public ResponseEntity<Integer> saveTool(
            @Valid @RequestBody ToolRequest request,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.save(request, connectedUser));
    }

    @GetMapping("{tool-id}")
    public ResponseEntity<ToolResponse> findTooById(
        @PathVariable("tool-id") Integer toolId
    ) {
        return ResponseEntity.ok(service.findById(toolId));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ToolResponse>> findAllTools(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "5", required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllTools(page, size, connectedUser));
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<ToolResponse>> findAllToolsByOwner(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "5", required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllToolsByOwner(page, size, connectedUser));
    }

    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedToolResponse>> findAllBorrowedTools(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "5", required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllBorrowedTools(page, size, connectedUser));
    }

    //TODO IMPLEMENT GET RETURNED ALL TOOLS

    @PatchMapping("/available/{tool-id}") //update the available status
    public ResponseEntity<Integer> updateAvailableStatus(
        @PathVariable("tool-id") Integer toolId,
        Authentication connectedUser
    ){
        return ResponseEntity.ok(service.updateAvailableStatus(toolId, connectedUser));
    }

    @PatchMapping("/archived/{tool-id}") //update the available status
    public ResponseEntity<Integer> updateArchivedStatus(
            @PathVariable("tool-id") Integer toolId,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.updateArchivedStatus(toolId, connectedUser));
    }


}
