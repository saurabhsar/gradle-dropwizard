package saurabh.araiyer.test.resources;

import com.codahale.metrics.annotation.Timed;
import saurabh.araiyer.test.AppConfiguration;
import com.google.inject.Inject;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {

  @Inject
  public SampleResource(AppConfiguration appConfiguration) {

  }

  @GET
  @Timed
  public Response sayHello(@QueryParam("name") Optional<String> name) {
    final String value = String.format("Sample Response");
    return Response.ok(value).build();
  }
}
