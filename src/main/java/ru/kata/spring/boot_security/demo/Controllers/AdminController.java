package ru.kata.spring.boot_security.demo.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.MyUserService;
import ru.kata.spring.boot_security.demo.services.RoleService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/admin")
public class AdminController {
    private RoleService roleService;
    private MyUserService myUserService;

    @GetMapping
    public String adminView(Model model) {
        List<User> allUsers = myUserService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "admin/admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("rol", "");
        return "admin/new";
    }

    //    ROLE_ADMIN, ROLE_USER,ROLE_ADMIN,ROLE_asd
    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user, @RequestParam("rol") String rol) {
        Set<String> tamp = Arrays.stream(rol.split(","))
                .map(String::trim)
                .filter(role -> role.equals("ROLE_USER") || role.equals("ROLE_ADMIN"))
                .collect(Collectors.toSet());
        if (!tamp.isEmpty()) {
            Set<Role> roleList = tamp.stream()
                    .map(roleName -> roleService.findByName(roleName))
                    .collect(Collectors.toSet());
            user.setRoles(roleList);
        }
        myUserService.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String updateUserView(@PathVariable Long id, Model model) {
        model.addAttribute("user", myUserService.getUserById(id));
        String roles = StringUtils.collectionToDelimitedString(myUserService.getUserById(id).getRoles().stream().map(Role::getName).collect(Collectors.toList()), ", ");
        model.addAttribute("roles", roles);
        System.out.println(myUserService.getUserById(id).getId());
        return "admin/update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("rol") String rol) {
        System.out.println(myUserService.getUserById(1L).getId());
        System.out.println(user.getId());
        Set<String> tamp = Arrays.stream(rol.split(","))
                .map(String::trim)
                .filter(role -> role.equals("ROLE_USER") || role.equals("ROLE_ADMIN"))
                .collect(Collectors.toSet());
        if (!tamp.isEmpty()) {
            Set<Role> roleList = tamp.stream()
                    .map(roleName -> roleService.findByName(roleName))
                    .collect(Collectors.toSet());
            user.setRoles(roleList);
        }
        myUserService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        myUserService.deleteUser(id);
        return "redirect:/admin";
    }
}
