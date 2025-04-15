package com.ferrara.tool.tool;

import com.ferrara.tool.exception.OperationNotPermittedException;
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

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ToolService {

    private final ToolMapper toolMapper;
    private final ToolRepository repository;
    private final ToolTransactionHistoryRepository transactionHistoryRepository;


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
        Page<Tool> tools = repository.findAllAvailableBooks(pageable, user.getId());
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
                .orElseThrow(() -> new EntityNotFoundException("NO TOOL FOUND WITH ID: "+toolId));

        //only owner of the tool, can update the tool
        User user = (User) connectedUser.getPrincipal();
        if(!Objects.equals(tool.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("YOU CANNOT UPDATE TOOL AVAILABLE STATUS");
        }
        tool.setAvailable(!tool.isAvailable());
        repository.save(tool);
        return toolId;
    }

    public Integer updateArchivedStatus(Integer toolId, Authentication connectedUser) {
        Tool tool = repository.findById(toolId)
                .orElseThrow(() -> new EntityNotFoundException("NO TOOL FOUND WITH ID: "+toolId));

        //only owner of the tool, can update the tool
        User user = (User) connectedUser.getPrincipal();
        if(!Objects.equals(tool.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("YOU CANNOT UPDATE TOOL ARCHIVED STATUS");
        }
        tool.setAvailable(!tool.isArchived());
        repository.save(tool);
        return toolId;
    }
}
