package local.dw.health;

import com.codahale.metrics.health.HealthCheck;

public class TemplateHealthCheck extends HealthCheck {
    private final String template;

	/*
	 * public TemplateHealthCheck(Template template) { this.template = template; }
	 */
    public TemplateHealthCheck(final String template) {
        this.template = template;
    }

	/*
	 * @Override protected Result check() throws Exception {
	 * template.render(Optional.of("woo")); template.render(Optional.empty());
	 * return Result.healthy(); }
	 */
    
    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
        	return Result.unhealthy("Template does not parse name");
        }        
        return Result.healthy();
    }
}
