package com.project.sale.controller;

import com.alibaba.druid.stat.TableStat;
import com.project.sale.domain.Permission;
import com.project.sale.domain.Role;
import com.project.sale.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    //给角色添加权限的方法
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name="roleId",required = true)String roleId,@RequestParam(name="ids",required = true)String[] permissionIds)throws Exception{
         roleService.addPermissionToRole(roleId,permissionIds);
         return "redirect:findAll.do";
    }


    //根据roleId查询role，并查询出可以添加的权限
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name="id",required = true)String roleId)throws Exception{
        ModelAndView mv=new ModelAndView();
          //根据roleId查询role
        Role role=roleService.findById(roleId);
        //根据roleId查询可以添加的权限
        List<Permission> otherPermissions=roleService.findOtherPermissions(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }


    @RequestMapping("/findAll.do")
    public ModelAndView findAll()throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Role> roleList=roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role)throws Exception{
        roleService.save(role);
        return "redirect:findAll.do";
    }
}
