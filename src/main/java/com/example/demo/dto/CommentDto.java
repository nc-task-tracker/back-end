package com.example.demo.dto;

import com.example.demo.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private String id;

    @NotNull
    private String commentText;

    @DateTimeFormat
    private Date time;

    private ProfileDto profile;

    private String issueId;
}
