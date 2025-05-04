package com.ferrara.tool.feedback;

import com.ferrara.tool.tool.Tool;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FeedbackMapper {

    public Feedback toFeedback(FeedbackRequest request) {
        Tool tool = Tool.builder()
                .id(request.toolId())
                .archived(false)
                .available(false)
                .build();
        return Feedback.builder()
                .stars(request.stars())
                .comment(request.comment())
                .tool(tool)
                .build();
    }


    public FeedbackResponse toFeedbackResponse(Feedback feedback, Integer currentUserId) {
        return FeedbackResponse.builder()
                .stars(feedback.getStars())
                .comment(feedback.getComment())
                .ownFeedback(Objects.equals(feedback.getCreatedBy(),currentUserId))
                .build();
    }
}
