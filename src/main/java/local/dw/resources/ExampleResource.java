package local.dw.resources;

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.caching.CacheControl;
import io.dropwizard.jersey.params.DateTimeParam;
import local.dw.api.Saying;
import local.dw.core.Template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class ExampleResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleResource.class);

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

	/*
	 * public ExampleResource(final Template template) { this.template =
	 * template.toString(); this.defaultName = "No none"; this.counter = new
	 * AtomicLong(); }
	 */
    public ExampleResource(final String template, final String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
	}

	@GET
    @Timed(name = "get-requests-timed")
//    @Metered(name = "get-requests-metered")
//    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        //return new Saying(counter.incrementAndGet(), template.render(name));
		final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }

	/*
	 * @POST public void receiveHello(Saying saying) {
	 * LOGGER.info("Received a saying: {}", saying); }
	 * 
	 * @GET
	 * 
	 * @Path("/date")
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public String receiveDate(@QueryParam("date")
	 * Optional<DateTimeParam> dateTimeParam) { if (dateTimeParam.isPresent()) {
	 * final DateTimeParam actualDateTimeParam = dateTimeParam.get();
	 * LOGGER.info("Received a date: {}", actualDateTimeParam); return
	 * actualDateTimeParam.get().toString(); } else {
	 * LOGGER.warn("No received date"); return null; } }
	 */
}
