package APITests.Pojo;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)//If your POJO does not contain certain properties that JSON does contain, they are ignored and no error is thrown.
public class Employee {

    @JsonProperty("first_name")//Alanlarımızın json içerisinde hangi isimle gözükeceğini belirledik.
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("job_id")
    private String jobId;

    private int salary;
}
