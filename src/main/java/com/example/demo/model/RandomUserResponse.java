package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
@Getter
public class RandomUserResponse {
    @JsonProperty("firstName")
    protected String first_name;
    @JsonProperty("lastName")
    protected String last_name;
    @JsonProperty("gender")
    protected String gender;
    public RandomUserResponse(){}


    public RandomUserResponse(String first_name, String last_name, String gender) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
    }
}
