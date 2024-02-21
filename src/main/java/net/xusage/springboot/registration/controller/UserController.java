package net.xusage.springboot.registration.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.xusage.springboot.registration.entity.UserPrincipal;
import net.xusage.springboot.registration.entity.UserSchema;
import net.xusage.springboot.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/public/login?logout=true";
    }

    @GetMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("user", userService.findByEmail(authentication.getName()));
        return "dashboard.html";
    }

    @GetMapping("/editInfo")
    public String editInfo(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("user", userService.findByEmail(authentication.getName()));
        return "editInfo.html";
    }

    @GetMapping("/editEmail")
    public String editEmail(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("user", userService.findByEmail(authentication.getName()));
        return "editEmail.html";
    }

    @GetMapping("/editPassword")
    public String editPassword(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("user", new UserSchema());
        return "editPassword.html";
    }

    @PostMapping("/updateInfo")
    public String updateInfo(@Validated(UserSchema.ValidateGroups.OnUpdate.class) @ModelAttribute("user") UserSchema user, Authentication authentication, Errors errors) {
        UserPrincipal authUser = principal();
        if(errors.hasErrors()){
            return "editInfo.html";
        }

        user.setId(authUser.getId());
        userService.update(user);
        return "redirect:/user/dashboard";
    }

    @PostMapping("/updateEmail")
    public String updateEmail(@Validated(UserSchema.ValidateGroups.OnUpdateEmail.class) @ModelAttribute("user") UserSchema user, Authentication authentication, Errors errors) {
        UserPrincipal authUser = principal();
        String userEmail = authUser.getUsername();
        if (!userEmail.equalsIgnoreCase(user.getEmail())) {
            UserSchema u = userService.findByEmail(user.getEmail());
            if (u != null) {
                errors.reject("You changed the email, but the email has been in the system.", "You changed the email, but the email has been in the system.");
            }
        }
        if(errors.hasErrors()){
            return "editEmail.html";
        }

        user.setId(authUser.getId());
        userService.update(user);
        return  "redirect:/user/logout";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(@Validated(UserSchema.ValidateGroups.OnUpdatePassword.class) @ModelAttribute("user") UserSchema user, Authentication authentication, Errors errors) {
        UserPrincipal authUser = principal();
        UserSchema userSchema = userService.findByEmail(authUser.getUsername());
        if (!userService.passwordMatches(user.getPasswordOld(), userSchema.getPassword())) {
            errors.reject("The old password is incorrect.", "The old password is incorrect.");
        }

        if(errors.hasErrors()){
            return "editPassword.html";
        }
        user.setId(authUser.getId());
        userService.update(user);
        return  "redirect:/user/logout";
    }

}
