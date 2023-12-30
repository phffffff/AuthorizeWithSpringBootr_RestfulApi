package com.example.authorizerestfulapi.controller;

import com.example.authorizerestfulapi.entity.Users;
import com.example.authorizerestfulapi.service.AbsService;
import com.example.authorizerestfulapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private AbsService<Users> userService;

    @GetMapping(value = {"/", "/list"})
    public ResponseEntity<List<Users>> getUsers(){
        List<Users> users = userService.getAll();
        if(users == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable String id){
        Users u = userService.get(Integer.parseInt(id)).get();
        if(u == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(u,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Users> addUser(@RequestBody Users u){
        if (u==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Users user = userService.add(u);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Users> updateUserById(@PathVariable String id,@RequestBody Users u){

        Users user = userService.update(Integer.valueOf(id), u);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable String id){

        Boolean flag = userService.delete(Integer.valueOf(id));
        if(!flag){
            return new ResponseEntity<>("Xóa thất bại", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Xóa thành công",HttpStatus.OK);
    }
}
