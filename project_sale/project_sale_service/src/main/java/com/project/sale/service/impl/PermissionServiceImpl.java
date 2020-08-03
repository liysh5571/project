package com.project.sale.service.impl;

import com.project.sale.dao.IPermissionDao;
import com.project.sale.domain.Permission;
import com.project.sale.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.ImageProducer;
import java.util.List;
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }
}
