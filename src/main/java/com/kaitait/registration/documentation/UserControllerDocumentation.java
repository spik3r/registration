package com.kaitait.registration.documentation;
import com.kaitait.registration.domain.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserControllerDocumentation {

    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Registered"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation("Register a new User")
    @PostMapping("/users/register")
    ResponseEntity<User> register(@RequestParam final String firstName, @RequestParam final String lastName, @RequestParam final String email, @RequestParam final String password);

    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Updated"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation("Update user by id")
    @PostMapping("/users/update")
    ResponseEntity<String> updateById(@RequestParam String id, @RequestParam final String firstName, @RequestParam final String lastName, @RequestParam final String email, @RequestParam final String password);

    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation("Get all Users")
    @GetMapping("/users")
    ResponseEntity<List<User>> getAll();

    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Ok", response = User.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation("Get users by email")
    @GetMapping("/users/email/{email}")
    ResponseEntity<List<User>> getByEmail(@PathVariable final String email);

    @ApiResponses(value= {
            @ApiResponse(code = 204, message = "Deleted"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation("Delete users by email")
    @DeleteMapping("/users/email/{email}")
    ResponseEntity<String> deleteByEmail(@PathVariable final String email);

    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Ok", response = User.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation("Get user by id")
    @GetMapping("/users/{id}")
    ResponseEntity<List<User>> getById(@PathVariable final String id);

    @ApiResponses(value= {
            @ApiResponse(code = 204, message = "Deleted"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation("Delete user by id")
    @DeleteMapping("/users/{id}")
    ResponseEntity<String> deleteById(@PathVariable final String id);
}