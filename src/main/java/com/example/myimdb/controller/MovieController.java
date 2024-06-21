package com.example.myimdb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myimdb.authorization.annotation.Admin;
import com.example.myimdb.authorization.annotation.CurrentUser;
import com.example.myimdb.authorization.annotation.LoginRequire;
import com.example.myimdb.domain.ResultStatus;
import com.example.myimdb.domain.MoviesMetadata;
import com.example.myimdb.domain.Result;
import com.example.myimdb.domain.User;
import com.example.myimdb.service.GenresService;
import com.example.myimdb.service.IMoviesMetadataService;
import com.example.myimdb.utils.RecommendationClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Tag(name = "Movie", description = "电影相关接口")
public class MovieController {
    @Autowired
    private IMoviesMetadataService moviesService;
    @Autowired
    private RecommendationClient recommendationClient;

    @Operation(summary = "获取电影列表", description = "获取电影列表接口")
    @Parameters({
            @Parameter(name = "pageNum", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页数量", required = true)
    })
    @GetMapping("/movies/{pageNum}/{pageSize}")
    public ResponseEntity<Result> getMovies(@PathVariable Integer pageNum,
                                            @PathVariable Integer pageSize,
                                            @RequestParam(required = false) boolean isHot
                                            ) {
        IPage<MoviesMetadata> page=new Page<>(pageNum,pageSize);
        if(isHot) {
            QueryWrapper<MoviesMetadata> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByDesc("popularity");
            return ResponseEntity.ok(Result.ok(moviesService.getBaseMapper().selectPage(page,queryWrapper)));
        }else
            return ResponseEntity.ok(Result.ok(moviesService.getBaseMapper().selectPage(page,null)));
    }


    @Operation(summary = "获取推荐电影", description = "获取推荐电影接口")
    @Parameters({
            @Parameter(name = "userId", description = "用户id", required = true)
    })
    @GetMapping("/{userId}/moves")
    public ResponseEntity<Result> getRecommend(@PathVariable Integer userId) {
            List<Integer> recommendations = recommendationClient.getRecommendations(userId);
            log.info("recommendations:{}",recommendations);

            List<MoviesMetadata> movies = moviesService.getBaseMapper().selectBatchIds(recommendations);

            return ResponseEntity.ok(Result.ok(movies));
    }

    @Operation(summary = "搜索电影", description = "获取电影详情接口")
    @Parameters({
            @Parameter(name = "searchInfo", description = "搜索的信息", required = true),
            @Parameter(name = "searchType", description = "搜索的类型", required = true)
    })
    @GetMapping("/movies/search")
    public ResponseEntity<Result> getMovieInfo(@RequestParam @NotNull String searchInfo,
                                               @RequestParam @NotNull String searchType) {
        List<Map<String, Object>> movie = moviesService.findMoviesByColumnCode(searchType,searchInfo);
//        MoviesMetadata movie = moviesService.getBaseMapper().selectById(id);
        if (movie == null) {
            return ResponseEntity.ok(Result.error(ResultStatus.RESOURCE_NOT_FOUND));
        }else {
            return ResponseEntity.ok(Result.ok(movie));
        }
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
            return ResponseEntity.ok(Result.error(501, "添加失败"));
        }
    }

    @Operation(summary = "删除电影", description = "删除电影接口")
    @Parameters({
            @Parameter(name = "id", description = "电影id", required = true)
    })
    @Admin
    @DeleteMapping("/movie/{id}")
    public ResponseEntity<Result> deleteMovie(@PathVariable @NotNull Integer id) {
        if (moviesService.getBaseMapper().deleteById(id) > 0) {
            return ResponseEntity.ok(Result.ok());
        } else {
            return ResponseEntity.ok(Result.error(501, "删除失败"));
        }
    }

    @Operation(summary = "更新电影", description = "更新电影接口")
    @Parameters({
            @Parameter(name = "movie", description = "电影信息", required = true)
    })
    @Admin
    @PutMapping("/movie")
    public ResponseEntity<Result> updateMovie(@RequestBody MoviesMetadata movie) {
        if (moviesService.getBaseMapper().updateById(movie) > 0) {
            return ResponseEntity.ok(Result.ok());
        } else {
            return ResponseEntity.ok(Result.error(501, "更新失败"));
        }
    }

//    @GetMapping("/movies")

    @GetMapping("/testGrpc")
    public ResponseEntity<Result> testGrpc(int userId) {
        List<Integer> recommendations = recommendationClient.getRecommendations(userId);
        return ResponseEntity.ok(Result.ok(recommendations));
    }

}
