package Thesis.Step2_SkimCalculation;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.*;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.scenario.ScenarioUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccessTripGeneratorForSkim {

    private static final String NETWORKFILE = "input/mergedNetwork2018.xml";      //Network for traffic assignment
    private static final String PLANSFILEOUTPUT = "skim/accessPlans_5_SC20.xml";    //The output file of demand generation


    private Scenario scenario;

    public List<String> PersonId = new ArrayList<>();
    public List<String> HomeLink = new ArrayList<>();
    public List<String> HomeXCoord = new ArrayList<>();
    public List<String> HomeYCoord = new ArrayList<>();
    public List<String> HomeEndTime = new ArrayList<>();
    public List<String> Activity = new ArrayList<>();
    public List<String> ActivityLink = new ArrayList<>();
    public List<String> ActivityXCoord = new ArrayList<>();
    public List<String> ActivityYCoord = new ArrayList<>();
    public List<String> ActivityEndTime = new ArrayList<>();
    public List<String> InterruptionLink = new ArrayList<>();
    public List<String> InterruptionXCoord = new ArrayList<>();
    public List<String> InterruptionYCoord = new ArrayList<>();
    public List<String> InterruptionTime = new ArrayList<>();


    public static void main(String[] args) {
        AccessTripGeneratorForSkim accessPlan = new AccessTripGeneratorForSkim();
        accessPlan.run();
    }

    AccessTripGeneratorForSkim(){
        this.scenario = ScenarioUtils.createScenario(ConfigUtils.createConfig());
    }

    private void run()  {

        String csvFile = "skim/forAnalysis/affectedCommutersSkim_5_sc20.csv";
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
                this.HomeXCoord.add(commuters[3]);
                this.HomeYCoord.add(commuters[4]);
                this.Activity.add(commuters[5]);
                this.ActivityLink.add(commuters[6]);
                this.ActivityEndTime.add(commuters[7]);
                this.ActivityXCoord.add(commuters[8]);
                this.ActivityYCoord.add(commuters[9]);
                this.InterruptionLink.add(commuters[11]);
                this.InterruptionXCoord.add(commuters[13]);
                this.InterruptionYCoord.add(commuters[14]);
                this.InterruptionTime.add(commuters[15]);
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
            Coord coordHome = new Coord(Double.valueOf(HomeXCoord.get(i)), Double.valueOf(HomeYCoord.get(i)));
            Coord coordActivity = new Coord(Double.valueOf(ActivityXCoord.get(i)), Double.valueOf(ActivityYCoord.get(i)));
            Coord coordInterruption = new Coord(Double.valueOf(InterruptionXCoord.get(i)), Double.valueOf(InterruptionYCoord.get(i)));
            createPerson(PersonId.get(i), coordHome, HomeEndTime.get(i), 1, coordInterruption,  InterruptionTime.get(i), delay, Activity.get(i), coordActivity);
        }

        //Write the population file to specified folder
        PopulationWriter pw = new PopulationWriter(scenario.getPopulation(),scenario.getNetwork());
        pw.write(PLANSFILEOUTPUT);
    }

    private void createPerson(String i, Coord hLink, String homeEndTime, int disruption, Coord disLink, String interruptionTime, Double waitTime, String act, Coord aLink){

        Id<Person> personId = Id.createPersonId(i);

        Person person = scenario.getPopulation().getFactory().createPerson(personId);

        Plan plan = scenario.getPopulation().getFactory().createPlan();

        org.matsim.api.core.v01.population.Activity activity1 = scenario.getPopulation().getFactory().createActivityFromCoord("disruption", disLink);
        activity1.setEndTime(Double.valueOf(interruptionTime));
        plan.addActivity(activity1);

        Leg leg1 = scenario.getPopulation().getFactory().createLeg("pt");
        plan.addLeg(leg1);

        Activity activity2 = scenario.getPopulation().getFactory().createActivityFromCoord("home", hLink);
        plan.addActivity(activity2);

        person.addPlan(plan);
        scenario.getPopulation().addPerson(person);
    }
}
