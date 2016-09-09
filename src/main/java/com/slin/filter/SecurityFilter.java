package com.slin.filter;

/**
 * Created by slin on 16/9/3.
 */

import com.alibaba.fastjson.JSON;
import com.slin.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SecurityFilter implements Filter {

    private IUserService userService;
    
    private static ApplicationContext ctx;
    static{  
        //在类路径下寻找resources/beans.xml文件  
        ctx = new ClassPathXmlApplicationContext("application-context.xml");
    }
    
    
    public void destroy() {
        // TODO Auto-generated method stub
    }
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)res;
        response.setContentType("text/html; charset=UTF-8");
        String path = request.getRequestURI();
        Map<String,Object> map = new HashMap<String,Object>();
        String accessToken = request.getParameter("accessToken");
        //从cookie中获取accessToken
//        Cookie cook[] = request.getCookies();
//        if (cook != null) {
//            for (int i = 0; i < cook.length; i++) {
//                if(cook[i].getName().equals("accessToken")){
//                    accessToken = cook[i].getValue();
//                }
//            }
//        }
        if(path.indexOf("/login")<0){
            //token为空直接返回错误状态码
            if("".equals(accessToken)){
                map.put("statusCode",401);
                map.put("message","没有授权票");
                PrintWriter out = response.getWriter();
                out.print(JSON.toJSON(map));
                return;
            }else{
                map.put("accessToken",accessToken);
                map.put("nowTime",new Date());
//                tokenService = (ITokenService) ctx.getBean("tokenService");
//                TokenUserInfoVo tokenUser = tokenService.getTokenUserInfoByToken(map);
//                if(null != tokenUser){
//                    map.put("lastTime", MyDateUtils.getDayAfterSecond(new Date(),60*30*1));
//                    //刷新token失效时间
////                    tokenService.updateTokenUserInfoByToken(map);
//                    request.setAttribute("tokenUser",tokenUser);
//                }else{
//                    map.put("statusCode",402);
//                    map.put("message","授权票失效");
//                    PrintWriter out = response.getWriter();
//                    out.print(JSON.toJSON(map));
//                    return;
//                }

            }
        }else if(path.indexOf("/loginOut")!=-1){
            request.setAttribute("accessToken",accessToken);
        }
        chain.doFilter(request,response);

    }
    public void init(FilterConfig fConfig) throws ServletException {
        /*ServletContext sc = fConfig.getServletContext();
        XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
        if (cxt != null && cxt.getBean("userService") != null && userService == null){
            userService = (IUserService) cxt.getBean("userService");
        }
        if (cxt != null && cxt.getBean("tokenService") != null && tokenService == null){
            tokenService = (ITokenService) cxt.getBean("tokenService");
        }*/
    }
}
