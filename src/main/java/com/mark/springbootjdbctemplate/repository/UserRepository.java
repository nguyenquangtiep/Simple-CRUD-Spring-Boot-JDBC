package com.mark.springbootjdbctemplate.repository;

import com.mark.springbootjdbctemplate.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    List<User> findAll();
    void save(User user);
    User findById(Long id);
    void update(User user);
    void deleteById(Long id);

}
