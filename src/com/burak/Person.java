package com.burak;

/**
 * Created by burak on 15.12.2015.
 */
public class Person {
    int id;

    public Person(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o)
    {
        /*try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        return ((Person) o).getId() == this.id;
    }
}
