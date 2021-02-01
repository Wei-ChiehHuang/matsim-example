package RandomDemandGeneration;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.population.io.PopulationReader;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.vehicles.*;

public class CreateVehicleFile {

    public static void main(String[] args) {
        //Create scenario
        Config config = ConfigUtils.createConfig();
        Scenario sc = ScenarioUtils.createScenario(config);
        //Read in population file
        new PopulationReader(sc).readFile("C:/Users/Wei/Documents/matsim/contribs/emissions/test/input/org/matsim/contrib/emissions/sample_population_MUC.xml");
        //Create vehicle containers
        Vehicles vehicles = VehicleUtils.createVehiclesContainer();
        VehicleType type;
        //Generating vehicle type category
        VehicleType.DoorOperationMode mode = VehicleType.DoorOperationMode.serial;
        Id<VehicleType> typ1 = Id.create("car_average", VehicleType.class);
        type = VehicleUtils.getFactory().createVehicleType(typ1);
        type.setDoorOperationMode(mode);
        type.setLength(7.2);
        type.setWidth(1.2);
        //type.setAccessTime(1.0);
        //type.setEgressTime(1.0);
        type.setDescription("BEGIN_EMISSIONSPASSENGER_CAR;average;average;averageEND_EMISSIONS");
        vehicles.addVehicleType(type);
        // generating vehicle ID and their type
        for (Person person : sc.getPopulation().getPersons().values()) {
            String id = person.getId().toString();
            Id<Vehicle> vehId = Id.createVehicleId(id);
            Vehicle vehicle = VehicleUtils.getFactory().createVehicle(vehId, type);
            vehicles.addVehicle(vehicle);
        }
        new VehicleWriterV1(vehicles).writeFile("C:/Users/Wei/Documents/matsim/contribs/emissions/test/input/org/matsim/contrib/emissions/vehicle_MUC.xml");
    }






















}
