package org.transport420.sgmv.heroku;

import java.util.Date;
import java.util.TimerTask;

public class MyTimeTask extends TimerTask
{

    public void run()
    {
        //write your code here
    	System.out.println("executed at: " + new Date());
    }
}
