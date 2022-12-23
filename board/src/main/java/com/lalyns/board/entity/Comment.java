package com.lalyns.board.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name="post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public Comment( Post post, User user, String contents) {
        this.contents = contents;
        this.post = post;
        this.user = user;
    }

    public void updateComment(String contents) {
        this.contents = contents;
    }

    public boolean isMatchedUserId(Long userId) {
        return this.user.getId().equals(userId);
    }

}
