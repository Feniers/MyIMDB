package com.example.myimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Crew对象")
public class Crew {
    @JsonProperty("credit_id")
    private String creditId;

    private String department;
    private Integer gender;
    private Integer id;
    private String job;
    private String name;

    @JsonProperty("profile_path")
    private String profilePath;
}