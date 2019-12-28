package saurabh.araiyer.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.dropwizard.Configuration;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false)
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppConfiguration extends Configuration {

  private String template;

  private String defaultName = "Stranger";

  public String getDefaultName() {
    return defaultName;
  }
}
