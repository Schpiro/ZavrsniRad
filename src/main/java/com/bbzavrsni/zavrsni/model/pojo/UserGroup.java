package com.bbzavrsni.zavrsni.model.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(targetEntity = UserGroup.class)
    @JoinColumn(name = "group_id")
    private MessageGroup messageGroup;
}
