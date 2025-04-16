package com.ferrara.tool.tool;

import com.ferrara.tool.exception.OperationNotPermittedException;
import com.ferrara.tool.file.FileStorageService;
import com.ferrara.tool.history.ToolTransactionHistory;
import com.ferrara.tool.history.ToolTransactionHistoryRepository;
import com.ferrara.tool.user.User;
import com.ferrara.tool.utils.common.PageResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ToolService {

    private final ToolMapper toolMapper;
    private final ToolRepository repository;
    private final ToolTransactionHistoryRepository transactionHistoryRepository;

    private final FileStorageService fileStorageService;


    public Integer save(ToolRequest request, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Tool tool = toolMapper.toTool(request);
        tool.setOwner(user);
        return repository.save(tool).getId();
    }

    public ToolResponse findById(Integer toolId) {
        return repository.findById(toolId)
                .map(toolMapper::toToolResponse)
                .orElseThrow(() -> new EntityNotFoundException("No tool found with the ID: " + toolId));
    }

    public PageResponse<ToolResponse> findAllTools(int page, int size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Tool> tools = repository.findAllAvailableTools(pageable, user.getId());
        List<ToolResponse> response = tools.stream()
                .map(toolMapper::toToolResponse)
                .toList();

        return new PageResponse<>(
                response,
                tools.getNumber(),
                tools.getSize(),
                tools.getTotalElements(),
                tools.getTotalPages(),
                tools.isFirst(),
                tools.isLast()
        );
    }

    public PageResponse<ToolResponse> findAllToolsByOwner(int page, int size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Tool> tools = repository.findAll(ToolSpecification.withOwnerId(user.getId()), pageable);
        List<ToolResponse> response = tools.stream()
                .map(toolMapper::toToolResponse)
                .toList();

        return new PageResponse<>(
                response,
                tools.getNumber(),
                tools.getSize(),
                tools.getTotalElements(),
                tools.getTotalPages(),
                tools.isFirst(),
                tools.isLast()
        );
    }

    public PageResponse<BorrowedToolResponse> findAllBorrowedTools(int page, int size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        //we have the relationship between Tool and ToolTransactionHistory
        Page<ToolTransactionHistory> borrowedTools = transactionHistoryRepository.findAllBorrowedTools(pageable, user.getId());
        List<BorrowedToolResponse> response = borrowedTools.stream()
                .map(toolMapper::toBorrowedToolResponse)
                .toList();
        return new PageResponse<>(
                response,
                borrowedTools.getNumber(),
                borrowedTools.getSize(),
                borrowedTools.getTotalElements(),
                borrowedTools.getTotalPages(),
                borrowedTools.isFirst(),
                borrowedTools.isLast()
        );
    }


    public Integer updateAvailableStatus(Integer toolId, Authentication connectedUser) {
        Tool tool = repository.findById(toolId)
                .orElseThrow(() -> new EntityNotFoundException("NO TOOL FOUND WITH ID: " + toolId));

        //only owner of the tool, can update the tool
        User user = (User) connectedUser.getPrincipal();
        if (!Objects.equals(tool.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("YOU CANNOT UPDATE TOOL AVAILABLE STATUS");
        }
        tool.setAvailable(!tool.isAvailable());
        repository.save(tool);
        return toolId;
    }

    public Integer updateArchivedStatus(Integer toolId, Authentication connectedUser) {
        Tool tool = repository.findById(toolId)
                .orElseThrow(() -> new EntityNotFoundException("NO TOOL FOUND WITH ID: " + toolId));

        //only owner of the tool, can update the tool
        User user = (User) connectedUser.getPrincipal();
        if (!Objects.equals(tool.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("YOU CANNOT UPDATE TOOL ARCHIVED STATUS");
        }
        tool.setAvailable(!tool.isArchived());
        repository.save(tool);
        return toolId;
    }

    public Integer borrowTool(Integer toolId, Authentication connectedUser) {
        Tool tool = repository.findById(toolId)
                .orElseThrow(() -> new EntityNotFoundException("NO TOOL FOUND WITH THE ID: " + toolId));
        if (tool.isArchived() || !tool.isAvailable()) {
            throw new OperationNotPermittedException("THE REQUESTED TOOL CANNOT BE BORROWED SINCE IT IS NOT AVAILABLE OR IS ARCHIVED");
        }
        User user = (User) connectedUser.getPrincipal();

        //avoid the owner borrowing its tool
        if (Objects.equals(tool.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("YOU CANNOT BORROW YOUR OWN TOOL ");
        }

        final boolean isAlreadyBorrowed = transactionHistoryRepository.isAlreadyBorrowedByUser(toolId, user.getId());
        if (isAlreadyBorrowed) {
            throw new OperationNotPermittedException("THE TOOL YOU REQUESTED IS ALREADY BORROWED");
        }

        ToolTransactionHistory toolTransactionHistory = ToolTransactionHistory.builder()
                .userId(user)
                .toolId(tool)
                .returned(false)
                .returnApproved(false)
                .build();

        return transactionHistoryRepository.save(toolTransactionHistory).getId();
    }

    public Integer returnBorrowedTool(Integer toolId, Authentication connectedUser) {
        Tool tool = repository.findById(toolId)
                .orElseThrow(() -> new EntityNotFoundException("NO TOOL FOUND WITH THE ID: " + toolId));
        if (tool.isArchived() || !tool.isAvailable()) {
            throw new OperationNotPermittedException("THE REQUESTED TOOL CANNOT BE BORROWED SINCE IT IS NOT AVAILABLE OR IS ARCHIVED");
        }
        User user = (User) connectedUser.getPrincipal();
        //avoid the owner returning its tool
        if (Objects.equals(tool.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("YOU CANNOT RETURN YOUR OWN TOOL SINCE YOU DIDN'T BORROW IT ");
        }
        ToolTransactionHistory toolTransactionHistory = transactionHistoryRepository.findByToolIdAndUserId(toolId, user.getId())
                .orElseThrow(() -> new OperationNotPermittedException("YOU DIDN'T BORROW THIS TOOL"));
        toolTransactionHistory.setReturned(true);
        return transactionHistoryRepository.save(toolTransactionHistory).getId();
    }

    public Integer approveReturnBorrowedTool(Integer toolId, Authentication connectedUser) {
        Tool tool = repository.findById(toolId)
                .orElseThrow(() -> new EntityNotFoundException("NO TOOL FOUND WITH THE ID: " + toolId));
        if (tool.isArchived() || !tool.isAvailable()) {
            throw new OperationNotPermittedException("THE REQUESTED TOOL CANNOT BE BORROWED SINCE IT IS NOT AVAILABLE OR IS ARCHIVED");
        }
        User user = (User) connectedUser.getPrincipal();
        //avoid the owner approving the return of its tool
        if (Objects.equals(tool.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("YOU CANNOT APPROVE THE RETURN OF YOUR OWN TOOL SINCE YOU COULDN'T BORROW IT ");
        }

        //CHECK IF THE TOOL RETURNED IS OWNED BY THE USER (SO BY THE OWNER)
        ToolTransactionHistory toolTransactionHistory = transactionHistoryRepository.findByToolIdAndOwnerId(toolId, user.getId())
                .orElseThrow(() -> new OperationNotPermittedException("YOU CANNOT APPROVE THIS TOOL RETURN"));

        toolTransactionHistory.setReturnApproved(true);

        return transactionHistoryRepository.save(toolTransactionHistory).getId();
    }

    public void uploadToolPicture(MultipartFile file, Authentication connectedUser, Integer toolId) {
        Tool tool = repository.findById(toolId)
                .orElseThrow(() -> new EntityNotFoundException("NO TOOL FOUND WITH THE ID: " + toolId));
        User user = (User) connectedUser.getPrincipal();

        var toolPicture = fileStorageService.saveFile(file, user.getId());
        tool.setToolPicture(toolPicture);
        repository.save(tool);
    }
}
