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
@Schema(name = "Keywords对象")
public class Keywords implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "关键词id", example = "1")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "关键词", example = "[{'id': 240, 'name': 'underdog'}, {'id': 378, 'name': 'prison'}, {'id': 730, 'name': 'factory worker'}, {'id': 1563, 'name': 'prisoner'}, {'id': 1787, 'name': 'helsinki'}, {'id': 10183, 'name': 'independent film'}, {'id': 13072, 'name': 'falling in love'}]")
    private String keywords;

}
