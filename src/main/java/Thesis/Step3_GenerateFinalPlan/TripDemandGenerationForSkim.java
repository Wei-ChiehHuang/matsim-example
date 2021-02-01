package Thesis.Step3_GenerateFinalPlan;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.network.*;
import org.matsim.api.core.v01.population.*;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.scenario.ScenarioUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TripDemandGenerationForSkim {

    private static final String NETWORKFILE = "input/mergedNetwork2018.xml";      //Network for traffic assignment
    private static final String PLANSFILEOUTPUT = "temp/newPlans.xml";    //The output file of demand generation


    private Scenario scenario;

    public List<String> PersonId = new ArrayList<>();
    public List<String> HomeLink = new ArrayList<>();
    public List<String> HomeEndTime = new ArrayList<>();
    public List<String> Activity = new ArrayList<>();
    public List<String> ActivityLink = new ArrayList<>();
    public List<String> ActivityEndTime = new ArrayList<>();
    public List<String> InterruptionLink = new ArrayList<>();
    public List<String> InterruptionTime = new ArrayList<>();


    public static void main(String[] args) {
        TripDemandGenerationForSkim stdg = new TripDemandGenerationForSkim();
        stdg.run();
    }

    TripDemandGenerationForSkim(){
        this.scenario = ScenarioUtils.createScenario(ConfigUtils.createConfig());
    }

    private void run()  {

        String csvFile = "temp/affectedCommuters.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] commuters = line.split(cvsSplitBy);
                this.PersonId.add(commuters[0]);
                this.HomeLink.add(commuters[1]);
                this.HomeEndTime.add(commuters[2]);
                this.Activity.add(commuters[3]);
                this.ActivityLink.add(commuters[4]);
                this.ActivityEndTime.add(commuters[5]);
                this.InterruptionLink.add(commuters[6]);
                this.InterruptionTime.add(commuters[7]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i=1; i < PersonId.size(); i++){
            double delay = 10.0*60.0;
            createPerson(PersonId.get(i), HomeLink.get(i), HomeEndTime.get(i), 1, InterruptionLink.get(i),  InterruptionTime.get(i), delay, Activity.get(i), ActivityLink.get(i));
        }

        //Write the population file to specified folder
        PopulationWriter pw = new PopulationWriter(scenario.getPopulation(),scenario.getNetwork());
        pw.write(PLANSFILEOUTPUT);
    }

    private void createPerson(String i, String hLink, String homeEndTime, int disruption, String disLink, String interruptionTime, Double waitTime, String act, String aLink){

        Id<Person> personId = Id.createPersonId(i);
        Id<Link> homeLink = Id.createLinkId(hLink);
        Id<Link> disruptionLink = Id.createLinkId(disLink);
        Id<Link> actLink = Id.createLinkId(aLink);

        Person person = scenario.getPopulation().getFactory().createPerson(personId);

        Plan plan = scenario.getPopulation().getFactory().createPlan();

        Activity activity1 = scenario.getPopulation().getFactory().createActivityFromLinkId("home", homeLink);
        activity1.setEndTime(Double.valueOf(homeEndTime));
        plan.addActivity(activity1);

        Leg leg1 = scenario.getPopulation().getFactory().createLeg("pt");
        plan.addLeg(leg1);

        if(disruption == 1 ){
            Activity activity2 = scenario.getPopulation().getFactory().createActivityFromLinkId("disruption", disruptionLink);
            activity2.setEndTime(Double.valueOf(interruptionTime)+waitTime);
            plan.addActivity(activity2);

            Leg leg2 = scenario.getPopulation().getFactory().createLeg("pt");
            plan.addLeg(leg2);
        }

        Activity activity3 = scenario.getPopulation().getFactory().createActivityFromLinkId(act, actLink);
        plan.addActivity(activity3);

        person.addPlan(plan);
        scenario.getPopulation().addPerson(person);
    }

}

