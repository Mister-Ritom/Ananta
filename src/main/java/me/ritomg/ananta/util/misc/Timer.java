package me.ritomg.ananta.util.misc;

import me.ritomg.ananta.util.Util;

@SuppressWarnings("unused")
//Good now it works maybe lol
public class Timer extends Util {

    long time;

    public Timer() {
        time = -1L;
    }

    public boolean passed(long ms)
    {
        return System.currentTimeMillis() - this.time >= ms;
    }

    public void reset()
    {
        this.time = System.currentTimeMillis();
    }

    public void resetTimeSkipTo(long ms)
    {
        this.time = System.currentTimeMillis() + ms;
    }

    public long getTime()
    {
        return time;
    }

    public void setTime(long time)
    {
        this.time = time;
    }

}
