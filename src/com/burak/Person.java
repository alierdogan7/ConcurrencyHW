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
         return ((Person) o).getId() == this.id;
    }
}
