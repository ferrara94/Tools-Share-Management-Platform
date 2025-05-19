package com.ferrara.tool.tool;

import com.ferrara.tool.feedback.Feedback;
import com.ferrara.tool.history.ToolTransactionHistory;
import com.ferrara.tool.user.User;
import com.ferrara.tool.utils.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter
@Getter
@Entity
public class Tool extends BaseEntity {

    private String name;
    private String description;
    private String condition;
    private String category;
    private String toolPicture; //file path
    private boolean available;
    private boolean archived;

//    @ManyToOne
//    @JoinColumn(name = "owner_id")
//    private User owner; // 1 tool has 1 owner - this field will be mapped by the User class

    @OneToMany(mappedBy = "tool")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "toolId")
    private List<ToolTransactionHistory> transactionHistories;

    @Transient
    public double getRate(){
        if(feedbacks == null || feedbacks.isEmpty()) {
            return 0.0;
        }
        var rate = this.feedbacks.stream()
                .mapToDouble(Feedback::getStars)
                .average()
                .orElse(0.0);

        return Math.round(rate*10.0)/10.0;
    }

}
