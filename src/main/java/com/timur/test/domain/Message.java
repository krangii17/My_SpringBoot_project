package com.timur.test.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(max = 248, message = "Message should be less than 248 symbols")
    private String text;

    @Length(max = 40, message = "Tag should be less than 40 symbols")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mess", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    public Message() {
    }

    public Message(@Length(max = 248, message = "Message should be less than 248 symbols") String text, @Length(max = 40, message = "Tag should be less than 40 symbols") String tag) {
        this.text = text;
        this.tag = tag;
    }

    public Message(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
