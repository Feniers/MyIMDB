package com.example.myimdb.controller;

import com.example.myimdb.service.IMoviesMetadataService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Schema(name = "MovieController", description = "电影相关接口")
public class MovieController {
    @Autowired
    private IMoviesMetadataService moviesService;


}
