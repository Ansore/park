package com.park.controller;

import com.park.dto.Result;
import com.park.enity.ParkInfo;
import com.park.exception.ParkException;
import com.park.exception.StatusEnum;
import com.park.service.ParkService;
import com.park.service.UserService;
import com.park.vo.LoginVo;
import com.park.vo.RegisterVo;
import com.park.vo.RequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import com.park.data.Data;

import java.util.List;

/**
 * 处理手机HTTP请求
 * Created by ansore on 16-9-11.
 */
@Controller
@RequestMapping(value =  "/")
public class PhoneController {

    @Autowired
    ParkService parkService;

    @Autowired
    UserService userService;

    @RequestMapping (value = "/phone", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result dealRequest(HttpServletRequest request, @RequestBody RequestVo requestVo){

        Result result = new Result(false, StatusEnum.getStatusCode(101).getStatusInfo());

        if(request.getMethod().toLowerCase().equals("get")){
            result.setStatusInfo(StatusEnum.getStatusCode(103).getStatusInfo());
            return result;
        }

        //检测输入字段
        if(requestVo==null|| StringUtils.isEmpty(requestVo.getAction())) {
            result.setStatusInfo(StatusEnum.getStatusCode(102).getStatusInfo());
            return result;
        }

        try {

            /**
             * 处理注册请求
             */
            if(requestVo.getAction().equals(Data.Reg)) {
                //检测输入字段
                if(StringUtils.isEmpty(requestVo.getPalte())||StringUtils.isEmpty(requestVo.getUsername())||StringUtils.isEmpty(requestVo.getPassword())||StringUtils.isEmpty(requestVo.getTelephone())){
                    result.setStatusInfo(StatusEnum.getStatusCode(102).getStatusInfo());
                    return result;
                }
                //实体转化
                RegisterVo registerVo = new RegisterVo();
                registerVo.setTelephone(requestVo.getTelephone());
                registerVo.setPassword(requestVo.getPassword());
                registerVo.setPalte(requestVo.getPalte());
                registerVo.setUsername(requestVo.getUsername());

                //Service 请求
                int i = userService.userRegister(registerVo);
                if(i == 1) {
                    result.setStatus(true);
                    result.setStatusInfo(StatusEnum.getStatusCode(201).getStatusInfo());
                }
            }

            /**
             * 处理登录请求
             */
            if(requestVo.getAction().equals(Data.LoginPhone)) {

                //检测输入字段
                if(StringUtils.isEmpty(requestVo.getTelephone())||StringUtils.isEmpty(requestVo.getPassword())){
                    result.setStatusInfo(StatusEnum.getStatusCode(102).getStatusInfo());
                    return result;
                }

                //实体转化
                LoginVo loginVo = new LoginVo();
                loginVo.setTelephone(requestVo.getTelephone());
                loginVo.setPassword(requestVo.getPassword());

                //service 请求
                int i = userService.userLogin(loginVo);
                if(i==1) {
                    result.setStatus(true);
                    result.setStatusInfo(StatusEnum.getStatusCode(200).getStatusInfo());
                }
            }

            /**
             * 获取停车场信息列表
             */
            if(requestVo.getAction().equals(Data.GetParksInfo)) {
                //构造返回数据
               // result = new Result<ParkInfo>(false,StatusEnum.getStatusCode(101).getStatusInfo());

                List<ParkInfo> l = parkService.getParksInfoList();
                if(l!=null&&l.size()!=0) {
                    result.setStatus(true);
                    result.setData(l);
                    result.setStatusInfo(StatusEnum.getStatusCode(202).getStatusInfo());
                }

                return result;
            }

            /**
             * 获取车位信息
             */
            if(requestVo.getAction().equals(Data.GetLotInfo)) {

                //检测输入字段
                if(StringUtils.isEmpty(requestVo.getParkId())) {
                    result.setStatusInfo(StatusEnum.getStatusCode(102).getStatusInfo());
                    return result;
                }

                //TODO

            }

            /**
             * 预订车位
             */
            if(requestVo.getAction().equals(Data.OrderPark)) {

                //检测输入字段
                if(StringUtils.isEmpty(requestVo.getParkId())||StringUtils.isEmpty(requestVo.getSpaceId())||StringUtils.isEmpty(requestVo.getTelephone())) {
                    result.setStatusInfo(StatusEnum.getStatusCode(102).getStatusInfo());
                    return result;
                }

                //TODO
            }

            /**
             * 获取预订信息
             */
            if(requestVo.getAction().equals(Data.GetOrderInfo)) {

                //检测输入字段
                if(StringUtils.isEmpty(requestVo.getTelephone())) {
                    result.setStatusInfo(StatusEnum.getStatusCode(102).getStatusInfo());
                    return result;
                }

                //TODO
            }

            /**
             * 开闸 （主）
             */
            if(requestVo.getAction().equals(Data.OpenMainRelay)) {

                //TODO
            }

            /**
             * 解锁车位
             */
            if(requestVo.getAction().equals(Data.UnlockRelay)) {

                //检测输入字段
                if(StringUtils.isEmpty(requestVo.getParkId())||StringUtils.isEmpty(requestVo.getSpaceId())) {
                    result.setStatusInfo(StatusEnum.getStatusCode(102).getStatusInfo());
                    return result;
                }

                //TODO
            }

            /**
             * 锁定车位
             */
            if(requestVo.getAction().equals(Data.LockRelay)) {

                //检测输入字段
                if(StringUtils.isEmpty(requestVo.getParkId())||StringUtils.isEmpty(requestVo.getSpaceId())) {
                    result.setStatusInfo(StatusEnum.getStatusCode(102).getStatusInfo());
                    return result;
                }

                //TODO
            }

            /**
             * 结束停车
             */
            if(requestVo.getAction().equals(Data.EndPark)) {

                //检测输入字段
                if(StringUtils.isEmpty(requestVo.getParkId())||StringUtils.isEmpty(requestVo.getSpaceId())) {
                    result.setStatusInfo(StatusEnum.getStatusCode(102).getStatusInfo());
                    return result;
                }

                //TODO
            }

            /**
             * 模拟支付
             */
            if(requestVo.getAction().equals(Data.PayMoney)) {

                //支付确认
                result.setStatus(true);
                result.setStatusInfo(StatusEnum.getStatusCode(203).getStatusInfo());

            }


        } catch (ParkException pe) {

            result.setStatusInfo(pe.getStatusInfo());
            return result;
        } catch (Exception e) {
            result.setStatusInfo(StatusEnum.getStatusCode(101).getStatusInfo());
            return result;
        }
        return result;
    }
}
