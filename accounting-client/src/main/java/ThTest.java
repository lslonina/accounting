/*
 * Copyright (c) 2017 Nokia Networks. All rights reserved.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Lukasz Slonina.
 */
public class ThTest
{

    public static void main( String[] args )
    {
        Map<Integer, String> map = new ConcurrentHashMap<>();


        testB();

        //testA();
    }


    public static void testB()
    {
        TimerTask task = new TimerTask()

        {

            @Override

            public void run()

            {

                System.out.println(System.currentTimeMillis());

            }

        };

        Timer timer = new Timer();

        timer.schedule(task, 0, 1000);
    }


    public static void testA()
    {
        Runnable job = () -> {
            int a = 1 / 0;
        };

        Thread thread = new Thread( job );

        Thread.UncaughtExceptionHandler ueh = ( t, e ) -> {
            System.out.printf( "Caught: %s for thread: %s%n", e, t );
        };

        Thread.UncaughtExceptionHandler uehd = ( t, e ) -> {
            System.out.println("Default handler");
            System.out.printf( "Caught: %s for thread: %s%n", e, t );
        };

        thread.setUncaughtExceptionHandler( ueh );
        Thread.setDefaultUncaughtExceptionHandler( uehd );

        thread.start();
    }
}
