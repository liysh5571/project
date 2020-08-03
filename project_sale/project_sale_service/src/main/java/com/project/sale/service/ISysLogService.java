package com.project.sale.service;

import com.project.sale.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    public void save(SysLog sysLog)throws Exception;

    List<SysLog> findAll()throws Exception;
}
