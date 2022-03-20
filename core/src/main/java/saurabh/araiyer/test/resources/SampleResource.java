package saurabh.araiyer.test.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import saurabh.araiyer.test.managers.QueueService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/restbus")
public class SampleResource {

  private final QueueService queueService;

  @Inject
  public SampleResource(QueueService queueService) {
    this.queueService = queueService;
  }

  @PUT
  @Timed
  @Produces("application/json")
  @Consumes("application/json")
  @Path("/register_queue")
  public Response registerQueue(@QueryParam("name") String name) {
    return Response.ok(queueService.registerQueue(name)).build();
  }

  @PUT
  @Timed
  @Produces("application/json")
  @Consumes("application/json")
  @Path("/register_sub")
  public Response registerSubscription(@QueryParam("queue_name") String qName,
                                       @QueryParam("sub_name") String subName,
                                       @QueryParam("dest_http") String destHttp) {

    return Response.ok(queueService.registerSubscription(qName, subName, destHttp)).build();
  }

  @DELETE
  @Timed
  @Produces("application/json")
  @Consumes("application/json")
  @Path("/del_sub")
  public Response delSubscription(@QueryParam("queue_name") String qName,
                                       @QueryParam("sub_name") String subName) {
    return Response.ok(queueService.deleteSubscription(qName, subName)).build();
  }

  @PUT
  @Timed
  @Produces("application/json")
  @Consumes("application/json")
  @Path("/start_consumer")
  public Response startConsumer() {
    queueService.startConsumer();
    return Response.ok().build();
  }

  @PUT
  @Timed
  @Produces("application/json")
  @Consumes("application/json")
  @Path("/stop_consumer")
  public Response stopConsumer() {
    queueService.stopConsumer();
    return Response.ok().build();
  }

  @POST
  @Timed
  @Produces("application/json")
  @Consumes("application/json")
  @Path("/produce")
  public Response produceMessage(@QueryParam("queue_name")String queueName, JsonNode message) {
    queueService.produceMessage(queueName, message);
    return Response.ok().build();
  }

  @PUT
  @Timed
  @Produces("application/json")
  @Consumes("application/json")
  @Path("/sink")
  public Response sink(String request) {
    System.out.println("recieved " + request);
    return Response.ok().build();
  }
}
