package com.ferrara.tool.feedback;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackResponse {
    private Integer starts;
    private String comment;
    private boolean ownFeedback;
}
