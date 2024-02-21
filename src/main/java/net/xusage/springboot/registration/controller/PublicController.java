package net.xusage.springboot.registration.controller;

import net.xusage.springboot.registration.entity.UserSchema;
import net.xusage.springboot.registration.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.xusage.springboot.registration.validations.ValidateGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/public")
public class PublicController extends BaseController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "logout", required = false) String logout,
                                   @RequestParam(value = "register", required = false) String register,
                                   Model model) {
        if (isAuthenticated()) {
            return "redirect:/user/dashboard";
        }

        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        } else if (logout != null) {
            errorMessage = "You have been successfully logged out !!";
        } else if (register != null) {
            errorMessage = "You registration successful. Login with registered credentials !!";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "login.html";
    }

    @GetMapping("/")
    public String displayHomePage() {
        if (isAuthenticated()) {
            return "redirect:/user/dashboard";
        }
        else {
            return "redirect:/public/login";
        }
    }

    @GetMapping("/register")
    public String displayRegisterPage(Model model) {
        if (isAuthenticated()) {
            return "redirect:/user/dashboard";
        }

        model.addAttribute("user", new UserSchema());
        return "register.html";
    }

    @PostMapping("/createUser")
    public String createUser(@Validated(ValidateGroups.OnCreate.class) @ModelAttribute("user") UserSchema user, Errors errors) {
        if(errors.hasErrors()){
            return "register.html";
        }
        boolean isSaved = userService.create(user);
        if(isSaved){
            return "redirect:/public/login?register=true";
        }else {
            return "register.html";
        }
    }


}
