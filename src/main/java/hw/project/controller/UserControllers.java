package hw.project.controller;

import hw.project.domainService.UserService;
import hw.project.dto.User;
import hw.project.util.UserValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserControllers {

    private final UserService userService;
    private final UserValidator userValidator;

    public UserControllers(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUser());
        return "user/allUsers";
    }

    @GetMapping("/new")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/new")
    public String register(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user/register";
        }
        userService.create(user);
        return "user/successfullyRegister";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

        if (userService.getUser(user) != null) {
            return "user/loginSuccessfully";
        }
            return "user/login";
    }

    @GetMapping("/change/{id}")
    public String change(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserForId(id));
        return "user/change";
    }

    @PatchMapping("/change")
    public String change(@ModelAttribute("user")@Valid User user,
                         BindingResult bindingResult) {
        if (userService.changePassword(user) != null) {
            userService.update(user);
            return "user/changePasswordSuccessfully";
        }
        return "user/change";
    }
}

