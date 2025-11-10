package smart.job.test.infrastructure.in;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @GetMapping("/{userEmail}")
    public ResponseEntity<Object> getUser(@PathVariable("userEmail") @NotNull @NotBlank String userEmail){
        return ResponseEntity.ok(userInputPort.getUser(userEmail));
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
        return ResponseEntity.ok(userInputPort.getAllUsers());
    }

}
