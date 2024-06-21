package com.example.myimdb.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myimdb.dao.MoviesMetadataMapper;
import com.example.myimdb.domain.Cast;
import com.example.myimdb.domain.Crew;
import com.example.myimdb.domain.MoviesMetadata;
import com.example.myimdb.service.IMoviesMetadataService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Adonis
 * @since 2024-05-04
 */
@Service
@Slf4j
public class MoviesMetadataServiceImpl extends ServiceImpl<MoviesMetadataMapper, MoviesMetadata> implements IMoviesMetadataService {

//    private final MoviesMetadataMapper moviesMetadataMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

//    @Autowired
//    public MoviesMetadataServiceImpl(MoviesMetadataMapper moviesMetadataMapper) {
//        this.moviesMetadataMapper = moviesMetadataMapper;
//    }
    @Override
    public String getMovies() {
        return null;
    }

    @Override
    public void getMoviesPage(IPage<MoviesMetadata> page) {
        this.baseMapper.selectPage(page, null);
    }

    @Override
    public List<Map<String, Object>> findMoviesByColumnCode(String columnCode, String keyword) {
//        String columnName = mapColumnCodeToName(columnCode);
//        List<String> columnsRequired = new ArrayList<>();

        // 添加元素到列表中
//        columnsRequired.add("title");
//        columnsRequired.add("Element 2");
//        columnsRequired.add("Element 3");
//        List<Map<String, Object>> results = moviesMetadataMapper.searchMoviesWithCredits(columnName, keyword);
        List<Map<String, Object>> results = this.baseMapper.searchMoviesWithCredits(columnCode, keyword);
//        for (Map<String, Object> result : results) {
//            String crewJson = (String) result.get("crew");
//            String castJson = (String) result.get("cast");
//            try {
//                // 处理 crew 字段
//                String validCrewJson = crewJson.replace("'", "\"").replace("None", "null");
//                List<Crew> crewList = objectMapper.readValue(validCrewJson, new TypeReference<List<Crew>>() {});
//                result.put("crew", crewList);
//
//                // 处理 cast 字段
//                String validCastJson = castJson.replace("'", "\"").replace("None", "null");
//                List<Cast> castList = objectMapper.readValue(validCastJson, new TypeReference<List<Cast>>() {});
//                result.put("cast", castList);
//
//                // 处理 production_countries 字段
//                String productionCountriesJson = (String) result.get("production_countries");
//                String validProductionCountriesJson = productionCountriesJson.replace("'", "\"").replace("None", "null");
//                List<Map<String, Object>> productionCountriesList = objectMapper.readValue(validProductionCountriesJson, new TypeReference<List<Map<String, Object>>>() {});
//                result.put("production_countries", productionCountriesList);
//
//                // 处理 production_companies 字段
//                String productionCompaniesJson = (String) result.get("production_companies");
//                String validProductionCompaniesJson = productionCompaniesJson.replace("'", "\"").replace("None", "null");
//                List<Map<String, Object>> productionCompaniesList = objectMapper.readValue(validProductionCompaniesJson, new TypeReference<List<Map<String, Object>>>() {});
//                result.put("production_companies", productionCompaniesList);
//
//                // 处理 genres 字段
//                String genresJson = (String) result.get("genres");
//                String validGenresJson = genresJson.replace("'", "\"").replace("None", "null");
//                List<Map<String, Object>> genresList = objectMapper.readValue(validGenresJson, new TypeReference<List<Map<String, Object>>>() {});
//                result.put("genres", genresList);
//            } catch (Exception e) {
//                e.printStackTrace();
//                // handle the exception properly
//            }
//        }
        return results;
    }

    private String mapColumnCodeToName(String columnCode) {
        return switch (columnCode) {
            case "1" -> "title";
            case "2" -> "cast";
            case "3" -> "genres";
            default -> "title"; // 默认值，也可以选择抛出异常
        };
    }
}
