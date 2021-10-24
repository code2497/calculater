package com.calculater.demo;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class HandleCal {
    /**
     * 输出算式
     * @param sum 符号数量
     * @param bound 范围
     */
    public static void outputExercises(int sum, int bound) {
        if (bound <= 0 || sum <= 0) {
            throw new RuntimeException("bound or sun must greater than 0");
        }
        Set<Expression> set = new HashSet<>();
        try (BufferedWriter exercisesWriter = new BufferedWriter(new FileWriter("Exercises.txt"));
             BufferedWriter answerWriter = new BufferedWriter(new FileWriter("Answers.txt"))
        ) {
            for (int i = 1; set.size()< sum;) {
                try {
                    //因为在运算的过程中会出现n÷0的情况，这时候就会抛异常
                    Expression expression = new Expression(3, bound);
                    expression.toString();
                    if (!set.contains(expression)) {
                        exercisesWriter.write(i + "." + expression.toString() + "\n");
                        answerWriter.write(i + "." + expression.getResult() + "\n");
                        set.add(expression);
                        i++;
                    }
                } catch (RuntimeException ignored) {

                }

            }
            exercisesWriter.flush();
            answerWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出计算成绩
     * @param exercisePath 表达式文件路径
     * @param answerPath 结果文件路径
     * @param gradePath 输出结果文件路径
     */
    public static void outputGrade(String exercisePath, String answerPath, String gradePath) {
        try (BufferedReader exReader = new BufferedReader(new FileReader(exercisePath));
             BufferedReader anReader = new BufferedReader(new FileReader(answerPath));
             BufferedWriter gradeWriter = new BufferedWriter(new FileWriter(gradePath))
        ) {
            String ex, an;
            int c = 0, w = 0;
            StringBuilder correct = new StringBuilder("Correct: %d (");
            StringBuilder wrong = new StringBuilder("Wrong: %d (");
            while ((ex = exReader.readLine()) != null && (an = anReader.readLine()) != null) {
                int exPoint = ex.indexOf(".");
                int anPoint = an.indexOf(".");
                if (exPoint != -1 && anPoint != -1) {
                    int i = Integer.valueOf(ex.substring(0,exPoint).trim());
                    Expression expression = new Expression(ex.substring(exPoint + 1));
                    Fraction answer = new Fraction(an.substring(anPoint + 1));
                    if (expression.getResult().equals(answer.toString())) {
                        c++;
                        correct.append(" ").append(i);
                        if (c % 20 == 0) {
                            correct.append("\n");
                        }
                    } else {
                        w++;
                        wrong.append(" ").append(i);
                        if (w % 20 == 0) {
                            wrong.append("\n");
                        }
                    }
                }
            }
            gradeWriter.write(String.format(correct.append(" )\n").toString(),c));
            gradeWriter.write(String.format(wrong.append(" )\n").toString(),w));
            gradeWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
