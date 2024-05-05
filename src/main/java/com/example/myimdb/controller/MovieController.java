package com.example.myimdb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myimdb.authorization.annotation.Admin;
import com.example.myimdb.authorization.annotation.LoginRequire;
import com.example.myimdb.domain.ResultStatus;
import com.example.myimdb.domain.MoviesMetadata;
import com.example.myimdb.domain.Result;
import com.example.myimdb.service.IMoviesMetadataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Movie", description = "电影相关接口")
public class MovieController {
    @Autowired
    private IMoviesMetadataService moviesService;

    @Operation(summary = "获取电影列表", description = "获取电影列表接口")
    @Parameters({
            @Parameter(name = "pageNum", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页数量", required = true)
    })
    @GetMapping("/movies/{pageNum}/{pageSize}")
    public ResponseEntity<Result> getMovies(@PathVariable Integer pageNum,
                                            @PathVariable Integer pageSize,
                                            @RequestParam(required = false) boolean isHot ) {
        IPage<MoviesMetadata> page=new Page<>(pageNum,pageSize);
        if(isHot) {
            QueryWrapper<MoviesMetadata> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByDesc("popularity");
            return ResponseEntity.ok(Result.ok(moviesService.getBaseMapper().selectPage(page,queryWrapper)));
        }else
            return ResponseEntity.ok(Result.ok(moviesService.getBaseMapper().selectPage(page,null)));
    }


    @Operation(summary = "获取电影详情", description = "获取电影详情接口")
    @Parameters({
            @Parameter(name = "id", description = "电影id", required = true)
    })
    @GetMapping("/movie/{id}")
    public ResponseEntity<Result> getMovie(@PathVariable @NotNull Integer id) {
        MoviesMetadata movie = moviesService.getBaseMapper().selectById(id);
        if (movie == null) {
            return ResponseEntity.ok(Result.error(ResultStatus.RESOURCE_NOT_FOUND));
        }else {
            return ResponseEntity.ok(Result.ok(movie));
        }
    }

    @Operation(summary = "添加电影", description = "添加电影接口")
    @Parameters({
            @Parameter(name = "movie", description = "电影信息", required = true)
    })
    @Admin
    @PostMapping("/movie")
    public ResponseEntity<Result> addMovie(@RequestBody MoviesMetadata movie) {
        if (moviesService.save(movie)) {
            return ResponseEntity.ok(Result.ok());
        } else {
            return ResponseEntity.ok(Result.error(ResultStatus.ERROR));
        }
    }

}
