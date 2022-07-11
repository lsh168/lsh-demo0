package com.lsh.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 一个用户有多种角色
 * @author ALE
 */
@Data
@Entity
@Table(name = "sys_user")
public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * 用户名
     * 如果表命与字段名不一致，必须有column注解标注
     */
    @Column(length = 20, name = "user_name")
    private String userName;
    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    @Column(name = "phone_number")
    private String phoneNumber;
    /**
     * 用户性别（0男，1女，2未知）
     */
    private String sex;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户类型（0管理员，1普通用户）
     */
    @Column(name = "user_Type")
    private String userType;
    /**
     * 创建人的用户id
     */
    @Column(name = "create_by")
    private Long createBy;
    /**
     * 创建时间   TIMESTAMP  日期+时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 更新人
     */
    @Column(name = "update_by")
    private Long updateBy;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @Column(name = "del_flag")
    private Integer delFlag;
    //维护关联关系的是拥有外键的一方，而另一方必须配置 mappedBy。
    /**
     * @ManyToOne 一定是维护外键关系的一方，所以没有 mappedBy 字段；
     *
     * @ManyToOne 删除的时候一定不能把 One 的一方删除了，所以也没有 orphanRemoval 的选项；
     *
     * @ManyToOne 的 Lazy 效果和 @OneToOne 的一样，所以和上面的用法基本一致；
     *
     * @OneToMany 的 Lazy 是有效果的。
     *
     * @ManyToMany 代表多对多的关联关系，这种关联关系任何一方都可以维护关联关系。
     */
    //被users变量维护
    //inverseJoinColumns表示中间表中关联对方ID的字段
    @ManyToMany(targetEntity = Role.class,fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;
}
