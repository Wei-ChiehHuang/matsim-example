package Thesis;

import org.matsim.api.core.v01.events.ActivityEndEvent;
import org.matsim.api.core.v01.events.ActivityStartEvent;
import org.matsim.api.core.v01.events.PersonArrivalEvent;
import org.matsim.api.core.v01.events.handler.ActivityEndEventHandler;
import org.matsim.api.core.v01.events.handler.ActivityStartEventHandler;
import org.matsim.api.core.v01.events.handler.PersonArrivalEventHandler;

import java.util.HashMap;

public class TravelTimeAndDistanceHandler implements ActivityEndEventHandler, ActivityStartEventHandler, PersonArrivalEventHandler {

    public HashMap<String,String> homeEndTime = new HashMap<String, String>();
    public HashMap<String,String> workStartTime = new HashMap<String, String>();
    public HashMap<String,String> linkList = new HashMap<String, String>();


    @Override
    public void handleEvent(ActivityEndEvent activityEndEvent) {
        if(activityEndEvent.getPersonId().toString().equals("A list of pp") && activityEndEvent.getActType().equals("home")){
            homeEndTime.put(activityEndEvent.getPersonId().toString(),Double.toString(activityEndEvent.getTime()));
        }
    }

    @Override
    public void handleEvent(ActivityStartEvent activityStartEvent) {
        if(activityStartEvent.getPersonId().toString().equals("A list of pp") && activityStartEvent.getActType().equals("work")){
            workStartTime.put(activityStartEvent.getPersonId().toString(), Double.toString(activityStartEvent.getTime()));
        }
    }

    @Override
    public void handleEvent(PersonArrivalEvent personArrivalEvent) {
        if(personArrivalEvent.getPersonId().toString().equals("A list of pp") && personArrivalEvent.getLinkId().toString().equals("A list of destination")){
            linkList.put(personArrivalEvent.getPersonId().toString(),personArrivalEvent.getLinkId().toString());
        }
    }
}
