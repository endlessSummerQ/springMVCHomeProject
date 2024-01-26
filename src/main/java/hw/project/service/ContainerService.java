package hw.project.service;

import hw.project.dto.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContainerService {

    private final JdbcTemplate jdbcTemplate;

    public ContainerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from users1 order by 1", new BeanPropertyRowMapper<>(User.class));
    }

    public User getUserForLogin(User user) {
        return jdbcTemplate.query("select * from users1 where login=?", new Object[]{user.getLogin()},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    public User getUser(User user) {
        return jdbcTemplate.query("select * from users1 where login=? and password=?", new Object[]{user.getLogin(), user.getPassword()},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    public User getUserForPassword(User user) {
        return jdbcTemplate.query("select * from users1 where password=?", new Object[]{user.getPassword()},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }
    public User getUserForId(int id) {
        return jdbcTemplate.query("select * from users1 where id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    public User getUserForPassword(User user, int id) {
        return jdbcTemplate.query("select * from users1 where id=? and password=?", new Object[]{id, user.getPassword()},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    public void create(User user) {
        jdbcTemplate.update("insert into users1 (login, password) values (?,?)", user.getLogin(),
                user.getPassword());
    }

    public void update(User user) {
        jdbcTemplate.update("update users set password=? where password=?", user.getNewPassword(), user.getPassword());
    }

}
