package io.github.likewhat.springbase.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.likewhat.springbase.model.AccountType;
import io.github.likewhat.springbase.model.Constants;
import io.github.likewhat.springbase.model.User;
import io.github.likewhat.springbase.security.SecurityUtils;
import io.github.likewhat.springbase.service.UserService;


@RequestMapping("/api/users")
@RestController
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/populate")
    public ResponseEntity<Map<String, Object>> test() {
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setEmail("test" + i + "@spring.org");
            user.setPassword(SecurityUtils.encodePassword("admin"));
            user.setConfirmed(false);
            user.setAccountType(AccountType.Administrator.name());
            user.setFirstName("First" + i);
            user.setLastName("Last" + i);
            user.setConfirmed(true);
            userService.create(user, Constants.ANONYMOUS_USER);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("count", 20);
        map.put("success", true);
        return ResponseEntity.ok(map);
    }


    @GetMapping("")
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }
}
