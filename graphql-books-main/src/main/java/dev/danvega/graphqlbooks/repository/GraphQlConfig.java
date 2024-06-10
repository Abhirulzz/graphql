package dev.danvega.graphqlbooks.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import graphql.GraphQL;
import graphql.execution.instrumentation.Instrumentation;
import graphql.parser.ParserOptions;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLSchema;

@Configuration
public class GraphQlConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(ExtendedScalars.GraphQLBigDecimal);

    }
    
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer1() {
        return wiringBuilder -> wiringBuilder
                .scalar(ExtendedScalars.GraphQLLong);

    }
    
  /*  @Bean
    public GraphQL graphQL(GraphQLSchema graphQLSchema, Instrumentation instrumentation) {
        // Set ParserOptions with a higher maximum tokens limit
        ParserOptions parserOptions = ParserOptions.newParserOptions()
                                                   .maxTokens(30000) // Increase the limit from the default
                                                   .build();

        return GraphQL.newGraphQL(graphQLSchema)
                      .instrumentation(instrumentation)
                      .parserOptions(parserOptions)
                      .build();
    
*/

}
