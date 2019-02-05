package com.timur.test.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Length(max = 248, message = "Comment should be less than 248 symbols")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "message_id")
    private Message mess;

    public Comment(){ }

    public Comment(@Length(max = 248, message = "Comment should be less than 248 symbols") String text, Message mess) {
        this.text = text;
        this.mess = mess;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message getMess() {
        return mess;
    }

    public void setMess(Message mess) {
        this.mess = mess;
    }
}
