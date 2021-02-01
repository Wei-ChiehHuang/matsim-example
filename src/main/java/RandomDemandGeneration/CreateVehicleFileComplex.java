package RandomDemandGeneration;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.population.io.PopulationReader;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.vehicles.*;

public class CreateVehicleFileComplex {

    //Create scenario
    Config config = ConfigUtils.createConfig();
    Scenario sc = ScenarioUtils.createScenario(config);

    //Create vehicle containers
    Vehicles vehicles = VehicleUtils.createVehiclesContainer();
    VehicleType type;
    //Generating vehicle type category
    VehicleType.DoorOperationMode mode = VehicleType.DoorOperationMode.serial;


    public static void main(String[] args) {
        CreateVehicleFileComplex CVF = new CreateVehicleFileComplex();
        CVF.runVehicleType();
        CVF.runVehicle();

    }

    private void runVehicleType() {
        createVehicleType("car_average",mode,7.2,1.0,1.0,1.0,"BEGIN_EMISSIONSPASSENGER_CAR;average;average;averageEND_EMISSIONS");
        createVehicleType("car_petrol",mode,6.2,1.0,1.0,1.0,"BEGIN_EMISSIONSPASSENGER_CAR;petrol (4S);&gt;=2L;PC-P-Euro-1END_EMISSIONS");
        createVehicleType("car_diesel",mode,5.2,1.0,1.0,1.0,"BEGIN_EMISSIONSPASSENGER_CAR;diesel;&lt;1,4L;PC-D-Euro-3END_EMISSIONS");
        createVehicleType("truck",mode,4.2,1.0,1.0,1.0,"BEGIN_EMISSIONSHEAVY_GOODS_VEHICLE;average;average;averageEND_EMISSIONS");

    }

    private void createVehicleType(String name, VehicleType.DoorOperationMode mode, double length, double width, double accessTime, double egressTime, String description) {

        Id<VehicleType> typ1 = Id.create(name, VehicleType.class);
        type = VehicleUtils.getFactory().createVehicleType(typ1);
        type.setDoorOperationMode(mode);
        type.setLength(length);
        type.setWidth(width);
        //type.setAccessTime(accessTime);
        //type.setEgressTime(egressTime);
        type.setDescription(description);
        vehicles.addVehicleType(type);
    }

    private void runVehicle(){
        //Read in population file
        new PopulationReader(sc).readFile("C:/Users/Wei/Documents/matsim/contribs/emissions/test/input/org/matsim/contrib/emissions/sample_population_MUC.xml");

        double variance = Math.random()*4;


        for (Person person : sc.getPopulation().getPersons().values()) {
            String id = person.getId().toString();
            Id<Vehicle> vehId = Id.createVehicleId(id);
            Vehicle vehicle = VehicleUtils.getFactory().createVehicle(vehId, type);
            vehicles.addVehicle(vehicle);
        }
        new VehicleWriterV1(vehicles).writeFile("C:/Users/Wei/Documents/matsim/contribs/emissions/test/input/org/matsim/contrib/emissions/vehicle_MUC.xml");
    }
}


