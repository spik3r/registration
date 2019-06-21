package com.kaitait.registration.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import lombok.extern.java.Log;

@Entity
@Table(name = "users")
@Log
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
    @CreatedDate
    private Date createdAt = new Date();
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String email;
    @NonNull private String password;

    public User(final User oldUser, final User newUser) {
        this.id = oldUser.id;
        this.createdAt = oldUser.createdAt;
        this.firstName = newUser.firstName;
        this.lastName = newUser.lastName;
        this.email = newUser.email;
        this.password = newUser.password;
    }
}

