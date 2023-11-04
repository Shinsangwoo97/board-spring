package com.board.springboot.user.repository;


import com.board.springboot.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

//    List<User> findAllById(Team team);

    User findUserById(Long id);

//    List<User> findAllById(Meeting meeting);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByUsername(String username);

//    Optional<User> findByKakaoId(Long kakaoId);

    User findUserByNickname(String nickname);

    User findUserByUsername(String username);

}
