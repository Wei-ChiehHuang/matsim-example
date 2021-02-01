package RandomDemandGeneration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.population.Activity;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.api.core.v01.population.PopulationWriter;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.gbl.MatsimRandom;
import org.matsim.core.network.io.MatsimNetworkReader;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.geometry.CoordinateTransformation;
import org.matsim.core.utils.geometry.geotools.MGC;
import org.matsim.core.utils.geometry.transformations.TransformationFactory;
import org.matsim.core.utils.gis.ShapeFileReader;
import org.opengis.feature.simple.SimpleFeature;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
public class RandomDemandGenerationStuttgart {
    //Specify all input files
    private static final String NETWORKFILE = "input/network.xml";      //Network for traffic assignment
    private static final String COUNTIES = "input/StuttgartRegion.shp";            //Polygon shapefile for demand generation
    private static final String PLANSFILEOUTPUT = "input/plans.xml";    //The output file of demand generation

    private Scenario scenario;

    //Map of shape geometries mapped to their id
    private Map<String,Geometry> shapeMap;

    //Due to the huge number of commuters, it's recommended to use a sample of the population.
    //+ This scale factor should be the same as the flow capacity factor. Try different value to evaluate the performance.
    private static double VEHICLE_OCCUPANCY = 1.3;

    private static double SCALEFACTOR = 0.1 / VEHICLE_OCCUPANCY;

    private static double MODALSPLIT = 0.78;

    //Define the coordinate transformation function
    private final CoordinateTransformation transformation =
            TransformationFactory.getCoordinateTransformation
                    (TransformationFactory.WGS84, TransformationFactory.DHDN_GK4);


    public static void main(String[] args) {
        RandomDemandGenerationStuttgart grde = new RandomDemandGenerationStuttgart();
        grde.run();
    }

    //A constructor for this class, which is to set up the scenario container.
    RandomDemandGenerationStuttgart (){
        this.scenario = ScenarioUtils.createScenario(ConfigUtils.createConfig());
        new MatsimNetworkReader(scenario.getNetwork()).readFile(NETWORKFILE);
    }
    //Generate randomly sampling demand
    private void run() {
        this.shapeMap = readShapeFile(COUNTIES, "RS");

        //Specify the number of commuters and the modal split of this relation
        String outerName = "Esslingen";
        String innerName = "Stuttgart";
        String outerCode = "08116";
        String innerCode = "08111";
        //Create commuters for this pair
        CreateOD(outerCode, innerCode, 46053 * SCALEFACTOR, 20165 * SCALEFACTOR, MODALSPLIT, outerName, innerName);

        //Specify the number of commuters and the modal split of this relation
        outerName = "Ludwigsburg";
        outerCode = "08118";
        //Create commuters for this pair
        CreateOD(outerCode, innerCode, 50705 * SCALEFACTOR, 17284 * SCALEFACTOR, MODALSPLIT, outerName, innerName);

        //Specify the number of commuters and the modal split of this relation
        outerName = "Boeblingen";
        outerCode = "08115";
        //Create commuters for this pair
        CreateOD(outerCode, innerCode, 29188 * SCALEFACTOR, 14869 * SCALEFACTOR, MODALSPLIT, outerName, innerName);

        //Specify the number of commuters and the modal split of this relation
        outerName = "RemsMurKreis";
        outerCode = "08119";
        //Create commuters for this pair
        CreateOD(outerCode, innerCode, 36879 * SCALEFACTOR, 10734 * SCALEFACTOR, MODALSPLIT, outerName, innerName);

        //Specify the number of commuters and the modal split of this relation
        outerName = "Reutlingen";
        outerCode = "08415";
        //Create commuters for this pair
        CreateOD(outerCode, innerCode, 4957 * SCALEFACTOR, 1943 * SCALEFACTOR, MODALSPLIT, outerName, innerName);

        //Specify the number of commuters and the modal split of this relation
        outerName = "Goeppingen";
        outerCode = "08117";
        //Create commuters for this pair
        CreateOD(outerCode, innerCode, 7355 * SCALEFACTOR, 1323 * SCALEFACTOR, MODALSPLIT, outerName, innerName);

        //Specify the number of commuters and the modal split of this relation
        outerName = "Heilbronn";
        outerCode = "08121";
        //Create commuters for this pair
        CreateOD(outerCode, innerCode, 3822 * SCALEFACTOR, 1315 * SCALEFACTOR, MODALSPLIT, outerName, innerName);

        //Specify the number of commuters and the modal split of this relation
        outerName = "Tuebingen";
        outerCode = "08416";
        //Create commuters for this pair
        CreateOD(outerCode, innerCode, 4448 * SCALEFACTOR, 1200 * SCALEFACTOR, MODALSPLIT, outerName, innerName);

        //Specify the number of commuters and the modal split of this relation
        outerName = "Karlsruhe";
        outerCode = "08212";
        //Create commuters for this pair
        CreateOD(outerCode, innerCode, 0.0, 897 * SCALEFACTOR, MODALSPLIT, outerName, innerName);

        //Specify the number of commuters and the modal split of this relation
        outerName = "Ostalbkreis";
        outerCode = "08136";
        //Create commuters for this pair
        CreateOD(outerCode, innerCode, 3480 * SCALEFACTOR, 764 * SCALEFACTOR, MODALSPLIT, outerName, innerName);

        //Specify the number of commuters and the modal split of this relation
        outerName = "EnzKreis";
        outerCode = "08236";
        //Create commuters for this pair
        CreateOD(outerCode, innerCode, 4038 * SCALEFACTOR, 0.0, MODALSPLIT, outerName, innerName);

        //Specify the number of commuters inside the inner one
        //Create commuters for this pair
        CreateOD(innerCode, innerCode, 0.0, 160675 * SCALEFACTOR, MODALSPLIT, innerName, innerName);

        //Write the population file to specified folder
        PopulationWriter pw = new PopulationWriter(scenario.getPopulation(),scenario.getNetwork());
        pw.write(PLANSFILEOUTPUT);
    }

