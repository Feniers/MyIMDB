package com.example.myimdb.domain;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Cast对象")
public class Cast {
    @JsonProperty("cast_id")
    private Integer castId;

    private String character;

    @JsonProperty("credit_id")
    private String creditId;

    private Integer gender;
    private Integer id;
    private String name;
    private Integer order;

    @JsonProperty("profile_path")
    private String profilePath;
}
