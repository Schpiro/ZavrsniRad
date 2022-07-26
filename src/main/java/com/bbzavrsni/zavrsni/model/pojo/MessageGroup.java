package com.bbzavrsni.zavrsni.model.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class MessageGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "groups")
    private List<User> groupParticipant;
}
