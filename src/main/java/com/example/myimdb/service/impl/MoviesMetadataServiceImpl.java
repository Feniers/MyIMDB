package com.example.myimdb.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myimdb.dao.MoviesMetadataMapper;
import com.example.myimdb.domain.MoviesMetadata;
import com.example.myimdb.service.IMoviesMetadataService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Adonis
 * @since 2024-05-04
 */
@Service
public class MoviesMetadataServiceImpl extends ServiceImpl<MoviesMetadataMapper, MoviesMetadata> implements IMoviesMetadataService {

    @Override
    public String getMovies() {
        return null;
    }

    @Override
    public void getMoviesPage(IPage<MoviesMetadata> page) {
        this.baseMapper.selectPage(page, null);
    }

}
