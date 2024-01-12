package com.derrors.pt.tools.data.entity.vo;

import com.derrors.pt.tools.data.entity.PtUserInfo;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author derrors
 * @date 2024/1/9
 */
@Data
public class PtUserVo {
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
    private LocalDateTime registerDate;

    /**
     * 邮箱
     */
    private String email;

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

    public static PtUserVo buildForm(PtUserInfo ptUser) {
        if (ptUser == null) {
            return null;
        }
        PtUserVo ptUserVo = new PtUserVo();
        ptUserVo.setUserId(ptUser.getUserId());
        ptUserVo.setUid(ptUser.getUid());
        ptUserVo.setEmail(ptUser.getEmail());
        ptUserVo.setPasskey(ptUser.getPasskey());
        ptUserVo.setRegisterDate(ptUser.getRegisterDate());
        ptUserVo.setBonus(ptUser.getBonus());
        ptUserVo.setShareRatio(ptUser.getShareRatio());
        ptUserVo.setUploadCount(ptUser.getUploadCount());
        ptUserVo.setDownloadCount(ptUser.getDownloadCount());
        ptUserVo.setLevel(ptUser.getLevel());
        return ptUserVo;
    }
}
