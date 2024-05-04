package com.example.myimdb.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
@TableName("ratings_small")
@Schema(name = "RatingsSmall对象")
public class RatingsSmall implements Serializable {

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
    private Double rating;

    @Schema(description = "时间戳", example = "1")
    private Integer timestamp;


}
