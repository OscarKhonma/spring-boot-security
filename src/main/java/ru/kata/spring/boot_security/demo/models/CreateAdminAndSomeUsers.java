package ru.kata.spring.boot_security.demo.models;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CreateAdminAndSomeUsers {
    private UserService userService;
    private RoleService roleService;

    @PostConstruct
    public void createAdminAndSomeUsers() {
        if (roleService.findByName("ROLE_ADMIN") == null && roleService.findByName("ROLE_USER") == null) {
            roleService.save(new Role("ROLE_ADMIN"));
            roleService.save(new Role("ROLE_USER"));
            User admin = new User();
            admin.setUsername("1");
            admin.setPassword("1");
            admin.setRoles(Set.of(roleService.findByName("ROLE_ADMIN")));
            userService.createUser(admin);
            User user = new User();
            user.setUsername("2");
            user.setPassword("2");
            user.setRoles(Set.of(roleService.findByName("ROLE_USER")));
            userService.createUser(user);
        }
    }
}
