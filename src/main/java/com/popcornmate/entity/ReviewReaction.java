package com.popcornmate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "review_reaction")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class ReviewReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_reaction_code")
    private Integer reviewReactionCode;
    @Enumerated(EnumType.STRING)
    @Column(name = "reaction")
    private ReviewReactionEnum reaction;
    @Column(name = "reacted_time")
    private LocalDateTime reactedTime;

    @JoinColumn(name = "review_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code")
    private User user;

    public ReviewReaction(Review review, User user, LocalDateTime reactedTime, ReviewReactionEnum reaction) {
        this.review = review;
        this.user = user;
        this.reactedTime = reactedTime;
        this.reaction = reaction;
    }
}
