package com.burak;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Haystack {

    /*
    * - You can use a conditional variable to signal the control thread that an item is found.
    * This conditional variable could be created by the controller thread and passed as
    * an argument to the constructors of the searchers.

    - You can stop a searcher thread from the control thread by making a function call
    that sets an atomic boolean variable named done maintained by the searcher to true.
    This same variable could be checked within the main search loop to early terminate the search.
    */

    public static void main(String[] args) {

        int size = 1000;
        Person[] list = new Person[size];
        for(int i = 0; i < size; i++)
        {
            int tmpId = (int) (Math.random() * 10000);

            if( i == 503 )
                list[i] = new Person(tmpId);
            else
                list[i] = new Person(19991); //place the needle to 503rd location with id 19991
        }

        search( list[503], list, 7);
    }

    static <T> int search(T needle, T[] haystack, int numThreads)  {

        //Lock lock = new ReentrantLock();
        //Condition needleFound = lock.newCondition();

        AtomicBoolean needleFound = new AtomicBoolean(false);

        List<SearcherThread<T>> searchers = new ArrayList<SearcherThread<T>>();

        int numItemsPerThread = haystack.length / numThreads;
        int extraItems = haystack.length - numThreads * numItemsPerThread;

        for(int i = 0, intStart = 0; i < numThreads; i++){
            int numItems = ( i < extraItems) ? (1 + numItemsPerThread) : numItemsPerThread;
            searchers.add( new SearcherThread<T>(needle, haystack, intStart, intStart + numItems - 1,
                                                    needleFound ) );
            intStart += numItems;
        }

        for(SearcherThread<T> searcher : searchers)
        {
            searcher.start();
        }

        for(SearcherThread<T> searcher : searchers)
        {
            try {
                searcher.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(SearcherThread<T> searcher : searchers)
        {
            System.out.println(searcher.toString() + "- RESULT: " + searcher.getResult());
        }

        return 3;

    }
}
