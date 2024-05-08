package com.example.myimdb.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Adonis
 * @since 2024-05-04
 */
@Data
@Schema(name = "Ratings对象")
public class Ratings implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "评分id", example = "1")
    @TableId(value = "id")
    private Integer id;

    @Schema(description = "用户id", example = "1")
    private Integer userId;

    @Schema(description = "电影id", example = "1")
    private Integer movieId;

    @Schema(description = "评分", example = "8.5")
    @Min(value = 0, message = "评分不能小于0")
    private Double rating;

    @Schema(description = "时间戳", example = "1")
    private Integer timestamp;

    public Ratings (RatingsSmall ratingsSmall){
        this.id=ratingsSmall.getId();
        this.userId=ratingsSmall.getUserId();
        this.movieId=ratingsSmall.getMovieId();
        this.rating=ratingsSmall.getRating();
        this.timestamp=ratingsSmall.getTimestamp();
    }

}
