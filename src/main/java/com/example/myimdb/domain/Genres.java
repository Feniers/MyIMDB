package com.example.myimdb.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 类型
 * @TableName genres
 */
@Schema(description = "类型")
@Data
public class Genres implements Serializable {

    /**
     * 电影id
     */
    @TableId(value = "id")
    @Schema(description = "类型id")
    private Integer id;
    /**
     * 类型名称
     */
    @Schema(description = "类型名称")
    private String name;


}
