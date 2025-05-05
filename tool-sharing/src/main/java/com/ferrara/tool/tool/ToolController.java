package com.ferrara.tool.tool;

import com.ferrara.tool.utils.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<ToolResponse> findToolById(
        @PathVariable("tool-id") Integer toolId
    ) {
        return ResponseEntity.ok(service.findById(toolId));
    }

    @GetMapping("/name/{tool-name}")
    public ResponseEntity<ToolResponse> findToolByName(
            @PathVariable("tool-name") String toolName
    ) {
        return ResponseEntity.ok(service.findByName(toolName));
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

    @PostMapping("/borrow/{tool-id}")
    public ResponseEntity<Integer> borrowTool (
            @PathVariable("tool-id") Integer toolId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.borrowTool(toolId, connectedUser));
    }

    @PatchMapping("/borrow/return/{tool-id}")
    public ResponseEntity<Integer> returnBorrowedTool(
            @PathVariable("tool-id") Integer toolId,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.returnBorrowedTool(toolId, connectedUser));

    }

    @PatchMapping("/borrow/return/approve/{tool-id}")
    public ResponseEntity<Integer> approveReturnBorrowedTool(
            @PathVariable("tool-id") Integer toolId,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.approveReturnBorrowedTool(toolId, connectedUser));

    }

    @PostMapping(value = "/picture/{tool-id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadToolPicture(
            @PathVariable("tool-id") Integer toolId,
            @Parameter()
            @RequestPart("file")MultipartFile file,
            Authentication connectedUser
    ){
        service.uploadToolPicture(file, connectedUser, toolId);
        return ResponseEntity.accepted().build();
    }


}
