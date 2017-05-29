package com.park.controller;

import com.park.dto.Result;
import com.park.enity.ParkInfo;
import com.park.enity.User;
import com.park.exception.StatusEnum;
import com.park.service.ParkService;
import com.park.service.UserService;
import com.park.socketmanage.SocketThreadManage;
import com.park.vo.RegisterVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ansore on 17-5-28.
 */
@Controller
@RequestMapping ("/web")
public class WebController {

    @Resource
    UserService userService;

    @Resource
    ParkService parkService;

    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }
    @RequestMapping("/login")
    public String loginPage(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "login";
    }

    @RequestMapping ("/check")
    public String login(HttpSession httpSession, Model model, String phone , String password) {

        //验证用户信息
        User user = userService.getUser(phone);
        if(user == null || !user.getPassword().equals(password)) {
            return "loginError";
        }
        else {
            httpSession.setAttribute("user",user);

            List<ParkInfo> l = parkService.getParksInfoList();
            if(l!=null&&l.size()!=0) {
                for (ParkInfo parkInfo : l) {
                    if (SocketThreadManage.socketThread.get(parkInfo.getParkid()) != null) {
                        parkInfo.setAlive(true);
                    } else {
                        parkInfo.setAlive(false);
                    }
                }
            }
            model.addAttribute("parksList",l);
            return "index";
        }
    }

    @RequestMapping("/reg")
    @ResponseBody
    public Result register(@RequestBody RegisterVo registerVo) {
        Result result = new Result(false,StatusEnum.getStatusCode(-1).getStatusInfo());
        if(StringUtils.isEmpty(registerVo.getPalte())||StringUtils.isEmpty(registerVo.getUsername())||StringUtils.isEmpty(registerVo.getPassword())||StringUtils.isEmpty(registerVo.getTelephone())){
            return new Result(false,StatusEnum.getStatusCode(102).getStatusInfo());
        }
        int i = userService.userRegister(registerVo);
        if(i == 1) {
            result.setStatus(true);
            result.setStatusInfo(StatusEnum.getStatusCode(201).getStatusInfo());
        }
        return result;
    }



}
