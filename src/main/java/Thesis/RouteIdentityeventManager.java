package Thesis;

import org.matsim.api.core.v01.network.Network;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsReaderXMLv1;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.io.MatsimNetworkReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class RouteIdentityeventManager {

    public static void main(String[] args) throws IOException {
        EventsManager events = EventsUtils.createEventsManager();


        Network network = NetworkUtils.createNetwork();
        MatsimNetworkReader networkReader = new MatsimNetworkReader(network);
        networkReader.readFile("OutputMunich_5_Initial/output_network.xml.gz");

//        RouteIdentifyEventHandler handler3 = new RouteIdentifyEventHandler();
//        events.addHandler(handler3);
//
//        TravelTimeAndDistanceHandler handler4 = new TravelTimeAndDistanceHandler();
//        events.addHandler(handler4);

//        EventsReaderXMLv1 eventsReader = new EventsReaderXMLv1(events);
//        eventsReader.readFile("OutputMunich_5_Initial/output_events.xml.gz");
//
//        File PersonEnterVehicle = new File("temp/10_personEnterVehicleSummary.csv");
//        java.io.FileWriter ppEnterSummary = new java.io.FileWriter(PersonEnterVehicle);
//        BufferedWriter ppEnterWriter = new BufferedWriter(ppEnterSummary);
//        ppEnterWriter.write("PersonId, VehicleId, EnterTime");
//        ppEnterWriter.newLine();
//        for(int i=0; i<handler2.EnterVehPPId.size(); i++)
//        {
//            ppEnterWriter.write(handler2.EnterVehPPId.get(i) +","+
//                    handler2.EnterVehVId.get(i) +","+
//                    handler2.EnterVehTime.get(i));
//            ppEnterWriter.newLine();
//        }
//        ppEnterWriter.close();
//        ppEnterSummary.close();
    }
}
