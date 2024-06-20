package ru.kata.spring.boot_security.demo.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/")
public class UserController {
    private UserServiceImpl userServiceimpl;
    @GetMapping
    public String redirect() {
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String user(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", (User) authentication.getPrincipal());
        return "user/user";
    }


}
