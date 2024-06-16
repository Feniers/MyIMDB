package com.example.myimdb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myimdb.domain.MoviesMetadata;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Adonis
 * @since 2024-05-04
 */
public interface IMoviesMetadataService extends IService<MoviesMetadata> {

    String getMovies();

    void getMoviesPage(IPage<MoviesMetadata> page);

    private String mapColumnCodeToName(int columnCode) {
        return null;
    }

    List<Map<String, Object>> findMoviesByColumnCode(String columnCode, String keyword);

}
