import java.util.Timer;
import java.util.TimerTask;


/*
 * Copyright (c) 2017 Nokia Networks. All rights reserved.
 */
public class BackAndForth
{
    enum Direction
    {
        FORWARDS, BACKWARDS
    }


    public static void main( String[] args )
    {
        TimerTask task = new TimerTask()
        {
            final static int MAXSTEPS = 20;
            volatile Direction direction = Direction.FORWARDS;
            volatile int steps = 0;


            @Override
            public void run()
            {
                switch( direction )
                {
                    case FORWARDS:
                        System.out.print( "\b " );
                        System.out.print( "*" );
                        break;
                    case BACKWARDS:
                        System.out.print( "\b " );
                        System.out.print( "\b\b*" );
                        if( ++steps == MAXSTEPS )
                        {
                            direction = (direction == Direction.FORWARDS) ? Direction.BACKWARDS
                                : Direction.FORWARDS;
                            steps = 0;
                        }
                }

            }
        };

        Timer timer = new Timer();
        timer.schedule( task, 0, 100 );
    }
}
