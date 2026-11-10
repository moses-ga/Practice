package io.kenzo.learning.controllers;

import io.kenzo.learning.exception.ModelNotFoundException;
import io.kenzo.learning.model.User;
import io.kenzo.learning.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

/**
 * Page + form endpoints for the User CRUD. The table itself is loaded by
 * DataTables from {@link io.kenzo.learning.controllers.apis.UserApiController}.
 */
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private static final String INDEX = "redirect:/users/index";

    private final UserService userService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("userObj", new User());
        return "users/index";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute("userObj") User user, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) {
            ra.addFlashAttribute("error", firstError(result));
            return INDEX;
        }
        if (userService.emailExists(user.getEmail(), null)) {
            ra.addFlashAttribute("error", "Email already exists");
            return INDEX;
        }
        if (userService.phoneExists(user.getPhone(), null)) {
            ra.addFlashAttribute("error", "Phone already exists");
            return INDEX;
        }
        userService.store(user);
        ra.addFlashAttribute("success", "User is stored successfully");
        return INDEX;
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("userObj") User user, BindingResult result, RedirectAttributes ra) {
        if (user.getId() == null) {
            ra.addFlashAttribute("error", "User id is missing");
            return INDEX;
        }
        if (result.hasErrors()) {
            ra.addFlashAttribute("error", firstError(result));
            return INDEX;
        }
        if (userService.emailExists(user.getEmail(), user.getId())) {
            ra.addFlashAttribute("error", "Email already exists");
            return INDEX;
        }
        if (userService.phoneExists(user.getPhone(), user.getId())) {
            ra.addFlashAttribute("error", "Phone already exists");
            return INDEX;
        }
        try {
            userService.update(user);
            ra.addFlashAttribute("success", "User is updated successfully");
        } catch (ModelNotFoundException e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return INDEX;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id, RedirectAttributes ra) {
        try {
            userService.delete(id);
            ra.addFlashAttribute("success", "User is deleted successfully");
        } catch (ModelNotFoundException e) {
            ra.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            ra.addFlashAttribute("error", "User can not be deleted");
        }
        return INDEX;
    }

    private static String firstError(BindingResult result) {
        return result.getAllErrors().stream()
                .findFirst()
                .map(ObjectError::getDefaultMessage)
                .orElse("Validation error");
    }
}
