package com.example.myimdb.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myimdb.domain.RatingsSmall;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Adonis
 * @since 2024-05-04
 */
public interface IRatingsSmallService extends IService<RatingsSmall> {
    boolean rate(Integer id, Integer movieId, Double rating);
}
