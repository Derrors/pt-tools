package com.derrors.pt.tools.data.entity.vo;

import com.derrors.pt.tools.data.entity.PtUserInfo;
import java.time.LocalDateTime;
import java.util.Map;
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
    private Map<String, String> cookies;

    public static PtUserVo buildForm(PtUserInfo ptUser) {
        if (ptUser == null) {
            return null;
        }
        PtUserVo ptUserVo = new PtUserVo();
        ptUserVo.setUserId(ptUser.getUserId());
        ptUserVo.setEmail(ptUser.getEmail());
        ptUserVo.setPasskey(ptUser.getPasskey());
        ptUserVo.setRegisterDate(ptUser.getRegisterDate());
        ptUserVo.setCookies(ptUser.getCookies());
        return ptUserVo;
    }
}
