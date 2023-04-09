package APITests.Pojo;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@ToString

@JsonIgnoreProperties(value = "id",allowSetters = true)
public class Spartan {

        //getter setter
        //toString
        private int id;
        private String name;
        private String gender;
        private long phone;

}
/*Scenario: we have a Spartan pojo class for saving response JSON which consist of 4 fields.

Can we use the same class to send a request body ? because we deserialize 4 fields but we only need to send 3 fields without id. Documentation says ID is auto generated please do not send it !

So the solution is we need Jackson, when it comes to converting our object and sending as a JSON basically serializes the ignored ID field.

how to tell ?

on top of your pojo class use
@JsonIgnoreProperties(value = "id",allowSetters = true)
 annotation to tell only deserialize, do NOT serialize. */
