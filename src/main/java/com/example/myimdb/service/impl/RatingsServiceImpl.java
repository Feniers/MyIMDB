package com.example.myimdb.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myimdb.dao.RatingsMapper;
import com.example.myimdb.domain.Ratings;
import com.example.myimdb.service.IRatingsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Adonis
 * @since 2024-05-04
 */
@Service
public class RatingsServiceImpl extends ServiceImpl<RatingsMapper, Ratings> implements IRatingsService {

}
