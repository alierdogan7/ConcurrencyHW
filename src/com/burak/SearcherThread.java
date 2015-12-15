package com.burak;

/**
 * Created by burak on 15.12.2015.
 */
public class SearcherThread<T> extends Thread {

    private T needle;
    private T[] haystack;
    private int start, end;
    private int result;

    public SearcherThread(T needle, T[] haystack, int start, int end) {
        this.needle = needle;
        this.haystack = haystack;
        this.start = start;
        this.end = end;
    }

    public void run()
    {
        for(int i = start; i < end; i++)
        {
            if( haystack[i].equals(needle) ){
                result = i;
                break;
            }
        }
    }

    public int getResult() {
        return result;
    }
}
