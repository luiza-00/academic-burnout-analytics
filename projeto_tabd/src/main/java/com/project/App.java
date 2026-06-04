package com.project;

import com.project.services.tasks.ApiConsumer;

/**
 * Hello world!
 *
 */
public class App 
{
        public static void main(String[] args) {

        ApiConsumer apiConsumer2 = new ApiConsumer("sridipbasu/ai-depndency-career-anxiety-and-student-burnout");

        apiConsumer2.start();

        try {

            apiConsumer2.join();
        
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        
        System.out.println("Response2: " + apiConsumer2.getResponse());
    }
}
