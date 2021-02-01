package RandomDemandGeneration;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.pt.transitSchedule.TransitScheduleReaderV1;
import org.matsim.pt.transitSchedule.api.*;
import org.matsim.vehicles.*;


public class TransitScheduling {

    private final static int HEADWAY = 20 * 60; //given the headway of S-Bahn is 20 min/hr
    private final static int START_TIME = 6 * 60 * 60; //given the S-Bahn starts at 6am daily
    private final static int END_TIME = 23 * 60 * 60; //given the S-Bahn ends at 11pm every day
    private final static String FILE_NAME = "input/schedule_bus.xml";
    private final static String OUTPUT_SCHEDULE = "input/adjustedSchedule.xml";
    private final static String OUTPUT_VEHICLES = "input/vehicles.xml";


    public static void main(String[] args) {

        //Initialize the scenario and read in the transit schedule file created by JOSM
        Scenario scenario = ScenarioUtils.createScenario(ConfigUtils.createConfig());
        TransitScheduleReaderV1 reader = new TransitScheduleReaderV1(scenario);
        reader.readFile(FILE_NAME);

        //Initialize the vehicles
        //Create vehicles container
        Vehicles vehicles = VehicleUtils.createVehiclesContainer();
        //Create factory
        VehiclesFactory vehiclesFactory = vehicles.getFactory();
        //Create vehicle type, which is SBahn
        VehicleType type = vehiclesFactory.createVehicleType(Id.create("Bus", VehicleType.class));
        //Create the capacity of this vehicle type
        VehicleCapacity capacity = vehiclesFactory.createVehicleCapacity();
        //Set the standing and seating capacity
        capacity.setSeats(1200);
        capacity.setStandingRoom(1300);
        //Set up the capacity
        type.setCapacity(capacity);
        //Add the vehicle type to the vehicle container
        vehicles.addVehicleType(type);


        //Initialize the schedule
        //Create factory
        TransitScheduleFactory transitScheduleFactory = scenario.getTransitSchedule().getFactory();
        //Get the ID of master route
        TransitLine line = scenario.getTransitSchedule().getTransitLines().get(Id.create("100", TransitLine.class));
        //Get the routes by ID
        TransitRoute routeBus100_1 = line.getRoutes().get(Id.create("100_1", TransitRoute.class));
        //TransitRoute routeS2_2 = line.getRoutes().get(Id.create("S2_Erding_Petershausen", TransitRoute.class));

        createDeparturesAndvehiclesForRoute(vehicles, type, transitScheduleFactory, routeBus100_1);
        //createDeparturesAndvehiclesForRoute(vehicles, type, transitScheduleFactory, routeS2_2);


        //Write out the vehicles and schedule files
        new VehicleWriterV1(vehicles).writeFile(OUTPUT_VEHICLES);
        new TransitScheduleWriter(scenario.getTransitSchedule()).writeFile(OUTPUT_SCHEDULE);
    }

    private static void createDeparturesAndvehiclesForRoute(Vehicles vehicles, VehicleType type,
                                                            TransitScheduleFactory transitScheduleFactory, TransitRoute route) {
        //the variable, id, is created to record the sequence number of vehicle journey
        int id = 0;
        for(double time = START_TIME; time < END_TIME; time += HEADWAY) {
            //departureId is used to denote the vehicle journey; ex. S2_1_0, S2_1_1......
            String departureId = route.getId() + "_" + id;
            //Create the object, departure, by using factory
            Departure departure = transitScheduleFactory.createDeparture(Id.create(departureId, Departure.class), time);

            //Create the object, vehicles, by using factory
            Vehicle vehicle = vehicles.getFactory().createVehicle(Id.createVehicleId(departureId), type);
            //Add vehicle to vehicles
            vehicles.addVehicle(vehicle);
            //Add the vehicle ID in departure
            departure.setVehicleId(vehicle.getId());
            //Add departure into the transit schedule file
            route.addDeparture(departure);
            id++;
        }
    }
}