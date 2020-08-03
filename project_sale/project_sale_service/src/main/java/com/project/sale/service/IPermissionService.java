package com.project.sale.service;

import com.project.sale.domain.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll()throws Exception;

    void save(Permission permission)throws Exception;
}
