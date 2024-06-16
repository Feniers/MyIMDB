package com.example.myimdb.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myimdb.dao.RatingsMapper;
import com.example.myimdb.dao.RatingsSmallMapper;
import com.example.myimdb.domain.Ratings;
import com.example.myimdb.domain.RatingsSmall;
import com.example.myimdb.service.IRatingsSmallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Adonis
 * @since 2024-05-04
 */
@Service
public class RatingsSmallServiceImpl extends ServiceImpl<RatingsSmallMapper, RatingsSmall> implements IRatingsSmallService {

    @Autowired
    private RatingsMapper ratingsMapper;

    /**
     * 评分
     * @param userId 用户id
     * @param movieId 电影id
     * @param rating 评分
     * @return 是否评分成功
     */
    @Override
    @Transactional
    public boolean rate(Integer userId, Integer movieId, Double rating) {
        QueryWrapper<RatingsSmall> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userId)
                .eq("movieId",movieId);
        RatingsSmall ratingsSmall = this.getOne(queryWrapper);
        if(ratingsSmall==null){
            ratingsSmall = new RatingsSmall();
            ratingsSmall.setUserId(userId);
            ratingsSmall.setMovieId(movieId);
            ratingsSmall.setRating(rating);
            ratingsSmall.setTimestamp((int) (System.currentTimeMillis() / 1000));
            this.baseMapper.insert(ratingsSmall);

            // 同步更新ratings表
            Ratings ratings = new Ratings(ratingsSmall);
            ratingsMapper.insert(ratings);

            return true;
        }else {
            ratingsSmall.setRating(rating);
            this.baseMapper.updateById(ratingsSmall);

            Ratings ratings = new Ratings(ratingsSmall);
            ratingsMapper.updateById(ratings);

            return true;
        }
    }
}
