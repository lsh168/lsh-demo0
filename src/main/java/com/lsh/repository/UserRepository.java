package com.lsh.repository;

import com.lsh.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //通过用户名查找用户
    Optional<User> findByUserName(String userName);
    //创建用户


    //通过id获取用户权限信息
//    @Query(value = "select distinct m.perms from sys_user_role ur left join "
//            "        FROM\n" +
//            "            sys_user_role ur\n" +
//            "            LEFT JOIN `sys_role` r ON ur.`role_id` = r.`id`\n" +
//            "            LEFT JOIN `sys_role_menu` rm ON ur.`role_id` = rm.`role_id`\n" +
//            "            LEFT JOIN `sys_menu` m ON m.`id` = rm.`menu_id`\n" +
//            "        WHERE\n" +
//            "            user_id = #{userid}\n" +
//            "            AND r.`status` = 0\n" +
//            "            AND m.`status` = 0",nativeQuery = true)
//    List findBy

}
