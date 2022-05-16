package com.Gallifrey.springboot00.contronller;

import com.Gallifrey.springboot00.bean.User;
import com.Gallifrey.springboot00.mapper.UserMapper;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@CrossOrigin("http://localhost:8080")
@RestController
public class LoginController {


    @Autowired
    UserMapper userMapper;

    @RequestMapping("/login")
    public HashMap login(@RequestBody User user){

            User trueUser=userMapper.getInfo(user);
            String flag="error";
            System.out.println("TureUser: "+trueUser);
            HashMap<String,Object> res=new HashMap<>();

            if(trueUser!=null){
                flag="ok";
            }
            res.put("flag",flag);
            res.put("trueUser",trueUser);
            //String res_json=JSON.toJSONString(res);// 直接传hashmap回去也行，springboot会吧map自动打包成json串
            return res;




    }
}
