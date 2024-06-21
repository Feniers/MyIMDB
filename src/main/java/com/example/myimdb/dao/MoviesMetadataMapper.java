package com.example.myimdb.dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myimdb.domain.MoviesMetadata;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Adonis
 * @since 2024-05-04
 */
public interface MoviesMetadataMapper extends BaseMapper<MoviesMetadata> {

    @Select("SELECT * FROM movies_metadata " +
            "WHERE genres LIKE CONCAT('%', #{columnName}, '%') AND title LIKE CONCAT('%', #{keyword}, '%')")
    List<Map<String, Object>> searchMoviesWithCredits(@Param("columnName") String columnName, @Param("keyword") String keyword);
//    default List<Map<String, Object>> selectSpecificColumns(@Param("columnName") String columnName, @Param("keyword") String keyword) {
//
//        return selectMaps(queryWrapper);
//    }

}
