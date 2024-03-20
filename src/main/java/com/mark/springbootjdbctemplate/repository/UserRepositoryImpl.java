package com.mark.springbootjdbctemplate.repository;

import com.mark.springbootjdbctemplate.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO user(fname, lname, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getFname(), user.getLname(), user.getEmail());
    }

    @Override
    public User findById(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user SET fname=?, lname=?, email=? WHERE id=?";
        jdbcTemplate.update(sql, user.getFname(), user.getLname(), user.getEmail(), user.getId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM user WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setFname(rs.getString("fname"));
            user.setLname(rs.getString("lname"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }
}
