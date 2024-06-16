package com.example.myimdb.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
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
@TableName("links_small")
@Schema(name = "LinksSmall对象")
public class LinksSmall implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "电影id", example = "1")
    @TableId(value = "movieId")
    private Integer movieId;

    private Integer imdbId;

    private Integer tmdbId;


}
