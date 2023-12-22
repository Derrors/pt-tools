package com.derrors.pt.tools.data.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Pt 站点信息
 *
 * @author derrors
 * @date 2023/12/21
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PtNode extends BaseEntity<PtNode> {

    /**
     * 站点名称
     */
    private String ptName;

    /**
     * 站点 Code
     */
    private String ptCode;

    /**
     * 站点地址
     */
    private String ptUrl;

    /**
     * 站点描述
     */
    private String ptDescription;

    /**
     * 是否删除 1:已删除 0:未删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 数据库字段
     */
    public static final String PT_NAME = "pt_name";
    public static final String PT_DESC = "pt_description";
    public static final String PT_CODE = "pt_code";
    public static final String PT_URL = "pt_url";
    public static final String IS_DELETED = "id_deleted";
}
