package com.example.myimdb.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myimdb.domain.Ratings;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Adonis
 * @since 2024-05-04
 */

// 加@Mapper注解，将RatingsMapper接口交给Spring容器管理，非绑定类可以才使用@Autowired注解注入
@Mapper
public interface RatingsMapper extends BaseMapper<Ratings> {

}
