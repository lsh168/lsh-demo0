package com.lsh.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
@Data
@Entity
@Table(name = "sys_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String name;
    @Column(name = "role_key")
    private String roleKey;
    private String status;
    @Column(name = "del_flag")
    private Integer delFlag;
    @Column(name = "create_by")
    private Long createBy;
    @Column(name = "create_time")
    private Timestamp createTime;
    @Column(name = "update_by")
    private Long updateBy;
    @Column(name = "update_time")
    private Timestamp updateTime;
    @Column(name = "remark")
    private String remark;
////以下代码会循环调用tostring，导致栈溢出错误
//    @ManyToMany(targetEntity = User.class,mappedBy = "roles" )
//    private List<User> users;
    @ManyToMany(targetEntity = Menu.class,fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_menu",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id")})
    private List<Menu> menus;
}
