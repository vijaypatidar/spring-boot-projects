package com.example.jpa.controllers;

import com.example.jpa.models.GraphQLInput;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GraphQLController {
    private final GraphQL graphQL;

    public GraphQLController(GraphQL graphQL) {
        this.graphQL = graphQL;
    }

    @PostMapping(
            value = "graphql",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ExecutionResult execute(@RequestBody GraphQLInput body){
        log.info("execute query : "+body.toString());
        ExecutionInput input = ExecutionInput.newExecutionInput()
                .query(body.getQuery())
                .variables(body.getVariables())
                .build();
        return graphQL.execute(input);
    }
}
