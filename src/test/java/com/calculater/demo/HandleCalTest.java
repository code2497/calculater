package com.calculater.demo;

import org.junit.Test;

public class HandleCalTest {
    private HandleCal function = new HandleCal();

    @Test
    public void outputExercises() {
        function.outputExercises(10,20);

    }

    @Test
    public void outputGrade() {
        function.outputGrade("Exercises.txt","Answers.txt","Grade.txt");
    }
}
