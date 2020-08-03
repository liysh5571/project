package com.project.sale.service.impl;

import com.project.sale.dao.ISysLogDao;
import com.project.sale.domain.SysLog;
import com.project.sale.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {
    @Autowired
    private ISysLogDao sysLogDao;


    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }


    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
