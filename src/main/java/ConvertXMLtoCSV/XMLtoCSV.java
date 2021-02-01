package ConvertXMLtoCSV;


import com.pb.common.datafile.CSVFileWriter;
import org.apache.commons.math3.genetics.Population;
import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.PopulationWriter;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.population.io.PopulationReader;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.households.Household;

import org.matsim.households.HouseholdsReaderV10;
import org.matsim.utils.objectattributes.ObjectAttributes;
import org.matsim.utils.objectattributes.ObjectAttributesXmlReader;
import org.matsim.utils.objectattributes.attributeconverters.CoordConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;

public class XMLtoCSV {
    public static void main (String[] args) throws FileNotFoundException {
        readPop("./src/main/java/ConvertXMLtoCSV/pp_002.xml", "./src/main/java/ConvertXMLtoCSV/pp_002.xml");
    }

/*    public static void readHh(String inputHouseholdFileName, String inputHouseholdAttributesFileName) throws FileNotFoundException {

        Scenario scenario = ScenarioUtils.createScenario(ConfigUtils.createConfig());
        HouseholdsReaderV10 householdsReader = new HouseholdsReaderV10(scenario.getHouseholds());
        householdsReader.readFile(inputHouseholdFileName);

        ObjectAttributes householdAttributes = new ObjectAttributes();
        ObjectAttributesXmlReader householdAttributesReader = new ObjectAttributesXmlReader(householdAttributes);
        householdAttributesReader.putAttributeConverter(Coord.class, new CoordConverter());
        householdAttributesReader.readFile(inputHouseholdAttributesFileName);

        Iterator<Household> it = scenario.getHouseholds().getHouseholds().values().iterator();

        int size = scenario.getHouseholds().getHouseholds().size();
        System.out.println(size);

        while (it.hasNext()) {
            Household household = it.next();
            Map<String, Object> atts = (Map<String, Object>) householdAttributes.getAttribute(household.getId().toString(), "null");
            System.out.println(atts.get("homeCoorWGS84").toString());
        }
    }*/


    public static void readPop(String inputHouseholdFileName, String inputHouseholdAttributesFileName) throws FileNotFoundException {

        Scenario scenario = ScenarioUtils.createScenario(ConfigUtils.createConfig());
        PopulationReader populationReader = new PopulationReader(scenario);
        populationReader.readFile(inputHouseholdFileName);

        ObjectAttributes populationAttributes = new ObjectAttributes();
        ObjectAttributesXmlReader populationAttributeReader = new ObjectAttributesXmlReader(populationAttributes);
        populationAttributeReader.readFile(inputHouseholdAttributesFileName);

        int size = scenario.getPopulation().getPersons().size();
        System.out.println(scenario.getPopulation().getPersons().toString());
        System.out.println(size);
        System.out.println(scenario.getPopulation().getPersonAttributes());
    }


}
