package flightbookingDB;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class AirportFlightConstantRamp10 extends Simulation { //constant spike-ramp up 10 sec

  private HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080")
          .acceptHeader("*/*")
          .acceptEncodingHeader("gzip, deflate, br")
          .acceptLanguageHeader("en-US,en;q=0.9")
          .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

  private ScenarioBuilder constantLoadScenario = scenario("FlightBooking_ConstantLoad")
          .exec(http("search airports")
                          .get("/airports/HEL"),
                  pause(10),
                  http("search flights")
                          .get("/flights/FL-3")
          );

  private ScenarioBuilder spikeLoadScenario = scenario("FlightBooking_SpikeLoad")
          .exec(http("search airports")
                          .get("/airports/HEL"),
                  pause(10),
                  http("search flights")
                          .get("/flights/FL-3")
          );

  public AirportFlightConstantRamp10() {
    setUp(
            constantLoadScenario.injectOpen(
                    constantUsersPerSec(50).during(1200)),
            // This will generate a constant load of 50 users/sec during 20 minutes (1200 seconds)

            spikeLoadScenario.injectOpen(
                    nothingFor(30), // waits for 30 seconds
                    rampUsers(500).during(10), // ramp up to 500 users in 10 seconds - simulating the 1st spike
                    nothingFor(240), // waits for 4 minute
                    rampUsers(500).during(10), // ramp up to 500 users in 10 seconds - simulating the 2nd spike
                    nothingFor(240), // waits for 4 minute
                    rampUsers(500).during(10), // ramp up to 500 users in 10 seconds - simulating the 3rd spike
                    nothingFor(240), // waits for 4 minute
                    rampUsers(500).during(10), // ramp up to 500 users in 10 seconds - simulating the 4th spike
                    nothingFor(240), // waits for 4 minute
                    rampUsers(500).during(10)) // ramp up to 500 users in 10 seconds - simulating the 5th spike
    ).protocols(httpProtocol);
  }
}