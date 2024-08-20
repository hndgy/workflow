package fr.hndgy.jworkflow.engine;

import fr.hndgy.jworkflow.entity.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class WorkflowEngine {

    public Workflow execute(
            WorkflowDefinition definition,
            Map<String, Object> parameters
    ) {
        return Workflow.from(definition.getActions(), parameters).execute();
    }
}
