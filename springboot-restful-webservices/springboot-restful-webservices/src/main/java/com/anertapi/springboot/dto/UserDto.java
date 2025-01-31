package com.anertapi.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Schema(
        description = "UserDto model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    //User first name should not be null or empty
    @Schema(
            description = "User First Name"
    )
    @NotEmpty(message = "User first name should not be null or empty")
    private String firstName;

    //User last name should not be null or empty
    @Schema(
            description = "User Last Name"
    )
    @NotEmpty(message = "User first name should not be null or empty")
    private String lastName;

    //User email should not be null or empty
    //Email address should be valid
    @Schema(
            description = "User Email Address"
    )
    @NotEmpty(message = "User first name should not be null or empty")
    @Email(message = "Email address should be valid")
    private String email;

}
