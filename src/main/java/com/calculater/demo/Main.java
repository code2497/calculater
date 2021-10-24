package com.calculater.demo;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {


    public static void main(String[] args) {
        System.out.println(args[0]);
        int exerciseCount = 0, bound = 0;
        String exercisePath = "", answerPath = "",gradePath = "./Grade.txt";

        for(int i = 0;i<args.length-1;i++){
            if("-n".equals(args[i])){
                exerciseCount = Integer.parseInt(args[i+1]);
            }
            if("-r".equals(args[i])){
                bound = Integer.parseInt(args[i+1]);
            }
            if("-e".equals(args[i])){
                exercisePath = args[i+1];
            }
            if("-a".equals(args[i])){
                answerPath = args[i+1];
            }
        }


        if (bound <= 0 || exerciseCount <= 0) {
            System.out.println("生成练习题参数输入错误或未输入，不生成练习题");
        } else {
            HandleCal.outputExercises(exerciseCount,bound);
            System.out.println("练习题与答案生成于当前目录下");
        }


        if (answerPath.isEmpty() || exercisePath.isEmpty()) {
            System.out.println("未输入答案文件路径或练习题文件路径，不生成成绩");
        } else {
            HandleCal.outputGrade(exercisePath, answerPath, gradePath);
            System.out.println("练习题与答案生成于当前目录下");
        }
    }
}
