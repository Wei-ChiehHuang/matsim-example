package Thesis.Step1_DetermineAffectedCommuters;

import org.matsim.api.core.v01.events.*;
import org.matsim.api.core.v01.events.handler.*;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.api.experimental.events.AgentWaitingForPtEvent;
import org.matsim.core.api.experimental.events.VehicleDepartsAtFacilityEvent;
import org.matsim.core.api.experimental.events.handler.AgentWaitingForPtEventHandler;
import org.matsim.core.api.experimental.events.handler.VehicleDepartsAtFacilityEventHandler;
import org.matsim.api.core.v01.events.handler.PersonEntersVehicleEventHandler;

import java.io.StringWriter;
import java.util.*;

public class AffectedPersonEventHandler implements PersonEntersVehicleEventHandler, PersonLeavesVehicleEventHandler , VehicleDepartsAtFacilityEventHandler, PersonDepartureEventHandler {

    public List<String> EnterVehPPId = new ArrayList<>();
    public List<String> EnterVehVId = new ArrayList<>();
    public List<String> EnterVehTime = new ArrayList<>();

    public List<String> LeaveVehPPId = new ArrayList<>();
    public List<String> LeaveVehVId = new ArrayList<>();
    public List<String> LeaveVehTime = new ArrayList<>();

    public List<String> VehDeptVId = new ArrayList<>();
    public List<String> VehDeptStationId = new ArrayList<>();
    public List<String> VehDeptTime = new ArrayList<>();

    public List<String> PPDeparturePPId = new ArrayList<>();
    public List<String> PPDepartureLinkId = new ArrayList<>();
    public List<String> PPDepartureTime = new ArrayList<>();

