package com.derrors.pt.tools.data.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.derrors.pt.tools.data.entity.base.BaseEntity;
import java.io.Serial;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户站点配置
 *
 * @author derrors
 * @date 2023/12/21
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PtUserInfo extends BaseEntity<PtUserInfo> {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 用户 Id
     */
    private String userId;

    /**
     * 用户的 UID
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
    private LocalDateTime registerDate;

    /**
     * 邮箱
     */
    private String email;

    /**
     * Cookie
     */
    private String cookies;

    /**
     * 魔力值
     */
    private String bonus;

    /**
     * 分享率
     */
    private String shareRatio;

    /**
     * 上传量
     */
    private String uploadCount;

    /**
     * 下载量
     */
    private String downloadCount;

    /**
     * 等级
     */
    private String level;

    /**
     * 是否删除 1:已删除 0:未删除
     */
    @TableLogic
    private Integer isDeleted;

    public static final String USER_ID = "user_id";
    public static final String UID = "uid";
    public static final String PT_CODE = "pt_code";
    public static final String PASS_KEY = "passkey";
    public static final String REGISTER_DATE = "register_date";
    public static final String EMAIL = "email";
    public static final String BONUS = "bonus";
    public static final String SHARE_RATIO = "share_ratio";
    public static final String UPLOAD_COUNT = "upload_count";
    public static final String DOWNLOAD_COUNT = "download_count";
    public static final String LEVEL = "level";
    public static final String COOKIES = "cookies";
    public static final String IS_DELETED = "is_deleted";
}
