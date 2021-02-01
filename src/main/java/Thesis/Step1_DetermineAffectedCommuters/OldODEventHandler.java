package Thesis.Step1_DetermineAffectedCommuters;

import org.matsim.api.core.v01.events.ActivityEndEvent;
import org.matsim.api.core.v01.events.ActivityStartEvent;
import org.matsim.api.core.v01.events.handler.ActivityEndEventHandler;
import org.matsim.api.core.v01.events.handler.ActivityStartEventHandler;
import org.matsim.api.core.v01.network.Network;

import java.util.ArrayList;
import java.util.List;


public class OldODEventHandler implements ActivityEndEventHandler, ActivityStartEventHandler {

    public List<String> homeId = new ArrayList<>();
    public List<String> workId = new ArrayList<>();
    //public List<MyPerson> homeList = new ArrayList<>();
    public List<String> PPList = new ArrayList<>();
    public List<String> ActList = new ArrayList<>();
    public List<String> LinkList = new ArrayList<>();
    public List<Double> EndTimeList = new ArrayList<>();

    private Network network;

    public OldODEventHandler(Network network ) {
        this.network = network ;
        reset(0);
    }

    @Override
    public void handleEvent(ActivityEndEvent activityEndEvent) {
        if(activityEndEvent.getActType().equals("home")){
            this.homeId.add(activityEndEvent.getPersonId().toString());
            this.PPList.add(activityEndEvent.getPersonId().toString());
            this.ActList.add(activityEndEvent.getActType().toString());
            this.LinkList.add(activityEndEvent.getLinkId().toString());
            this.EndTimeList.add(activityEndEvent.getTime());
        }

    }

    @Override
    public void handleEvent(ActivityStartEvent activityStartEvent) {
        if(activityStartEvent.getActType().equals("work")||activityStartEvent.getActType().equals("education")){
            this.workId.add(activityStartEvent.getPersonId().toString());
            this.PPList.add(activityStartEvent.getPersonId().toString());
            this.ActList.add(activityStartEvent.getActType().toString());
            this.LinkList.add(activityStartEvent.getLinkId().toString());
            this.EndTimeList.add(activityStartEvent.getTime());
        }
    }
}