    @Override
    public void handleEvent(PersonEntersVehicleEvent personEntersVehicleEvent) {
        if(personEntersVehicleEvent.getVehicleId().toString().equals("train3502584-48")||
           personEntersVehicleEvent.getVehicleId().toString().equals("train3502584-49")||
//                personEntersVehicleEvent.getVehicleId().toString().equals("train3502584-50")||
//                personEntersVehicleEvent.getVehicleId().toString().equals("train3502584-51")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502584-52")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502584-53")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502584-54")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502584-55")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502584-56")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502584-57")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502584-58")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502584-59")||
           personEntersVehicleEvent.getVehicleId().toString().equals("train3502585-48")||
           personEntersVehicleEvent.getVehicleId().toString().equals("train3502585-49")||
//                personEntersVehicleEvent.getVehicleId().toString().equals("train3502585-50")||
//                personEntersVehicleEvent.getVehicleId().toString().equals("train3502585-51")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502585-52")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502585-53")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502585-54")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502585-55")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502585-56")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502585-57")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502585-58")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502585-59")||
           personEntersVehicleEvent.getVehicleId().toString().equals("train3502653-48")||
           personEntersVehicleEvent.getVehicleId().toString().equals("train3502653-49")||
//                personEntersVehicleEvent.getVehicleId().toString().equals("train3502653-50")||
//                personEntersVehicleEvent.getVehicleId().toString().equals("train3502653-51")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502653-52")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502653-53")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502653-54")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502653-55")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502653-56")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502653-57")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502653-58")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502653-59")||
           personEntersVehicleEvent.getVehicleId().toString().equals("train3502654-48")||
           personEntersVehicleEvent.getVehicleId().toString().equals("train3502654-49")){
//                personEntersVehicleEvent.getVehicleId().toString().equals("train3502654-50")||
//                personEntersVehicleEvent.getVehicleId().toString().equals("train3502654-51")){
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502654-52")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502654-53")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502654-54")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502654-55")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502654-56")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502654-57")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502654-58")||
//                    personEntersVehicleEvent.getVehicleId().toString().equals("train3502654-59")){
            EnterVehPPId.add(personEntersVehicleEvent.getPersonId().toString());
            EnterVehVId.add(personEntersVehicleEvent.getVehicleId().toString());
            EnterVehTime.add(Double.toString(personEntersVehicleEvent.getTime()));
        }
    }

    @Override
    public void handleEvent(PersonLeavesVehicleEvent personLeavesVehicleEvent) {
        if(personLeavesVehicleEvent.getVehicleId().toString().equals("train3502584-48")||
           personLeavesVehicleEvent.getVehicleId().toString().equals("train3502584-49")||
//                personLeavesVehicleEvent.getVehicleId().toString().equals("train3502584-50")||
//                personLeavesVehicleEvent.getVehicleId().toString().equals("train3502584-51")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502584-52")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502584-53")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502584-54")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502584-55")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502584-56")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502584-57")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502584-58")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502584-59")||
           personLeavesVehicleEvent.getVehicleId().toString().equals("train3502585-48")||
           personLeavesVehicleEvent.getVehicleId().toString().equals("train3502585-49")||
//                personLeavesVehicleEvent.getVehicleId().toString().equals("train3502585-50")||
//                personLeavesVehicleEvent.getVehicleId().toString().equals("train3502585-51")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502585-52")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502585-53")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502585-54")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502585-55")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502585-56")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502585-57")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502585-58")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502585-59")||
           personLeavesVehicleEvent.getVehicleId().toString().equals("train3502653-48")||
           personLeavesVehicleEvent.getVehicleId().toString().equals("train3502653-49")||
//                personLeavesVehicleEvent.getVehicleId().toString().equals("train3502653-50")||
//                personLeavesVehicleEvent.getVehicleId().toString().equals("train3502653-51")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502653-52")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502653-53")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502653-54")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502653-55")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502653-56")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502653-57")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502653-58")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502653-59")||
           personLeavesVehicleEvent.getVehicleId().toString().equals("train3502654-48")||
           personLeavesVehicleEvent.getVehicleId().toString().equals("train3502654-49")){
//                personLeavesVehicleEvent.getVehicleId().toString().equals("train3502654-50")||
//                personLeavesVehicleEvent.getVehicleId().toString().equals("train3502654-51")){
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502654-52")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502654-53")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502654-54")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502654-55")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502654-56")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502654-57")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502654-58")||
//                    personLeavesVehicleEvent.getVehicleId().toString().equals("train3502654-59")){
            LeaveVehPPId.add(personLeavesVehicleEvent.getPersonId().toString());
            LeaveVehVId.add(personLeavesVehicleEvent.getVehicleId().toString());
            LeaveVehTime.add(Double.toString(personLeavesVehicleEvent.getTime()));
        }
    }

    @Override
    public void handleEvent(VehicleDepartsAtFacilityEvent vehicleDepartsAtFacilityEvent) {

        if(vehicleDepartsAtFacilityEvent.getFacilityId().toString().equals("2665022810-3502584")||
           vehicleDepartsAtFacilityEvent.getFacilityId().toString().equals("2665022748-3502585")||
           vehicleDepartsAtFacilityEvent.getFacilityId().toString().equals("2665022745-3502653")||
           vehicleDepartsAtFacilityEvent.getFacilityId().toString().equals("2441182955-3502654")){

            if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-48")||
               vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-49")||
//                    vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-50")||
//                    vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-51")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-52")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-53")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-54")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-55")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-56")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-57")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-58")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-59")||
               vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-48")||
               vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-49")||
//                    vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-50")||
//                    vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-51")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-52")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-53")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-54")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-55")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-56")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-57")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-58")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-59")||
               vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-48")||
               vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-49")||
//                    vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-50")||
//                    vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-51")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-52")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-53")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-54")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-55")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-56")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-57")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-58")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-59")||
               vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-48")||
               vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-49")){
//                    vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-50")||
//                    vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-51")){
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-52")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-53")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-54")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-55")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-56")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-57")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-58")||
//                        vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-59")){
                VehDeptVId.add(vehicleDepartsAtFacilityEvent.getVehicleId().toString());
                VehDeptStationId.add(vehicleDepartsAtFacilityEvent.getFacilityId().toString());
                VehDeptTime.add(Double.toString(vehicleDepartsAtFacilityEvent.getTime()));
            }

        }

    }

    @Override
    public void handleEvent(PersonDepartureEvent personDepartureEvent) {

        if(personDepartureEvent.getLinkId().toString().contains("ptbus") && personDepartureEvent.getLegMode().equals("pt")){
            PPDeparturePPId.add(personDepartureEvent.getPersonId().toString());
            PPDepartureLinkId.add(personDepartureEvent.getLinkId().toString());
            PPDepartureTime.add(Double.toString(personDepartureEvent.getTime()));
        }
    }


