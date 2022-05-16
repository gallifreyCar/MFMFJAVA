package com.Gallifrey.springboot00.contronller;

import com.Gallifrey.springboot00.bean.QueryInfo;
import com.Gallifrey.springboot00.bean.User;
import com.Gallifrey.springboot00.mapper.UserMapper;
import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController

public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/allUser")
    public HashMap getUserList(QueryInfo queryInfo){

        //获取最大列表数和当前编号
        int numbers=userMapper.getUserCounts("%"+queryInfo.getQuery()+"%");
        int pageStart=(queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        List<User> users= userMapper.getAllUser("%"+queryInfo.getQuery()+"%",pageStart,queryInfo.getPageSize());
        HashMap<String ,Object> res=new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",users);
        //不转换也可以，springboot能把传回去的hashmap转化成json串
//        String res_string= JSON.toJSONString(res);
//        return res_string;
        //所以这里就不转了
        return res;
    }

    @RequestMapping("/userState")
    public String updateUserState(int id,Boolean state){
        int i=userMapper.updateState(id,state);
                return i>0?"success":"error";
    }

    @RequestMapping("/addUser")
    public String addUser(@RequestBody User user){
        user.setState(false);
        int i=userMapper.addUser(user);
        return i>0?"success":"error";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(int id){
        int i=userMapper.deleteUser(id);
        return i>0?"success":"error";
    }

    @RequestMapping("/getUpdateUser")
    public User getUpdateUser(int id){
        User user=userMapper.getUpdateUser(id);
        return user;
    }
    @RequestMapping("/editUser")
    public String editUser(@RequestBody User user){
        int i=userMapper.editUser(user);
        return i>0?"success":"error";
    }
}
