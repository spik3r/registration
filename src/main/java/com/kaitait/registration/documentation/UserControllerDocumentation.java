package com.kaitait.registration.documentation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletResponse;

public interface UserControllerDocumentation {

    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    ResponseEntity<String> create(final String firstName, final String lastName, final String email, final String password);
}