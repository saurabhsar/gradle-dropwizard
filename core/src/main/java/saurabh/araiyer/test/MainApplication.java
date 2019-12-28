package saurabh.araiyer.test;

import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MainApplication extends Application<AppConfiguration> {

  @Override
  public String getName() {
    return "Test Gradle Dropwizard Inject App";
  }

  @Override
  public void initialize(Bootstrap<AppConfiguration> bootstrap) {
    GuiceBundle<AppConfiguration> guiceBundle = GuiceBundle.<AppConfiguration>newBuilder()
        .addModule(new ManagerModule())
        .setConfigClass(AppConfiguration.class)
        .enableAutoConfig(getClass().getPackage().getName())
        .addModule(new ManagerModule())
        .build(Stage.DEVELOPMENT);
    bootstrap.addBundle(guiceBundle);
  }

  @Override
  public void run(AppConfiguration configuration,
      Environment environment) {
    final TemplateHealthCheck healthCheck =
        new TemplateHealthCheck(configuration.getDefaultName());
    environment.healthChecks().register("healthcheck", healthCheck);
  }


  public static void main(String[] args) throws Exception {
    new MainApplication().run(args);
  }
}
