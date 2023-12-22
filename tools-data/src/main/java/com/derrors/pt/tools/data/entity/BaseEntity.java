package com.derrors.pt.tools.data.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * BaseEntity
 *
 * @author derrors
 * @date 2023/12/21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseEntity<T extends BaseEntity> extends Model {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(fill = FieldFill.INSERT)
    protected Long id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    protected String createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected String updateTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    protected String creator;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected String modifier;

    public static final String ID = "id";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";
    public static final String TENANT_ID = "tenant_id";
}
