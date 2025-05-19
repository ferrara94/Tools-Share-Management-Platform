package com.ferrara.tool.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ferrara.tool.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@EntityListeners(AuditingEntityListener.class) //remember to have @EnableJpaAuditing in the main class
public class Role {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;

    //many-to-many - mappedBy should be the role attribute in User
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore //avoid the infinite loop
    private List<User> users;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

}
