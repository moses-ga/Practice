package io.kenzo.learning.controllers.apis;

import io.kenzo.learning.model.User;
import io.kenzo.learning.repositories.UserRepository;
import io.kenzo.learning.services.UserService;
import io.kenzo.learning.utils.DataTablesUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserRepository userRepository;
    private final UserService userService;

    /** Server-side DataTables source for the users page. */
    @GetMapping("/list")
    public DataTablesOutput<User> list(DataTablesInput input) {
        return DataTablesUtils.findAll(userRepository, input);
    }

    @GetMapping("/{id}")
    public User show(@PathVariable("id") UUID id) {
        return userService.findById(id);
    }
}