//    //Feldmoching-Messestadt Ost interrupted
//        public HashMap<String,String> train3502584_48_Boarding = new HashMap<String, String>();
//        public HashMap<String,String> train3502584_48_Leaving = new HashMap<String, String>();
//        public HashMap<String,String> train3502584_49_Boarding = new HashMap<String, String>();
//        public HashMap<String,String> train3502584_49_Leaving = new HashMap<String, String>();
//        //Messestadt Ost-Feldmoching interrupted
//        public HashMap<String,String> train3502585_48_Boarding = new HashMap<String, String>();
//        public HashMap<String,String> train3502585_48_Leaving = new HashMap<String, String>();
//        public HashMap<String,String> train3502585_49_Boarding = new HashMap<String, String>();
//        public HashMap<String,String> train3502585_49_Leaving = new HashMap<String, String>();
//
//        //Mangfallplatz-Olympia-Einkaufszentrum interrupted
//        public HashMap<String,String> train3502653_48_Boarding = new HashMap<String, String>();
//        public HashMap<String,String> train3502653_48_Leaving = new HashMap<String, String>();
//        public HashMap<String,String> train3502653_49_Boarding = new HashMap<String, String>();
//        public HashMap<String,String> train3502653_49_Leaving = new HashMap<String, String>();
//
//        //Olympia-Einkaufszentrum-Mangfallplatz interrupted
//        public HashMap<String,String> train3502654_48_Boarding = new HashMap<String, String>();
//        public HashMap<String,String> train3502654_48_Leaving = new HashMap<String, String>();
//        public HashMap<String,String> train3502654_49_Boarding = new HashMap<String, String>();
//        public HashMap<String,String> train3502654_49_Leaving = new HashMap<String, String>();
//
//        public HashMap<String,String> train3502584_Confronting = new HashMap<String, String>();
//
//        private Network network;
//
//	    public AffectedPersonEventHandler(Network network ) {
//            this.network = network ;
//            //reset(0);
//        }
//
//
//
//        @Override
//        public void handleEvent(AgentWaitingForPtEvent agentWaitingForPtEvent) {
//
//            if(agentWaitingForPtEvent.getWaitingAtStopId().toString().equals("2665022810-3502584")||agentWaitingForPtEvent.getWaitingAtStopId().toString().equals("2665022748-3502585")||agentWaitingForPtEvent.getWaitingAtStopId().toString().equals("2665022745-3502653")||agentWaitingForPtEvent.getWaitingAtStopId().toString().equals("2441182955-3502654")){
//                train3502584_Confronting.put(agentWaitingForPtEvent.getPersonId().toString(),agentWaitingForPtEvent.waitingAtStopId.toString());
//            }
//        }
//

