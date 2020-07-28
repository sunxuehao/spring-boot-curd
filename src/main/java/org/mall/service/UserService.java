package org.mall.service;

import com.github.pagehelper.PageInfo;
import org.mall.domain.Commodity;
import org.mall.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    //新增学生信息
    void insertUser(User user);

    //修改学生信息
    void updateUser(User user);

    //根据id查询获取学生信息
    User findById(Integer id);

    //根据id删除学生信息
    void deleteById(Integer id);


    //    下面市县pagehelper的分页功能
    List<User> findAllUserByPageF(int pageNum, int pageSize);

    PageInfo<User> findAllUserByPageS(int pageNum, int pageSize);
}

