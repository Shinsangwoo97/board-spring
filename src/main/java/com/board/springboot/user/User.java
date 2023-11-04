package com.board.springboot.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column
    private String nickname;

    @JsonIgnore
    @Column
    private String password;

    @Column
    private boolean isKakao;

    @Column
    private String userImgUrl;

    @Column
    private int count;
}
