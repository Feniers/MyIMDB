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

    @Schema(description = "演职员id", example = "1")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String cast;

    private String crew;
}
