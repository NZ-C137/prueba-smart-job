package smart.job.test.infrastructure.in;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smart.job.test.domain.in.UserInputPort;
import smart.job.test.domain.model.UserDTO;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserInputPort userInputPort;

    @PostMapping
    public ResponseEntity<Object> insertUser(@RequestBody @Valid UserDTO user){
        return ResponseEntity.ok(userInputPort.insertUser(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable("userId") String userId){
        return ResponseEntity.ok(null);
    }


}
