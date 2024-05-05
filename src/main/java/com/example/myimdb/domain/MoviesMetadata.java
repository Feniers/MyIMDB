package com.example.myimdb.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Adonis
 * @since 2024-05-04
 */
@Data
@TableName("movies_metadata")
@Schema(name = "MoviesMetadata对象")
public class MoviesMetadata implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id", example = "1")
    @TableId(value = "id")
    private Integer id;

    private Integer is_deleted;

    @Schema(description = "IMDB id", example = "1")
    private String imdbId;

    @Schema(description = "是否是成人内容", example = "False")
    private boolean adult;

    @Schema(description = "所属集合", example = "{'id': 10194, 'name': 'Toy Story Collection', 'poster_path': '/7G9915LfUQ2lVfwMEEhDsn3kT4B.jpg', 'backdrop_path': '/9FBwqcd9IRruEDUrTdcaafOMKUq.jpg'}")
    private String belongsToCollection;

    @Schema(description = "预算", example = "30000000")
    private Integer budget;

    @Schema(description = "类型", example = "[{'id': 16, 'name': 'Animation'}, {'id': 35, 'name': 'Comedy'}, {'id': 10751, 'name': 'Family'}]")
    private String genres;

    @Schema(description = "主页", example = "http://toystory.disney.com/toy-story")
    private String homepage;

    @Schema(description = "原始语言", example = "en")
    private String originalLanguage;

    @Schema(description = "原始标题", example = "Toy Story")
    private String originalTitle;

    @Schema(description = "概述", example = "Led by Woody, Andy's toys live happily in his room until Andy's birthday brings Buzz Lightyear onto the scene. Afraid of losing his place in Andy's heart, Woody plots against Buzz. But when circumstances separate Buzz and Woody from their owner, the duo eventually learns to put aside their differences.")
    private String overview;

    @Schema(description = "流行度", example = "21.946943")
    private Double popularity;

    @Schema(description = "海报路径", example = "/rhIRbceoE9lR4veEXuwCC2wARtG.jpg")
    private String posterPath;

    @Schema(description = "制作公司", example = "[{'name': 'Pixar Animation Studios', 'id': 3}]")
    private String productionCompanies;

    @Schema(description = "制作国家", example = "[{'iso_3166_1': 'US', 'name': 'United States of America'}]")
    private String productionCountries;

    @Schema(description = "发布日期", example = "1995-10-30")
    private Date releaseDate;

    @Schema(description = "收入", example = "373554033")
    private Integer revenue;

    @Schema(description = "运行时间", example = "81.0")
    private Double runtime;

    @Schema(description = "语言", example = "[{'iso_639_1': 'en', 'name': 'English'}]")
    private String spokenLanguages;

    @Schema(description = "状态", example = "Released")
    private String status;

    @Schema(description = "标语", example = "Roll the dice and unleash the excitement!")
    private String tagline;

    @Schema(description = "标题", example = "Toy Story")
    private String title;

    @Schema(description = "是否有视频", example = "False")
    private boolean video;

    @Schema(description = "评分", example = "7.7")
    private Double voteAverage;

    @Schema(description = "评分次数", example = "5415")
    private Integer voteCount;

//    @Override
//    public String toString() {
//        return "MoviesMetadata{" +
//                "id = " + id +
//                ", imdbId = " + imdbId +
//                ", adult = " + adult +
//                ", belongsToCollection = " + belongsToCollection +
//                ", budget = " + budget +
//                ", genres = " + genres +
//                ", homepage = " + homepage +
//                ", originalLanguage = " + originalLanguage +
//                ", originalTitle = " + originalTitle +
//                ", overview = " + overview +
//                ", popularity = " + popularity +
//                ", posterPath = " + posterPath +
//                ", productionCompanies = " + productionCompanies +
//                ", productionCountries = " + productionCountries +
//                ", releaseDate = " + releaseDate +
//                ", revenue = " + revenue +
//                ", runtime = " + runtime +
//                ", spokenLanguages = " + spokenLanguages +
//                ", status = " + status +
//                ", tagline = " + tagline +
//                ", title = " + title +
//                ", video = " + video +
//                ", voteAverage = " + voteAverage +
//                ", voteCount = " + voteCount +
//                "}";
//    }
}