//        @Override
//        public void handleEvent(PersonEntersVehicleEvent personEntersVehicleEvent) {
//            //Feldmoching-Messestadt Ost interrupted
//	        if(personEntersVehicleEvent.getVehicleId().toString().equals("train3502584-48")){
//                train3502584_48_Boarding.put(personEntersVehicleEvent.getPersonId().toString(),"get access time") ;
//            }
//            if(personEntersVehicleEvent.getVehicleId().toString().equals("train3502584-49")){
//                train3502584_49_Boarding.put(personEntersVehicleEvent.getPersonId().toString(),"get access time") ;
//            }
//
//            //Messestadt Ost-Feldmoching interrupted
//            if(personEntersVehicleEvent.getVehicleId().toString().equals("train3502585-48")){
//                train3502585_48_Boarding.put(personEntersVehicleEvent.getPersonId().toString(),"get access time") ;
//            }
//            if(personEntersVehicleEvent.getVehicleId().toString().equals("train3502585-49")){
//                train3502585_49_Boarding.put(personEntersVehicleEvent.getPersonId().toString(),"get access time") ;
//            }
//
//            //Mangfallplatz-Olympia-Einkaufszentrum interrupted
//            if(personEntersVehicleEvent.getVehicleId().toString().equals("train3502653-48")){
//                train3502653_48_Boarding.put(personEntersVehicleEvent.getPersonId().toString(),"get access time") ;
//            }
//            if(personEntersVehicleEvent.getVehicleId().toString().equals("train3502653-49")){
//                train3502653_49_Boarding.put(personEntersVehicleEvent.getPersonId().toString(),"get access time") ;
//            }
//
//            //Olympia-Einkaufszentrum-Mangfallplatz interrupted
//            if(personEntersVehicleEvent.getVehicleId().toString().equals("train3502654-48")){
//                train3502654_48_Boarding.put(personEntersVehicleEvent.getPersonId().toString(),"get access time") ;
//            }
//            if(personEntersVehicleEvent.getVehicleId().toString().equals("train3502654-49")){
//                train3502654_49_Boarding.put(personEntersVehicleEvent.getPersonId().toString(),"get access time") ;
//            }
//
//        }

//        @Override
//        public void handleEvent(PersonLeavesVehicleEvent personLeavesVehicleEvent) {
//            //Feldmoching-Messestadt Ost interrupted
//	        if(personLeavesVehicleEvent.getVehicleId().toString().equals("train3502584-48")){
//                train3502584_48_Leaving.put(personLeavesVehicleEvent.getPersonId().toString(),personLeavesVehicleEvent.getVehicleId().toString()) ;
//            }
//            if(personLeavesVehicleEvent.getVehicleId().toString().equals("train3502584-49")){
//                train3502584_49_Leaving.put(personLeavesVehicleEvent.getPersonId().toString(),personLeavesVehicleEvent.getVehicleId().toString()) ;
//            }
//
//            //Messestadt Ost-Feldmoching interrupted
//            if(personLeavesVehicleEvent.getVehicleId().toString().equals("train3502585-48")){
//                train3502585_48_Leaving.put(personLeavesVehicleEvent.getPersonId().toString(),personLeavesVehicleEvent.getVehicleId().toString()) ;
//            }
//            if(personLeavesVehicleEvent.getVehicleId().toString().equals("train3502585-49")){
//                train3502585_49_Leaving.put(personLeavesVehicleEvent.getPersonId().toString(),personLeavesVehicleEvent.getVehicleId().toString()) ;
//            }
//
//            //Mangfallplatz-Olympia-Einkaufszentrum interrupted
//            if(personLeavesVehicleEvent.getVehicleId().toString().equals("train3502653-48")){
//                train3502653_48_Leaving.put(personLeavesVehicleEvent.getPersonId().toString(),personLeavesVehicleEvent.getVehicleId().toString()) ;
//            }
//            if(personLeavesVehicleEvent.getVehicleId().toString().equals("train3502653-49")){
//                train3502653_49_Leaving.put(personLeavesVehicleEvent.getPersonId().toString(),personLeavesVehicleEvent.getVehicleId().toString()) ;
//            }
//
//            //Olympia-Einkaufszentrum-Mangfallplatz interrupted
//            if(personLeavesVehicleEvent.getVehicleId().toString().equals("train3502654-48")){
//                train3502654_48_Leaving.put(personLeavesVehicleEvent.getPersonId().toString(),personLeavesVehicleEvent.getVehicleId().toString()) ;
//            }
//            if(personLeavesVehicleEvent.getVehicleId().toString().equals("train3502654-49")){
//                train3502654_49_Leaving.put(personLeavesVehicleEvent.getPersonId().toString(),personLeavesVehicleEvent.getVehicleId().toString()) ;
//            }
//	    }

