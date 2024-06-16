package ru.kata.spring.boot_security.demo.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.MyUserService;
import ru.kata.spring.boot_security.demo.services.RoleService;

import java.util.Set;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ForEveryoneController {
    private RoleService roleService;
    private MyUserService myUserService;

    @GetMapping
    public String getRegister(Model model) {
        model.addAttribute("user", new User());
        if (roleService.findByName("ROLE_ADMIN") == null && roleService.findByName("ROLE_USER") == null) {
            roleService.save(new Role("ROLE_ADMIN"));
            roleService.save(new Role("ROLE_USER"));
            User user = new User();
            user.setUsername("1");
            user.setPassword("1");
            user.setRoles(Set.of(roleService.findByName("ROLE_ADMIN")));
            myUserService.createUser(user);
        }
        return "regist/register";
    }

    @PostMapping
    public String postRegister(@ModelAttribute("user") User user) {
        user.setRoles(Set.of(roleService.findByName("ROLE_USER")));
        myUserService.createUser(user);
        return "redirect:/";
    }
}
