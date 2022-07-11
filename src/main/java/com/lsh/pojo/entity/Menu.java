package com.lsh.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
@Data
@Entity
@Table(name = "sys_menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "menu_name")
    private String menuName;
    private String path;
    private String component;
    private String visible;
    private String status;
    private String perms;
    private String icon;
    @Column(name = "create_by")
    private Long createBy;
    @Column(name = "create_time")
    private Timestamp createTime;
    @Column(name = "update_by")
    private Long updateBy;
    @Column(name = "update_time")
    private Timestamp updateTime;
    @Column(name = "del_flag")
    private Integer delFlag;
    private String remark;
    //以下代码会循环调用tostring，导致栈溢出错误
    //menus是role实体的一个字段
//    @ManyToMany(targetEntity = Role.class,mappedBy = "menus")
//    private List<Role> roles;

}
