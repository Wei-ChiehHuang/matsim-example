package RandomDemandGeneration;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;
import org.matsim.core.config.groups.PlansCalcRouteConfigGroup;
import org.matsim.core.config.groups.StrategyConfigGroup;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;
import org.matsim.core.replanning.strategies.DefaultPlanStrategiesModule;
import org.matsim.core.scenario.ScenarioUtils;

public class RunMatsimStuttgart {
    public static void main(String[] args) {
        Config config = ConfigUtils.createConfig();
        config.controler().setLastIteration(30);
        config.controler().setOutputDirectory("OutputStuttgart");
        config.controler().setOverwriteFileSetting( OverwriteFileSetting.deleteDirectoryIfExists );
        config.network().setInputFile("input/network.xml");
        config.plans().setInputFile("input/plans.xml");
        config.qsim().setFlowCapFactor(0.1);
        config.qsim().setStorageCapFactor(0.1);

        PlanCalcScoreConfigGroup.ActivityParams home = new PlanCalcScoreConfigGroup.ActivityParams("home");
        home.setTypicalDuration(14 * 60 * 60);
        config.planCalcScore().addActivityParams(home);
        PlanCalcScoreConfigGroup.ActivityParams work = new PlanCalcScoreConfigGroup.ActivityParams("work");
        work.setTypicalDuration(8 * 60 * 60);
        config.planCalcScore().addActivityParams(work);

        PlansCalcRouteConfigGroup.ModeRoutingParams pt = new PlansCalcRouteConfigGroup.ModeRoutingParams("pt");
        pt.setBeelineDistanceFactor(1.3);
        pt.setTeleportedModeSpeed(11.11);//GIVEN THE AVERAGE TRAVEL SPEED IS 40KM/H
        // pt.setTeleportedModeFreespeedFactor();
        config.plansCalcRoute().addModeRoutingParams(pt);

        // define strategies:
        {
            StrategyConfigGroup.StrategySettings strat = new StrategyConfigGroup.StrategySettings();
            strat.setStrategyName(DefaultPlanStrategiesModule.DefaultStrategy.ReRoute.toString());
            strat.setWeight(0.15);
            config.strategy().addStrategySettings(strat);
        }
        {
            StrategyConfigGroup.StrategySettings strat = new StrategyConfigGroup.StrategySettings();
            strat.setStrategyName(DefaultPlanStrategiesModule.DefaultSelector.ChangeExpBeta.toString());
            strat.setWeight(0.9);

            config.strategy().addStrategySettings(strat);
        }
        {
            StrategyConfigGroup.StrategySettings strat = new StrategyConfigGroup.StrategySettings();
            strat.setStrategyName(DefaultPlanStrategiesModule.DefaultStrategy.ChangeTripMode.toString());
            strat.setWeight(0.25);
            config.strategy().addStrategySettings(strat);
        }
        config.strategy().setFractionOfIterationsToDisableInnovation(0.9);

        config.vspExperimental().setWritingOutputEvents(true);

        Scenario scenario = ScenarioUtils.loadScenario(config) ;


        Controler controler = new Controler( scenario ) ;
        controler.run();

    }
}