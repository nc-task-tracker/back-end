package com.example.demo.dto;

import com.example.demo.model.Dashboard;
import com.example.demo.model.Filter;
import com.example.demo.model.Project;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    private String id;
    @NotNull(message = "fullName field must be filled")
    @Size(min = 3)
    private String fullName;
    @NotBlank()
    @Email(message = "enter a valid email")
    private String email;
    @NotNull(message = "")
    @Size(min = 3, max = 30, message = "Login skype size should be between 3 & 30")
    private String skype;
    @NotBlank()
    @Size( max = 15, message = "telephone number must be less than 15 characters")
    private String telephone;

    private String additional;
    @NotNull(message = "enter a valid birthday")
    private Date birthday;

    private String description;

//    @Null
//    private Set<Dashboard> dashboards = new HashSet<>();
//    @Null
//    private Set<Filter> filters = new HashSet<>();
//    @Null
//    private Set<Project> projects = new HashSet<>();
}
