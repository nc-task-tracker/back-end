package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "comment", schema = "new_schema")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String commentText;
    private Date time;

    @ManyToOne(cascade = {
            CascadeType.ALL
    })
    @JoinColumn(name = "profileId", referencedColumnName = "id")
    private Profile profile;

    public Comment(String commentText, Date time, Profile profile) {
        this.commentText = commentText;
        this.time = time;
        this.profile = profile;
    }
}
