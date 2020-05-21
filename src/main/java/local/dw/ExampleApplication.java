package local.dw;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import local.dw.health.TemplateHealthCheck;
import local.dw.resources.ExampleResource;

public class ExampleApplication extends Application<ExampleConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ExampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "Example";
    }

    @Override
    public void initialize(final Bootstrap<ExampleConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ExampleConfiguration configuration, final Environment environment) {
		
    	final ExampleResource resource = new ExampleResource(
    			configuration.getTemplate(), 
    			configuration.getDefaultName() 
    	);
    	final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
    	environment.healthChecks().register("template",  healthCheck);
    	environment.jersey().register(resource);
    }

}
