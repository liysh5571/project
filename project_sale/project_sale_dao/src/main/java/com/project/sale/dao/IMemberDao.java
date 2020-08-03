package com.project.sale.dao;

import com.project.sale.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {
    @Select("select * from member where id=#{id}")
    public Member findById(String id)throws Exception;
}
