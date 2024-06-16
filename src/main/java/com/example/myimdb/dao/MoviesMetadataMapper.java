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

    @Select("<script>" +
            "SELECT m.*, c.cast, c.crew " +
            "FROM movies_metadata m " +
            "JOIN credits c ON m.id = c.id " +
            "WHERE " +
            "<choose>" +
            "  <when test='columnName == \"title\" or columnName == \"original_title\" or columnName == \"overview\"'>" +
            "    m.${columnName} LIKE CONCAT('%', #{keyword}, '%')" +
            "  </when>" +
            "  <when test='columnName == \"cast\" or columnName == \"crew\"'>" +
            "    c.${columnName} LIKE CONCAT('%', #{keyword}, '%')" +
            "  </when>" +
            "  <otherwise>" +
            "    1=0 <!-- 防止不正确的输入产生错误 -->" +
            "  </otherwise>" +
            "</choose>" +
            "</script>")
    List<Map<String, Object>> searchMoviesWithCredits(@Param("columnName") String columnName, @Param("keyword") String keyword);
//    default List<Map<String, Object>> selectSpecificColumns(@Param("columnName") String columnName, @Param("keyword") String keyword) {
//
//        return selectMaps(queryWrapper);
//    }

}