    private void CreateOD(String outerZoneCode, String innerZoneCode, double flowIn, double flowOut, double modalSplit, String outerZoneName, String innerZoneName) {
        //Geometries for the relations
        Geometry outerDistrict = this.shapeMap.get(outerZoneCode);
        Geometry innerDistrict = this.shapeMap.get(innerZoneCode);
        //Create commuters for this pair
        if (flowIn > 0) {
            createCommutersForPair(flowIn, modalSplit * flowIn, outerDistrict, innerDistrict, outerZoneName + "-" + innerZoneName);
        }
        if (flowOut > 0) {
            createCommutersForPair(flowOut, modalSplit * flowOut, innerDistrict, outerDistrict, innerZoneName + "-" + outerZoneName);
        }
    }

    //Function to create commuters for a pair
    public void createCommutersForPair(double commuters, double carcommuters, Geometry home, Geometry work, String toFromPrefix) {
        //Randomly create home and work location of each commuter
        for (int i = 0; i<=commuters;i++){
            //Specify the transportation mode
            String mode = "car";
            if (i>carcommuters) {
                mode = "pt";
            }

            //Specify the home location randomly and transform the coordinate
            Coord homec = drawRandomPointFromGeometry(home);
            homec = transformation.transform(homec);

            //Specify the working location randomly and transform the coordinate
            Coord workc = drawRandomPointFromGeometry(work);
            workc = transformation.transform(workc);
            //Create plan for each commuter
            createOnePerson(i, homec, workc, mode, toFromPrefix);

        }
    }


    //Read in shapefile


    public Map<String,Geometry> readShapeFile(String filename, String attrString){
        Map<String,Geometry> shapeMap = new HashMap<String, Geometry>();
        for (SimpleFeature ft : ShapeFileReader.getAllFeatures(filename)) {
            GeometryFactory geometryFactory= new GeometryFactory();
            WKTReader wktReader = new WKTReader(geometryFactory);
            Geometry geometry;
            try {
                geometry = wktReader.read((ft.getAttribute("the_geom")).toString());
                shapeMap.put(ft.getAttribute(attrString).toString(),geometry);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return shapeMap;
    }


    //Create random coordinates within a given polygon
    private  Coord drawRandomPointFromGeometry(Geometry g) {
        Random rnd = MatsimRandom.getLocalInstance();
        Point p;
        double x, y;
        do {
            x = g.getEnvelopeInternal().getMinX() +  rnd.nextDouble() * (g.getEnvelopeInternal().getMaxX() - g.getEnvelopeInternal().getMinX());
            y = g.getEnvelopeInternal().getMinY() + rnd.nextDouble() * (g.getEnvelopeInternal().getMaxY() - g.getEnvelopeInternal().getMinY());
            p = MGC.xy2Point(x, y);
        } while (!g.contains(p));
        Coord coord = new Coord(p.getX(), p.getY());
        return coord;
    }
    //Create plan for each commuters
    private void createOnePerson(int i, Coord coord, Coord coordWork, String mode, String toFromPrefix) {

        double minuteOffset = Math.random()*120;

        Id<Person> personId = Id.createPersonId(toFromPrefix+i);
        Person person = scenario.getPopulation().getFactory().createPerson(personId);

        Plan plan = scenario.getPopulation().getFactory().createPlan();

        //Create Activity 1 + Leg1
        //create a home activity from coordinate "scenario.getPopulation().getFactory().createActivityFromCoord("home", coord)"
        Activity activity1 = scenario.getPopulation().getFactory().createActivityFromCoord("home", coord);
        activity1.setEndTime((8 * 60 + minuteOffset)*60);
        plan.addActivity(activity1);

        //add a leg with mode "scenario.getPopulation().getFactory().createLeg(mode)"
        Leg leg1 = scenario.getPopulation().getFactory().createLeg(mode);
        plan.addLeg(leg1);

        //Create Activity 2 + Leg2
        //create a work activity from coordinate "scenario.getPopulation().getFactory().createActivityFromCoord("home", coord)"
        Activity activity2 = scenario.getPopulation().getFactory().createActivityFromCoord("work", coordWork);
        activity2.setEndTime((16 * 60 + minuteOffset)*60);
        plan.addActivity(activity2);

        //add a leg with mode "scenario.getPopulation().getFactory().createLeg(mode)"
        Leg leg2 = scenario.getPopulation().getFactory().createLeg(mode);
        plan.addLeg(leg2);

        //Create Activity 3
        //create a work activity from coordinate "scenario.getPopulation().getFactory().createActivityFromCoord("home", coord)"
        Activity activity3 = scenario.getPopulation().getFactory().createActivityFromCoord("home", coord);
        activity3.setEndTime((8 * 60 + minuteOffset)*60);
        plan.addActivity(activity3);

        person.addPlan(plan);
        scenario.getPopulation().addPerson(person);
    }



}
