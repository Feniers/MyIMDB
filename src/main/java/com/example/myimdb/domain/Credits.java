package com.example.myimdb.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@Schema(name = "演职员对象", description = " ")
public class Credits implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description ="movie_mateData id", example = "1")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "演员")
    private String cast;

    @Schema(description = "导演")
    private String crew;
}
