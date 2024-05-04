package com.example.myimdb.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myimdb.dao.KeywordsMapper;
import com.example.myimdb.domain.Keywords;
import com.example.myimdb.service.IKeywordsService;
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
public class KeywordsServiceImpl extends ServiceImpl<KeywordsMapper, Keywords> implements IKeywordsService {

}
