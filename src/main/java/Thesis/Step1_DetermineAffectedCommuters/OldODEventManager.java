package Thesis.Step1_DetermineAffectedCommuters;

import Thesis.Step1_DetermineAffectedCommuters.OldODEventHandler;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsReaderXMLv1;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.io.MatsimNetworkReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OldODEventManager {

    public static void main(String[] args) throws IOException {
        EventsManager events = EventsUtils.createEventsManager();

        Network network = NetworkUtils.createNetwork();
        MatsimNetworkReader networkReader = new MatsimNetworkReader(network);
        networkReader.readFile("OutputMunich_initial/output_network.xml.gz");

        OldODEventHandler handler1 = new OldODEventHandler(network);
        events.addHandler(handler1);

        EventsReaderXMLv1 eventsReader = new EventsReaderXMLv1(events);
        eventsReader.readFile("OutputMunich_initial/output_events.xml.gz");

        List<String> affectedPerson = new ArrayList<>();
        handler1.homeId.retainAll(handler1.workId);
        affectedPerson.addAll(handler1.homeId);
        System.out.println(affectedPerson.size());


        File affectedPersonSummary = new File("temp/00_commutersPT.csv");
        java.io.FileWriter aPersonSummary = new java.io.FileWriter(affectedPersonSummary);
        BufferedWriter aPersonWriter = new BufferedWriter(aPersonSummary);
        aPersonWriter.write("PersonId");
        aPersonWriter.newLine();
        for(int i=0; i<affectedPerson.size(); i++)
        {
            aPersonWriter.write(affectedPerson.get(i));
            aPersonWriter.newLine();
        }
        aPersonWriter.close();
        aPersonWriter.close();



        File actSummary = new File("temp/01_actSummary.csv");
        java.io.FileWriter HBWHBESummary = new java.io.FileWriter(actSummary);
        BufferedWriter actWrite = new BufferedWriter(HBWHBESummary);

        actWrite.write("PersonId, ActTyp, Link, EndTime");
        actWrite.newLine();
        for(int j=0; j<handler1.PPList.size(); j++)
        {
            //handler1.workList.set(i, handler1.workList.);
            actWrite.write(handler1.PPList.get(j) + "," +
                    handler1.ActList.get(j) +","+
                    handler1.LinkList.get(j) +","+
                    handler1.EndTimeList.get(j));
            actWrite.newLine();
        }
        actWrite.close();
        actWrite.close();
    }
}




