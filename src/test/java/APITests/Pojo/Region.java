package APITests.Pojo;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
public class Region {
    //region_id
    //if your json key and variable name not matching, you can map it with jsonProperty
    @JsonProperty("region_id")
    private int rId;
    @JsonProperty("region_name")
    private String region_name;
    @JsonProperty("links")
    private List<Link> links;   //This variable make a connection with Link class.

    private List<Regions> items;
    private boolean hasMore;
    private int limit;
    private int offset;
    private int count;
}
