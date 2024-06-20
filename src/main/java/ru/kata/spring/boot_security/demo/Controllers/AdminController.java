package ru.kata.spring.boot_security.demo.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/admin")
public class AdminController {
    private RoleService roleService;
    private UserService userService;

    @GetMapping
    public String adminView(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "admin/admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        Set<Role> allRoles = roleService.getAllRoles();
        model.addAttribute("user", new User());
        model.addAttribute("roles", allRoles);
        return "admin/new";
    }


    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String updateUserView(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
