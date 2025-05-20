package com.popcornmate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reported_review")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class ReportedReview {
    @Id
    @Column(name = "reported_review_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportedReviewCode;
    @Column(name = "reason")
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_code")
    private Review review;
    @OneToOne
    @JoinColumn(name = "user_code")
    private User user;
}
