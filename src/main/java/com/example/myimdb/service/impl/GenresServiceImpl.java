package com.example.myimdb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myimdb.dao.GenresMapper;
import com.example.myimdb.domain.Genres;
import com.example.myimdb.service.GenresService;
import org.springframework.stereotype.Service;

@Service
public class GenresServiceImpl extends ServiceImpl<GenresMapper, Genres> implements GenresService{
}
