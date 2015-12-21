package com.burak;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by burak on 15.12.2015.
 */
public class SearcherThread<T> extends Thread {

    private T needle;
    private T[] haystack;
    private int start, end;
    private int result;
    private AtomicBoolean needleFound;

    public SearcherThread(T needle, T[] haystack, int start, int end,
                          AtomicBoolean needleFound) {
        this.needle = needle;
        this.haystack = haystack;
        this.start = start;
        this.end = end;
        this.needleFound = needleFound;
        this.result = -1;
    }

    @Override
    public void run()
    {
        for(int i = start; i < end; i++)
        {
            //if needle is found by any thread stop the process
            if(needleFound.get())
            {
                System.out.println("needle is found, thread " + this.toString() + " cancelling");
                return;
            }

            if( haystack[i].equals(needle) ){
                System.out.println("needle is found by thread " + this.toString() );
                result = i;
                needleFound.set(true);
                break;
            }
        }
    }

    public int getResult() {
        return result;
    }
}
