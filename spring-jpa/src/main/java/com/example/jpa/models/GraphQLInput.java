package com.example.jpa.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class GraphQLInput {
    private String query;
    private Map<String,Object> variables;

}
