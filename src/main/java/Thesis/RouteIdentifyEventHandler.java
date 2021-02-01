package Thesis;

import org.matsim.api.core.v01.events.ActivityStartEvent;
import org.matsim.api.core.v01.events.PersonDepartureEvent;
import org.matsim.api.core.v01.events.VehicleEntersTrafficEvent;
import org.matsim.api.core.v01.events.handler.ActivityStartEventHandler;
import org.matsim.api.core.v01.events.handler.PersonDepartureEventHandler;
import org.matsim.api.core.v01.events.handler.VehicleEntersTrafficEventHandler;

import java.util.HashMap;


public class RouteIdentifyEventHandler implements PersonDepartureEventHandler, ActivityStartEventHandler, VehicleEntersTrafficEventHandler {



    //read in csv
    public HashMap<String,String> ppTrack = new HashMap<String, String>();
    public HashMap<String,String> vehTrack = new HashMap<String, String>();
    public HashMap<String,String> newOrigin = new HashMap<String, String>();

    @Override
    public void handleEvent(PersonDepartureEvent personDepartureEvent) {
        if(personDepartureEvent.getPersonId().toString().equals("A List of ppID")){
            ppTrack.put(personDepartureEvent.getLinkId().toString(),Double.toString(personDepartureEvent.getTime()));
        }
    }

    @Override
    public void handleEvent(VehicleEntersTrafficEvent vehicleEntersTrafficEvent) {

        if(vehicleEntersTrafficEvent.getVehicleId().toString().equals("A List of vehID")){
            vehTrack.put(vehicleEntersTrafficEvent.getLinkId().toString(),Double.toString(vehicleEntersTrafficEvent.getTime()));
        }

    }

    @Override
    public void handleEvent(ActivityStartEvent activityStartEvent) {

        if(activityStartEvent.getPersonId().toString().equals("A List of ppID") && activityStartEvent.getActType().equals("work")){

            ppTrack.keySet().retainAll(vehTrack.keySet());
            newOrigin.put(activityStartEvent.getPersonId().toString(),ppTrack.keySet().toString());
            //output the file at the end
        }

    }


}
