package com.project.sale.service;

import com.project.sale.domain.Role;
import com.project.sale.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    UserInfo findById(String id)throws Exception;
    List<UserInfo> findAll()throws Exception;
    void save(UserInfo userInfo)throws Exception;
    List<Role> findOtherRoles(String userId)throws Exception;
    void addRoleToUser(String userId,String[]roleIds)throws Exception;
}
