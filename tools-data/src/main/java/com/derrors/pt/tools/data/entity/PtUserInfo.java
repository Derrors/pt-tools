package com.derrors.pt.tools.data.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 用户站点配置
 *
 * @author derrors
 * @date 2023/12/21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PtUserInfo extends BaseEntity<PtUserInfo> {

    /**
     * 用户 Id
     */
    private String userId;

    /**
     * 用户密钥
     */
    private String passkey;

    /**
     * 注册时间
     */
    private LocalDateTime registerDate;

    /**
     * 邮箱
     */
    private String email;

    /**
     * Cookie
     */
    private Map<String, String> cookies;

    /**
     * 是否删除 1:已删除 0:未删除
     */
    @TableLogic
    private Integer isDeleted;

    public static final String USER_ID = "user_id";
    public static final String PASS_KEY = "passkey";
    public static final String REGISTER_DATE = "register_date";
    public static final String EMAIL = "email";
    public static final String COOKIES = "Cookies";
    public static final String IS_DELETED = "is_deleted";
}
