package com.example.myimdb.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Schema(name = "User", description = "用户实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Table("user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

//    @Schema(description = "用户id", example = "20")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer is_deleted;

    @Schema(description = "用户名", example = "admin")
//    @NotEmpty(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码", example = "123456")
//    @NotBlank(message = "密码不能为空")
    private String password;

//    @Schema(description = "昵称", example = "管理员")
    private String nickname;

//    @Schema(description = "角色", example = "1")
    @Range(min = 0, max = 1, message = "角色只能为0或1")
    private Integer role;


//    public Map<String, Object> toMap() {
//        return Map.of("id", id, "username", username, "nickname", nickname, "role", role);
//    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
