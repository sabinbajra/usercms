package Tetrad.CMS.project.controller;

import Tetrad.CMS.project.exception.UserNotFoundException;
import Tetrad.CMS.project.model.User;
import Tetrad.CMS.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    private UserRepository userRepository;

    public UserController(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    User getUser(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));

    }

    @DeleteMapping("/users/{id}")
    String deleteUser(@PathVariable Long id)
    {
        if(!userRepository.existsById(id))
        {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User has been deleted successfully";
    }


}
