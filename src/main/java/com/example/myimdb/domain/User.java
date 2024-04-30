package com.example.myimdb.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Schema(name="User", description="用户实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("user")
public class User {
    @Schema(description = "用户id", example = "1")
    private long id;
    @Schema(description = "用户名", example = "admin")
    private String username;
    @Schema(description = "密码", example = "123456")
    private String password;
    @Schema(description = "昵称", example = "管理员")
    private String nickname;

}
