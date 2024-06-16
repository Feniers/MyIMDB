package com.example.myimdb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myimdb.domain.Genres;
import com.example.myimdb.domain.Result;
import com.example.myimdb.service.GenresService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Genres", description = "类型相关接口")
@RequestMapping("/genres")
public class GenresController {
    @Autowired
    private GenresService genresService;

    @Operation(summary = "获取类型列表", description = "获取类型列表接口")
    @GetMapping()
    public ResponseEntity<Result> getGenres() {
        return ResponseEntity.ok(Result.ok(genresService.list()));
    }

    @Operation(summary = "添加类型", description = "添加类型接口")
    @Parameter(name = "genres", description = "类型", required = true)
    @PostMapping()
    public ResponseEntity<Result> addGenres(@RequestBody Genres genres) {
        if (genresService.getOne(new QueryWrapper<Genres>().eq("name", genres.getName())) == null) {
            genresService.save(genres);
            return ResponseEntity.ok(Result.ok());
        } else {
            return ResponseEntity.ok(Result.error(501,"类型已存在"));
        }
    }
}
