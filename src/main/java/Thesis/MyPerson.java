package Thesis;

public class MyPerson {



    String PersonId;
    String ActTyp;
    String LinkId;
    double EndTime;



    public MyPerson(String personId, String actType, String linkId, double time) {

        this.PersonId = personId;
        this.ActTyp = actType;
        this.LinkId = linkId;
        this.EndTime = time;



    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            MyPerson tiger = (MyPerson) object;
            if (this.PersonId == tiger.PersonId) {
                result = true;
            }
        }
        return result;
    }
    // just omitted null checks
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 7 * hash + this.PersonId.hashCode();
        return hash;
    }



}
