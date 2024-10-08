package fr.hndgy.jworkflow;

import fr.hndgy.jworkflow.action.base.FunctionAction;
import fr.hndgy.jworkflow.action.http.HttpAction;
import fr.hndgy.jworkflow.action.log.LogAction;
import fr.hndgy.jworkflow.action.logical.EachAction;
import fr.hndgy.jworkflow.action.logical.IfAction;
import fr.hndgy.jworkflow.engine.WorkflowEngine;
import fr.hndgy.jworkflow.entity.ActionDefinition;
import fr.hndgy.jworkflow.entity.WorkflowDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootApplication
public class JworkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(JworkflowApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(WorkflowEngine workflowEngine) {
        return (args) -> {

            Map<String, Object> parameters = Map.of(
                    "name", "Nicolas",
                    "link", "https://www.google.com"
            );

            WorkflowDefinition definition = WorkflowDefinition.builder()
                    .id("test")
                    .name("test")
                    .actions(List.of(
                            ActionDefinition.load("""
                                    {
                                        "id" : "log_smth",
                                        "message" : "log from json : {{parameters.name}}"
                                    }
                                    """, LogAction.class
                            ),
                            FunctionAction.builder()
                                    .id("function_action")
                                    .function(context -> Map.of(
                                            "greeting", "Hello World"
                                    ))
                                    .build(),
                            HttpAction.builder()
                                    .id("http_action")
                                    .link("https://www.google.com?q={{parameters.name}}")
                                    .build(),
                            EachAction.builder()
                                    .id("each_action")
                                    .values(List.of(
                                            Map.of("name", "Nico"),
                                            Map.of("name", "Alex"),
                                            Map.of("name", "John")
                                    ))
                                    .doAction(
                                            EachAction.builder()
                                                    .id("each_action_2")
                                                    .values(List.of("1", "2", "3"))
                                                    .doAction(
                                                            IfAction.builder()
                                                                    .id("if_action")
                                                                    .value1("{{parameters.each_action_2_index}}")
                                                                    .value2("1")
                                                                    .doActions(List.of(
                                                                            LogAction.builder()
                                                                                    .id("log_name")
                                                                                    .message("{{parameters.each_action_index}} - {{parameters.each_action_2_index}} {{parameters.each_action_item.name}}")
                                                                                    .build()
                                                                    ))
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .build(),
                            IfAction.builder()
                                    .id("if_action")
                                    .value1("{{results.http_action.body.test}}")
                                    .value2("ok")
                                    .negate(false)
                                    .doActions(List.of(
                                            LogAction.builder()
                                                    .id("log_name")
                                                    .message("Test is {{results.http_action.body.test}}")
                                                    .build()
                                    ))
                                    .elseActions(List.of(
                                            LogAction.builder()
                                                    .id("log_name")
                                                    .message("ELSE")
                                                    .build()
                                    ))
                                    .build()
                    ))
                    .build();

            var workflow = workflowEngine.execute(
                    definition,
                    parameters
            );

            log.info("Result : {}", workflow.getContext());
        };
    }
}
// company created -> send email owner -> send email to all employees