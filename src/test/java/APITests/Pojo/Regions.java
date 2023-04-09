package APITests.Pojo;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.*;

@Getter //from lombok dependency
@Setter //from lombok dependency
@ToString //from lombok dependency
@JsonIgnoreProperties(ignoreUnknown = true)  //this is from jackson dependency
public class Regions {
    private List<Regions> items;
    private int count;
    @JsonProperty("region_id")
    private String region_id;
    @JsonProperty("region_name")
    private String region_name;
    private List<Link> links;
}