//
//
//        @Override
//        public void handleEvent(VehicleDepartsAtFacilityEvent vehicleDepartsAtFacilityEvent) {
//
//                //Feldmoching-Messestadt Ost interrupted
//	            if(vehicleDepartsAtFacilityEvent.getFacilityId().toString().equals("2665022810-3502584")){
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-48")){
//                        train3502584_48_Boarding.keySet().removeAll(train3502584_48_Leaving.keySet());
//                        // write on-boared affected
//                    }
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-49")){
//                        train3502584_49_Boarding.keySet().removeAll(train3502584_49_Leaving.keySet());
//                        // write on-boared affected
//                    }
//                }
//                if(vehicleDepartsAtFacilityEvent.getFacilityId().toString().equals("2665022780-3502584")){
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-48")){
//                        train3502584_48_Boarding.keySet().removeAll(train3502584_48_Leaving.keySet());
//                        // write en-route affected
//                    }
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502584-49")){
//                        train3502584_49_Boarding.keySet().removeAll(train3502584_49_Leaving.keySet());
//                        // en-route
//                    }
//                }
//
//                //Messestadt Ost-Feldmoching interrupted
//                if(vehicleDepartsAtFacilityEvent.getFacilityId().toString().equals("2665022748-3502585")){
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-48")){
//                        train3502585_48_Boarding.keySet().removeAll(train3502585_48_Leaving.keySet());
//                    }
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-49")){
//                        train3502585_49_Boarding.keySet().removeAll(train3502585_49_Leaving.keySet());
//                    }
//                }
//                if(vehicleDepartsAtFacilityEvent.getFacilityId().toString().equals("2622046995-3502585")){
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-48")){
//                        train3502585_48_Boarding.keySet().removeAll(train3502585_48_Leaving.keySet());
//                    }
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502585-49")){
//                        train3502585_49_Boarding.keySet().removeAll(train3502585_49_Leaving.keySet());
//                    }
//                }
//
//                //Mangfallplatz-Olympia-Einkaufszentrum interrupted
//                if(vehicleDepartsAtFacilityEvent.getFacilityId().toString().equals("2665022745-3502653")){
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-48")){
//                        train3502653_48_Boarding.keySet().removeAll(train3502653_48_Leaving.keySet());
//                    }
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-49")){
//                        train3502653_49_Boarding.keySet().removeAll(train3502653_49_Leaving.keySet());
//                    }
//                }
//                if(vehicleDepartsAtFacilityEvent.getFacilityId().toString().equals("2552438497-3502653")){
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-48")){
//                        train3502653_48_Boarding.keySet().removeAll(train3502653_48_Leaving.keySet());
//                    }
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502653-49")){
//                        train3502653_49_Boarding.keySet().removeAll(train3502653_49_Leaving.keySet());
//                    }
//                }
//
//                //Olympia-Einkaufszentrum-Mangfallplatz interrupted
//                if(vehicleDepartsAtFacilityEvent.getFacilityId().toString().equals("2441182955-3502654")){
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-48")){
//                        train3502654_48_Boarding.keySet().removeAll(train3502654_48_Leaving.keySet());
//                    }
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-49")){
//                        train3502654_49_Boarding.keySet().removeAll(train3502654_49_Leaving.keySet());
//                    }
//                }
//                if(vehicleDepartsAtFacilityEvent.getFacilityId().toString().equals("2554223953-3502654")){
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-48")){
//                        train3502654_48_Boarding.keySet().removeAll(train3502654_48_Leaving.keySet());
//                    }
//                    if(vehicleDepartsAtFacilityEvent.getVehicleId().toString().equals("train3502654-49")){
//                        train3502654_49_Boarding.keySet().removeAll(train3502654_49_Leaving.keySet());
//                    }
//                }
//        }
}