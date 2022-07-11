package com.lsh.pojo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 维护关联关系的是拥有外键的一方，而另一方必须配置 mappedBy。
 *
 * @ManyToOne 一定是维护外键关系的一方，所以没有 mappedBy 字段；
 */
@Entity
@Table(name = "sys_user_role")
public class UserRole implements Serializable {
    private long userId;
    private long roleId;


    @Id
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "role_id")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole that = (UserRole) o;
        return userId == that.userId &&
                roleId == that.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}
