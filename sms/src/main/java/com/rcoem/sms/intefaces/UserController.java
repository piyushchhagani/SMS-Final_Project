package com.rcoem.sms.intefaces;

import com.rcoem.sms.application.dto.StudentDetails;
import com.rcoem.sms.application.dto.UserDetails;
import com.rcoem.sms.application.services.StudentService;
import com.rcoem.sms.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDetails userDetails) {
        UserDetails insertedStudentDetails=userService.registerUser(userDetails);
        return ResponseEntity.created(URI.create("/users/"+insertedStudentDetails.getId())).build();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/sign-in")
    public ResponseEntity<UserDetails> signInUser( @RequestBody UserDetails userDetails) {
        UserDetails studentDetails= userService.signInUser(userDetails.getEmail(),userDetails.getPassword());
        if(studentDetails==null){
          return  ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(studentDetails);
        }
    }

}
