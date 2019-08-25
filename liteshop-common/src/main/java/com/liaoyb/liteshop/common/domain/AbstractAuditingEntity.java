package com.liaoyb.liteshop.common.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 基础审计信息对象
 *
 * @author liaoyb
 */
@Data
public abstract class AbstractAuditingEntity {
    /**
     * 创建者
     */
    private Long createdBy;
    /**
     * 创建时间
     */
    private LocalDateTime createdDate;
    /**
     * 更新者
     */
    private Long lastModifiedBy;
    /**
     * 上次更新时间
     */
    private LocalDateTime lastModifiedDate;
}
