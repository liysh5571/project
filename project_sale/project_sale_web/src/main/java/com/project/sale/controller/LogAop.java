package com.project.sale.controller;

import com.project.sale.domain.SysLog;
import com.project.sale.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;//开始时间
    private Class clazz;
    private Method method;
     //前置通知,主要是获取开始时间，执行的类是哪一个，执行的方法是哪一个
    @Before("execution(* com.project.sale.controller.*.*(..))")
    public void deBefore(JoinPoint jp)throws NoSuchMethodException{
         visitTime=new Date();//当前开始访问的时间
        clazz=jp.getTarget().getClass();//具体访问的类
        String methodName=jp.getSignature().getName();  //获取访问的方法的名称
        Object[]args=jp.getArgs();   //获取访问的方法的参数
        //获取具体执行的method对象
        if(args==null||args.length==0) {
            method = clazz.getMethod(methodName);//只能获取无参数的方法
        }else{
            Class[] classArgs=new Class[args.length];
            for(int i=0;i<args.length;i++){
                classArgs[i]=args[i].getClass();
            }
            clazz.getMethod(methodName,classArgs);
        }
    }
    //后置通知
    @After("execution(* com.project.sale.controller.*.*(..))")
    public void deAfter(JoinPoint jp)throws Exception{
        long time=new Date().getTime()-visitTime.getTime();//获取访问时长
         String url="";
        //获取url
        if(clazz!=null&&method!=null&&clazz!= LogAop.class){
            //获取类上@RequestMapping上的（“/”）
            RequestMapping classAnnotation=(RequestMapping)clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                String[] classValue=classAnnotation.value();
                //获取方法上的@RequestMapping（xxx）
                RequestMapping methodAnnotation= method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    String[] methodValue=methodAnnotation.value();
                    url=classValue[0]+methodValue[0];
                    //获取访问的ip地址
                    String ip=request.getRemoteAddr();

                    //如何获取当前操作的用户
                    SecurityContext context= new SecurityContextHolder().getContext();//从上下文中获取当前登录的用户
                    User user=(User)context.getAuthentication().getPrincipal();
                    String username=user.getUsername();

                    //将日志相关信息封装到SysLog中
                    SysLog sysLog=new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] "+clazz.getName()+"[方法名] "+method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                    //调用Service完成操作
                    sysLogService.save(sysLog);
                }

            }
        }

    }
}
