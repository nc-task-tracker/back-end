package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "profile", schema = "new_schema")
@Getter
@Setter
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String firstName;
    private String secondName;
    private String email;
    private Date birthday;

    @OneToOne (cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @JsonManagedReference
    private User user;

    @Fetch(value = FetchMode.SELECT)
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "profile_filter",
            joinColumns = @JoinColumn(name = "profileid"),
            inverseJoinColumns = @JoinColumn(name = "filterid")
    )
    @JsonBackReference
    private Set<Filter> filters = new HashSet<>();

    public Profile(String firstName, String secondName, String email, Date birthday, User user, Set<Filter> filters) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.birthday = birthday;
        this.user = user;
        this.filters = filters;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", user=" + user +
                ", filters=" + filters +
                '}';
    }
}
