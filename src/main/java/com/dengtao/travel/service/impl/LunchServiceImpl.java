package com.dengtao.travel.service.impl;

import com.dengtao.travel.service.ILunchService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LunchServiceImpl implements ILunchService {
    @Override
    public String random(String name) {
        String[] ghmian= {"勾魂面","牛肉面","杂酱面","鸡杂面","辣鸡面","煎蛋面","豇豆面","生椒面","番茄圆子面","酸菜肉丝面"};
        String[] ghmi={"青椒肉丝","蛋炒饭","扬州炒饭","回锅饭","鸡杂","鱼香肉丝","土豆肉丝","烂肉豇豆","青笋肉丝","榨菜肉丝","盐煎肉","番茄炒蛋","酸菜肉丝"
        ,"牛肉盖浇饭","排骨盖浇饭","火腿盖浇饭"};
        if("面".equals(name)){
            int random = getRandom(ghmian);
            return ghmian[random];
        }else if("饭".equals(name)){
            int random = getRandom(ghmi);
            return ghmi[random];
        }
        return null;
    }
    public int getRandom(String[] arr){
        Random random = new Random();
        int i = random.nextInt(arr.length);
        return i;
    }
}
