package com.derrors.pt.tools.data.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.derrors.pt.tools.data.entity.base.BaseEntity;

import java.io.Serial;
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

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 站点名称
     */
    private String name;

    /**
     * 站点简称
     */
    private String alias;

    /**
     * 站点 Code
     */
    private String code;

    /**
     * 站点地址
     */
    private String url;

    /**
     * 站点描述
     */
    private String description;

    /**
     * 是否删除 1:已删除 0:未删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 数据库字段
     */
    public static final String NAME = "name";
    public static final String ALIAS = "alias";
    public static final String DESC = "description";
    public static final String CODE = "code";
    public static final String URL = "url";
    public static final String IS_DELETED = "is_deleted";
}
