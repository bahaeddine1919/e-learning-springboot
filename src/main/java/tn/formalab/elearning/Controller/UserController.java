package tn.formalab.elearning.Controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.formalab.elearning.models.Course;
import tn.formalab.elearning.models.Lesson;
import tn.formalab.elearning.models.User;
import tn.formalab.elearning.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {


    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "register")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        user.password = this.bCryptPasswordEncoder.encode(user.password);
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }


    @GetMapping(path = "oneuser/{id}")
    public ResponseEntity<User> getProductById(@PathVariable Integer id){
        try {
            User user =userRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new User());
        }


    }

    @PatchMapping(path = "update")
    public ResponseEntity<User> updateCourse(@RequestBody User user){

        User Updateuser =this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(Updateuser);

    }



    @PostMapping(path = "login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody User user) {

        HashMap<String, Object> response = new HashMap<>();

        User userFromDB = userRepository.findByEmail(user.email);

        if (userFromDB == null) {
            response.put("message", "user not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {

            Boolean compare = this.bCryptPasswordEncoder.matches(user.password, userFromDB.password);

            if (!compare) {
                response.put("message", "user not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {

                if (!userFromDB.accountState) {
                    response.put("message", "user not allowed !");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                } else {
                    String token = Jwts.builder()
                            .claim("data", userFromDB)
                            .signWith(SignatureAlgorithm.HS256, "SECRET")
                            .compact();

                    response.put("token", token);

                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
            }
        }
    }
    @JsonIgnoreProperties("user")
    @GetMapping(path = "all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> categories = this.userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }


    @GetMapping(path = "tuteurs")
    public ResponseEntity<List<User>> getAllTuteurs() {
        List<User> categories = this.userRepository.findByRole("tuteur");
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }
    @GetMapping(path = "apprenant")
    public ResponseEntity<List<User>> getAllApprenant(){
        List<User> categories = this.userRepository.findByRole("client");
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }


    @GetMapping(path = "one/{id}")
    public ResponseEntity<User> getLessonById(@PathVariable Integer id){
        try {
            User user =userRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new User());
        }


    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Map<String,String>> deleteById(@PathVariable Integer id) {
        this.userRepository.deleteById(id);
        HashMap<String,String> obj =new HashMap<>();
        obj.put("message","users deleted Succesfully");
        return ResponseEntity.status(HttpStatus.OK).body(obj) ;
    }


}
