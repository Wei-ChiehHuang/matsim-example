package Thesis.Step0_Preparation;

import com.google.common.collect.Sets;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;
import org.matsim.core.config.groups.PlansCalcRouteConfigGroup;
import org.matsim.core.config.groups.QSimConfigGroup;
import org.matsim.core.config.groups.StrategyConfigGroup;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;
import org.matsim.core.replanning.strategies.DefaultPlanStrategiesModule;
import org.matsim.core.scenario.ScenarioUtils;

import java.util.Collections;

public class RunInitialAssignment {

    public static void main(String[] args) {
        Config config = ConfigUtils.createConfig();
        config.controler().setLastIteration(10);
        config.controler().setMobsim("qsim");
        config.controler().setWritePlansInterval(config.controler().getLastIteration());
        config.controler().setWriteEventsInterval(config.controler().getLastIteration());
        config.controler().setOutputDirectory("OutputMunich_Johann");
        config.controler().setOverwriteFileSetting( OverwriteFileSetting.deleteDirectoryIfExists );

        config.qsim().setEndTime(24*3600);
        config.qsim().setTrafficDynamics(QSimConfigGroup.TrafficDynamics.withHoles);
        //config.qsim().setFlowCapFactor(0.1);
        //config.qsim().setStorageCapFactor(0.1);
        config.qsim().setStuckTime(10);
        config.qsim().setNumberOfThreads(16);
        config.global().setNumberOfThreads(16);
        config.parallelEventHandling().setNumberOfThreads(16);
        config.qsim().setUsingThreadpool(false);

        config.network().setInputFile("input/mergedNetwork2018.xml");
        config.plans().setInputFile("input/plans_2011_onlyAuto_inMUC.xml");
        config.transit().setUseTransit(true);
        config.transit().setTransitScheduleFile("input/schedule2018.xml");
        config.transit().setVehiclesFile("input/vehicles2018.xml");
        config.transit().setTransitModes(Sets.newHashSet("pt"));
        //config.vspExperimental().setWritingOutputEvents(true);


        PlanCalcScoreConfigGroup.ActivityParams home = new PlanCalcScoreConfigGroup.ActivityParams("home");
        home.setTypicalDuration(12 * 60 * 60);
        config.planCalcScore().addActivityParams(home);

        PlanCalcScoreConfigGroup.ActivityParams work = new PlanCalcScoreConfigGroup.ActivityParams("work");
        work.setTypicalDuration(8 * 60 * 60);
        config.planCalcScore().addActivityParams(work);

        PlanCalcScoreConfigGroup.ActivityParams education = new PlanCalcScoreConfigGroup.ActivityParams("education");
        education.setTypicalDuration(8 * 60 * 60);
        config.planCalcScore().addActivityParams(education);

        PlanCalcScoreConfigGroup.ActivityParams shopping = new PlanCalcScoreConfigGroup.ActivityParams("shopping");
        shopping.setTypicalDuration(1 * 60 * 60);
        config.planCalcScore().addActivityParams(shopping);

        PlanCalcScoreConfigGroup.ActivityParams other = new PlanCalcScoreConfigGroup.ActivityParams("other");
        other.setTypicalDuration(1 * 60 * 60);
        config.planCalcScore().addActivityParams(other);

        PlanCalcScoreConfigGroup.ActivityParams airport = new PlanCalcScoreConfigGroup.ActivityParams("airport");
        airport.setTypicalDuration(8 * 60 * 60);
        config.planCalcScore().addActivityParams(airport);


//        PlansCalcRouteConfigGroup.ModeRoutingParams car = new PlansCalcRouteConfigGroup.ModeRoutingParams("car");
//        car.setTeleportedModeFreespeedFactor(1.0);
//        config.plansCalcRoute().addModeRoutingParams(car);

//        PlansCalcRouteConfigGroup.ModeRoutingParams pt = new PlansCalcRouteConfigGroup.ModeRoutingParams("pt");
//        pt.setTeleportedModeFreespeedFactor(1.0);
//        config.plansCalcRoute().addModeRoutingParams(pt);

        PlansCalcRouteConfigGroup.ModeRoutingParams bike = new PlansCalcRouteConfigGroup.ModeRoutingParams("bike");
        bike.setBeelineDistanceFactor(2.0);
        bike.setTeleportedModeSpeed(12 / 3.6);
        config.plansCalcRoute().addModeRoutingParams(bike);

        PlansCalcRouteConfigGroup.ModeRoutingParams walk = new PlansCalcRouteConfigGroup.ModeRoutingParams("walk");
        walk.setBeelineDistanceFactor(2.0);
        walk.setTeleportedModeSpeed(4 / 3.6);
        config.plansCalcRoute().addModeRoutingParams(walk);


        // define strategies:
        {
            StrategyConfigGroup.StrategySettings strat = new StrategyConfigGroup.StrategySettings();
            strat.setStrategyName(DefaultPlanStrategiesModule.DefaultStrategy.ReRoute.toString());
            strat.setWeight(0.5);
            config.strategy().addStrategySettings(strat);
        }
        {
            StrategyConfigGroup.StrategySettings strat = new StrategyConfigGroup.StrategySettings();
            strat.setStrategyName(DefaultPlanStrategiesModule.DefaultSelector.ChangeExpBeta.toString());
            strat.setWeight(0.1);
            config.strategy().addStrategySettings(strat);
        }

        config.strategy().setFractionOfIterationsToDisableInnovation(0.8);
        config.strategy().setMaxAgentPlanMemorySize(4);


        Scenario scenario = ScenarioUtils.loadScenario(config) ;

        Controler controler = new Controler( scenario ) ;
        controler.run();


    }

}
