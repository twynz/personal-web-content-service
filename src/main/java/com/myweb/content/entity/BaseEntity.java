package com.myweb.content.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8388417013613884409L;

    @JsonIgnore
    private Timestamp createTime;

    @JsonIgnore
    private Timestamp updateTime;

    @JsonIgnore
    private UUID createBy;

    @JsonIgnore
    private UUID updateBy;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public UUID getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UUID createBy) {
        this.createBy = createBy;
    }

    public UUID getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(UUID updateBy) {
        this.updateBy = updateBy;
    }

    public void populateCreation() {
        this.setCreateTime(new Timestamp(System.currentTimeMillis()));
    }

    public void populateUpdate() {
        this.setUpdateTime(new Timestamp(System.currentTimeMillis()));
    }

}
