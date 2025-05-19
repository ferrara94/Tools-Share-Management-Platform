package com.ferrara.tool.feedback;

import com.ferrara.tool.exception.OperationNotPermittedException;
import com.ferrara.tool.tool.Tool;
import com.ferrara.tool.tool.ToolRepository;
import com.ferrara.tool.user.User;
import com.ferrara.tool.utils.common.PageResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final ToolRepository repository;
    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;

    public Integer save(FeedbackRequest request, Authentication connectedUser) {
        Tool tool = repository.findById(request.toolId())
                .orElseThrow(() -> new EntityNotFoundException("NO TOOL FOUND WITH THE ID: " + request.toolId()));
        if (tool.isArchived() || !tool.isAvailable()) {
            throw new OperationNotPermittedException("YOU CANNOT GIVE FEEDBACK TO A NOT AVAILABLE/ARCHIVED TOOL");
        }
        //User user = (User) connectedUser.getPrincipal();
        if (Objects.equals(tool.getCreatedBy(), connectedUser.getName())) {
            throw new OperationNotPermittedException("YOU CANNOT AND SHOULD NOT GIVE A FEEDBACK TO YOUR OWN TOOL ");
        }
        Feedback feedback = feedbackMapper.toFeedback(request);
        return feedbackRepository.save(feedback).getId();
    }

    public PageResponse<FeedbackResponse> findAllFeedbacksByTool(Integer toolId, int page, int size, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size);
        User user = (User) connectedUser.getPrincipal();
        Page<Feedback> feedbacks = feedbackRepository.findAllByToolId(toolId, pageable);

        List<FeedbackResponse> response = feedbacks.stream()
                .map(f -> feedbackMapper.toFeedbackResponse(f, user.getId()))
                .toList();

        return new PageResponse<>(
                response,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast()
        );
    }
}
