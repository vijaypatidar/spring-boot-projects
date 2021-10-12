package com.example.jpa.config;

import com.example.jpa.services.CourseService;
import com.example.jpa.services.StudentService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class GraphQLConfig {
    private final StudentService studentService;
    private final CourseService courseService;

    public GraphQLConfig(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Bean
    public GraphQL getGraphQL() throws IOException {
        SchemaParser parser = new SchemaParser();
        ClassPathResource schema = new ClassPathResource("schema.graphql");
        TypeDefinitionRegistry typeDefinitionRegistry = parser.parse(schema.getInputStream());

        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getStudent", studentService.getStudent()))
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getStudents", studentService.getStudents()))
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getCourse", courseService.getCourse()))
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getCourses", courseService.getCourses()))
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

        return GraphQL.newGraphQL(graphQLSchema).build();

    }
}
