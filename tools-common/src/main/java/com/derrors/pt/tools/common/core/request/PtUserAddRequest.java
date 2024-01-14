package com.derrors.pt.tools.common.core.request;

import lombok.Data;

/**
 * @author derrors
 * @date 2024/1/10
 */

@Data
public class PtUserAddRequest {

    /**
     * 用户 Id
     */
    private String userId;

    /**
     * 用户 UID
     */
    private Integer uid;

    /**
     * pt 站点 Code
     */
    private String ptCode;

    /**
     * 用户密钥
     */
    private String passkey;

    /**
     * 注册时间
     */
    private String registerDate;

    /**
     * 邮箱
     */
    private String email;

    /**
     * Cookie
     */
    private String cookies;

}
