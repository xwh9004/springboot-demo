package com.example.common.util;

import java.util.Random;

public class RandUtil {

    /**
     * 模拟随机调用超时
     */
    public static void  randomlyRunLong(int bound){
        Random rand = new Random();
        int randNum = rand.nextInt(bound)+1;
        if(randNum==3)sleep();
    }
    private static void sleep(){
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
