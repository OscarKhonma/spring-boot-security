package ru.kata.spring.boot_security.demo.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.services.MyUserService;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/user")
public class UserController {
    private MyUserService myUserService;

    @GetMapping("/{id}")
    public String user(@PathVariable Long id, Model model) {
        model.addAttribute("user", myUserService.getUserById(id));
        return "user/user";
    }


}
