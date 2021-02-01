package Thesis.Step1_DetermineAffectedCommuters;

import Thesis.Step1_DetermineAffectedCommuters.AffectedPersonEventHandler;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsReaderXMLv1;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.io.MatsimNetworkReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class AffectedPersonEventManager {

    public static void main(String[] args) throws IOException {
        EventsManager events = EventsUtils.createEventsManager();


        Network network = NetworkUtils.createNetwork();
        MatsimNetworkReader networkReader = new MatsimNetworkReader(network);
        networkReader.readFile("OutputMunich_initial/output_network.xml.gz");

        AffectedPersonEventHandler handler2 = new AffectedPersonEventHandler();
        events.addHandler(handler2);

        EventsReaderXMLv1 eventsReader = new EventsReaderXMLv1(events);
        eventsReader.readFile("OutputMunich_initial/output_events.xml.gz");

        File PersonEnterVehicle = new File("temp/10_personEnterVehicleSummary_SC10.csv");
        java.io.FileWriter ppEnterSummary = new java.io.FileWriter(PersonEnterVehicle);
        BufferedWriter ppEnterWriter = new BufferedWriter(ppEnterSummary);
        ppEnterWriter.write("PersonId, VehicleId, EnterTime");
        ppEnterWriter.newLine();
        for(int i=0; i<handler2.EnterVehPPId.size(); i++)
        {
            ppEnterWriter.write(handler2.EnterVehPPId.get(i) +","+
                    handler2.EnterVehVId.get(i) +","+
                    handler2.EnterVehTime.get(i));
            ppEnterWriter.newLine();
        }
        ppEnterWriter.close();
        ppEnterSummary.close();

        File PersonLeaveVehicle = new File("temp/11_personLeaveVehicleSummary_SC10.csv");
        java.io.FileWriter ppLeaveSummary = new java.io.FileWriter(PersonLeaveVehicle);
        BufferedWriter ppLeaveWriter = new BufferedWriter(ppLeaveSummary);
        ppLeaveWriter.write("PersonId, VehicleId, LeaveTime");
        ppLeaveWriter.newLine();
        for(int i=0; i<handler2.LeaveVehPPId.size(); i++)
        {
            ppLeaveWriter.write(handler2.LeaveVehPPId.get(i) +","+
                    handler2.LeaveVehVId.get(i) +","+
                    handler2.LeaveVehTime.get(i));
            ppLeaveWriter.newLine();
        }
        ppLeaveWriter.close();
        ppLeaveSummary.close();

        File VehicleDeparture = new File("temp/12_vehicleDepartureSummary_SC10.csv");
        java.io.FileWriter vehDepartureSummary = new java.io.FileWriter(VehicleDeparture);
        BufferedWriter vehDepartureWriter = new BufferedWriter(vehDepartureSummary);
        vehDepartureWriter.write("VehicleId, StationId, DeptTime");
        vehDepartureWriter.newLine();
        for(int i=0; i<handler2.VehDeptVId.size(); i++)
        {
            vehDepartureWriter.write(handler2.VehDeptVId.get(i) +","+
                    handler2.VehDeptStationId.get(i) +","+
                    handler2.VehDeptTime.get(i));
            vehDepartureWriter.newLine();
        }
        vehDepartureWriter.close();
        vehDepartureSummary.close();

        File PersonDeparture = new File("temp/13_personDepartureSummary_SC10.csv");
        java.io.FileWriter ppDepartureSummary = new java.io.FileWriter(PersonDeparture);
        BufferedWriter ppDepartureWriter = new BufferedWriter(ppDepartureSummary);
        ppDepartureWriter.write("PersonId, DeptLinkId, DeptTime");
        ppDepartureWriter.newLine();
        for(int i=0; i<handler2.PPDeparturePPId.size(); i++)
        {
            ppDepartureWriter.write(handler2.PPDeparturePPId.get(i) +","+
                    handler2.PPDepartureLinkId.get(i) +","+
                    handler2.PPDepartureTime.get(i));
            ppDepartureWriter.newLine();
        }
        ppDepartureWriter.close();
        ppDepartureSummary.close();

   }



}
