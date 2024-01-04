package com.derrors.pt.tools.common.core.base;

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
public class BaseEntity<T extends Model<?>> extends Model<T> {
}
