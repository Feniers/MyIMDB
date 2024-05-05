package com.example.myimdb.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;

@Schema(name="User", description="用户实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id", example = "1")
    @TableId(value = "id")
    private Integer id;

    private Integer is_deleted;

    @Schema(description = "用户名", example = "admin")
    private String username;

    @Schema(description = "密码", example = "123456")
    private String password;

    @Schema(description = "昵称", example = "管理员")
    private String nickname;

    @Schema(description = "角色", example = "1")
    private Integer role;

//    @Schema(description = "头像", example = "http://www.baidu.com")
//    private String avatar;

}
