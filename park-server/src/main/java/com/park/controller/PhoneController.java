package com.park.controller;

import com.park.dao.OrderInfoDAO;
import com.park.dto.Message;
import com.park.dto.Result;
import com.park.enity.OrderInfo;
import com.park.enity.ParkInfo;
import com.park.enity.User;
import com.park.exception.ParkException;
import com.park.exception.StatusEnum;
import com.park.service.ParkService;
import com.park.service.UserService;
import com.park.socketmanage.SocketThreadManage;
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

    //TODO 写入Serivice
    @Autowired
    OrderInfoDAO orderInfoDAO;

    @RequestMapping (value = "/phone", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result dealRequest(HttpServletRequest request, @RequestBody RequestVo requestVo){

        Result result = new Result(false, StatusEnum.getStatusCode(-1).getStatusInfo());

        if(request.getMethod().toLowerCase().equals("get")){
            result.setStatus(false);
            result.setStatusInfo(StatusEnum.getStatusCode(103).getStatusInfo());
            return result;
        }

        //检测输入字段
        if(requestVo==null|| StringUtils.isEmpty(requestVo.getAction())) {
            result.setStatus(false);
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
                    result.setStatus(false);
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
                return result;
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
                return result;
            }

            /**
             * 获取停车场信息列表
             */
            if(requestVo.getAction().equals(Data.GetParksInfo)) {
                //构造返回数据
               // result = new Result<ParkInfo>(false,StatusEnum.getStatusCode(101).getStatusInfo());
                List<ParkInfo> l = parkService.getParksInfoList();
                if(l!=null&&l.size()!=0) {
                    for (ParkInfo parkInfo : l) {
                        if(SocketThreadManage.socketThread.get(parkInfo.getParkid()) != null) {
                            parkInfo.setAlive(true);
                        } else {
                            parkInfo.setAlive(false);
                        }
                    }
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

                if(SocketThreadManage.socketThread.get(requestVo.getParkId())==null) {
                    result.setStatusInfo(StatusEnum.getStatusCode(104).getStatusInfo());
                    return  result;
                }

                //PC交互
                Message message = new Message();
                message.setMessageType(Data.GetParkInfo);
                Message m = SocketThreadManage.socketThread.get(requestVo.getParkId()).sendMessageWait(message);

                if(m==null) {
                    result.setStatusInfo(StatusEnum.getStatusCode(105).getStatusInfo());
                    return result;
                }
                //赋值返回
                result.setStatus(true);
                result.setStatusInfo(StatusEnum.getStatusCode(202).getStatusInfo());
                result.setData(m.getParkList());

                //释放线程Message对象
                SocketThreadManage.socketThread.get(requestVo.getParkId()).freeMessage();
                return result;
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

                if(SocketThreadManage.socketThread.get(requestVo.getParkId())==null) {
                    result.setStatusInfo(StatusEnum.getStatusCode(104).getStatusInfo());
                    return result;
                }

                if(orderInfoDAO.orderIsExist(requestVo.getTelephone())>0) {
                    result.setStatusInfo(StatusEnum.getStatusCode(106).getStatusInfo());
                    return result;
                }
                Message message = new Message();
                message.setMessageType(Data.OrderInfo);
                message.setParkId(requestVo.getSpaceId());
                message.setTelephone(requestVo.getTelephone());
                User user = userService.getUser(requestVo.getTelephone());
                message.setPalte(user.getPalte());
                message.setUserName(user.getUsername());
                Message m = SocketThreadManage.socketThread.get(requestVo.getParkId()).sendMessageWait(message);
                if(m==null||m.getStatu()!=true) {
                    result.setStatusInfo(StatusEnum.getStatusCode(105).getStatusInfo());
                    return result;
                }
                //写入数据库
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setParkid(requestVo.getParkId());
                orderInfo.setPhone(requestVo.getTelephone());
                orderInfoDAO.addOrderInfo(orderInfo);
                result.setStatus(true);
                result.setStatusInfo(StatusEnum.getStatusCode(205).getStatusInfo());
                //释放线程Message对象
                SocketThreadManage.socketThread.get(requestVo.getParkId()).freeMessage();
                return result;
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

                if(orderInfoDAO.orderIsExist(requestVo.getTelephone())==0) {
                    result.setStatusInfo(StatusEnum.getStatusCode(107).getStatusInfo());
                    return result;
                }

                int parkid = orderInfoDAO.getOrderInfo(requestVo.getTelephone()).getParkid();

                if(SocketThreadManage.socketThread.get(parkid)==null) {
                    result.setStatusInfo(StatusEnum.getStatusCode(104).getStatusInfo());
                    return result;
                }

                Message message = new Message();
                message.setMessageType(Data.GetOrderInfoPC);
                message.setTelephone(requestVo.getTelephone());
                Message m = SocketThreadManage.socketThread.get(parkid).sendMessageWait(message);

                if(m==null||m.getStatu()!=true) {
                    result.setStatusInfo(StatusEnum.getStatusCode(105).getStatusInfo());
                    return result;
                }
                List<ParkInfo> l = parkService.getParksInfoList();
                for(int i = 0; i < l.size(); i++) {
                    if(l.get(i).getParkid()==parkid) {
                        result.setPhone(l.get(i).getTelephone());
                    }
                }
                result.setStatus(true);
                result.setParkId(parkid);
                result.setParkName(m.getParkName());
                result.setLotId(m.getParkId());
                result.setStatusInfo(StatusEnum.getStatusCode(205).getStatusInfo());
                //释放线程Message对象
                SocketThreadManage.socketThread.get(parkid).freeMessage();
                return result;
            }

            /**
             * 开闸 （主）
             */
            if(requestVo.getAction().equals(Data.OpenMainRelay)) {

                //检测输入字段
                if(StringUtils.isEmpty(requestVo.getParkId())) {
                    result.setStatusInfo(StatusEnum.getStatusCode(102).getStatusInfo());
                    return result;
                }

                if(SocketThreadManage.socketThread.get(requestVo.getParkId())==null) {
                    result.setStatusInfo(StatusEnum.getStatusCode(104).getStatusInfo());
                    return result;
                }

                Message message = new Message();
                message.setMessageType(Data.Unlock);
                message.setParkId(0);
                Message m = SocketThreadManage.socketThread.get(requestVo.getParkId()).sendMessageWait(message);
                if(m == null || m.getStatu() == false) {
                    result.setStatus(false);
                    result.setStatusInfo(StatusEnum.getStatusCode(-1).getStatusInfo());
                    SocketThreadManage.socketThread.get(requestVo.getParkId()).freeMessage();
                    return result;
                }
                result.setStatus(true);
                result.setStatusInfo(StatusEnum.getStatusCode(204).getStatusInfo());
                SocketThreadManage.socketThread.get(requestVo.getParkId()).freeMessage();
                return result;
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
                //发送控制信息给PC
                if(SocketThreadManage.socketThread.get(requestVo.getParkId())!=null) {
                    Message message = new Message();
                    message.setMessageType(Data.Unlock);
                    message.setParkId(requestVo.getSpaceId());
                    Message m = SocketThreadManage.socketThread.get(requestVo.getParkId()).sendMessageWait(message);
                    if(m == null || m.getStatu() == false) {
                        SocketThreadManage.socketThread.get(requestVo.getParkId()).freeMessage();
                        result.setStatus(false);
                        result.setStatusInfo(StatusEnum.getStatusCode(-1).getStatusInfo());
                        return result;
                    }
                    result.setStatus(true);
                    result.setStatusInfo(StatusEnum.getStatusCode(204).getStatusInfo());
                    SocketThreadManage.socketThread.get(requestVo.getParkId()).freeMessage();
                    return result;
                }
                else {
                    result.setStatusInfo(StatusEnum.getStatusCode(104).getStatusInfo());
                    return result;
                }
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
                if(SocketThreadManage.socketThread.get(requestVo.getParkId())==null) {
                    result.setStatusInfo(StatusEnum.getStatusCode(104).getStatusInfo());
                    return result;
                }

                //发送控制信息给PC
                if(SocketThreadManage.socketThread.get(requestVo.getParkId())!=null) {
                    Message message = new Message();
                    message.setMessageType(Data.Lock);
                    message.setParkId(requestVo.getSpaceId());
                    Message m = SocketThreadManage.socketThread.get(requestVo.getParkId()).sendMessageWait(message);
                    if(m == null || m.getStatu() == false) {
                        SocketThreadManage.socketThread.get(requestVo.getParkId()).freeMessage();
                        result.setStatus(false);
                        result.setStatusInfo(StatusEnum.getStatusCode(-1).getStatusInfo());
                        return result;
                    }
                    result.setStatus(true);
                    result.setStatusInfo(StatusEnum.getStatusCode(204).getStatusInfo());
                    SocketThreadManage.socketThread.get(requestVo.getParkId()).freeMessage();
                    return result;
                }
                else {
                    result.setStatusInfo(StatusEnum.getStatusCode(104).getStatusInfo());
                    return result;
                }
            }

            //关闭LED
            if(requestVo.getAction().equals(Data.offLED)) {
                //检测输入字段
                if(StringUtils.isEmpty(requestVo.getParkId())||StringUtils.isEmpty(requestVo.getSpaceId())) {
                    result.setStatusInfo(StatusEnum.getStatusCode(102).getStatusInfo());
                    return result;
                }
                if(SocketThreadManage.socketThread.get(requestVo.getParkId())==null) {
                    result.setStatusInfo(StatusEnum.getStatusCode(104).getStatusInfo());
                    return result;
                }
                if(SocketThreadManage.socketThread.get(requestVo.getParkId())!=null) {
                    Message message = new Message();
                    message.setMessageType(Data.LEDOff);
                    message.setParkId(requestVo.getSpaceId());
                    Message m = SocketThreadManage.socketThread.get(requestVo.getParkId()).sendMessageWait(message);
                    if(m == null || m.getStatu() == false) {
                        result.setStatus(false);
                        result.setStatusInfo(StatusEnum.getStatusCode(-1).getStatusInfo());
                        SocketThreadManage.socketThread.get(requestVo.getParkId()).freeMessage();
                        return result;
                    }
                    result.setStatus(true);
                    result.setStatusInfo(StatusEnum.getStatusCode(204).getStatusInfo());
                    SocketThreadManage.socketThread.get(requestVo.getParkId()).freeMessage();
                    return result;
                }
                else {
                    result.setStatusInfo(StatusEnum.getStatusCode(104).getStatusInfo());
                    return result;
                }
            }

            //开启LED
            if(requestVo.getAction().equals(Data.offLED)) {
                //检测输入字段
                if(StringUtils.isEmpty(requestVo.getParkId())||StringUtils.isEmpty(requestVo.getSpaceId())) {
                    result.setStatusInfo(StatusEnum.getStatusCode(102).getStatusInfo());
                    return result;
                }
                if(SocketThreadManage.socketThread.get(requestVo.getParkId())==null) {
                    result.setStatusInfo(StatusEnum.getStatusCode(104).getStatusInfo());
                    return result;
                }
                if(SocketThreadManage.socketThread.get(requestVo.getParkId())!=null) {
                    Message message = new Message();
                    message.setMessageType(Data.LEDOn);
                    message.setParkId(requestVo.getSpaceId());
                    Message m = SocketThreadManage.socketThread.get(requestVo.getParkId()).sendMessageWait(message);
                    if(m == null || m.getStatu() != true) {
                        result.setStatus(false);
                        result.setStatusInfo(StatusEnum.getStatusCode(-1).getStatusInfo());
                        SocketThreadManage.socketThread.get(requestVo.getParkId()).freeMessage();
                        return result;
                    }
                    result.setStatus(true);
                    result.setStatusInfo(StatusEnum.getStatusCode(204).getStatusInfo());
                    SocketThreadManage.socketThread.get(requestVo.getParkId()).freeMessage();
                    return result;
                }
                else {
                    result.setStatusInfo(StatusEnum.getStatusCode(104).getStatusInfo());
                    return result;
                }
            }

            /**
             * 结束停车
             */
            if(requestVo.getAction().equals(Data.EndPark)) {

                //检测输入字段
                if(StringUtils.isEmpty(requestVo.getTelephone())||StringUtils.isEmpty(requestVo.getParkId())||StringUtils.isEmpty(requestVo.getSpaceId())) {
                    result.setStatusInfo(StatusEnum.getStatusCode(102).getStatusInfo());
                    return result;
                }

                //PC通信
                if(SocketThreadManage.socketThread.get(requestVo.getParkId())==null) {
                    result.setStatusInfo(StatusEnum.getStatusCode(104).getStatusInfo());
                    return result;
                }

                Message message = new Message();
                message.setMessageType(Data.EndParkInfo);
                message.setParkId(requestVo.getSpaceId());
                Message m = SocketThreadManage.socketThread.get(requestVo.getParkId()).sendMessageWait(message);
                if(m == null||m.getStatu()!=true) {
                    result.setStatus(false);
                    result.setStatusInfo(StatusEnum.getStatusCode(105).getStatusInfo());
                    SocketThreadManage.socketThread.get(requestVo.getParkId()).freeMessage();
                    return result;
                }
                orderInfoDAO.delOrderInfo(requestVo.getTelephone());
                result.setStatus(true);
                result.setPayNum(m.getPayNum());
                result.setStatusInfo(StatusEnum.getStatusCode(204).getStatusInfo());
                //释放线程Message对象
                SocketThreadManage.socketThread.get(requestVo.getParkId()).freeMessage();
                return result;
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
            e.printStackTrace();
            result.setStatusInfo(StatusEnum.getStatusCode(101).getStatusInfo());
            return result;
        }
        return result;
    }
}