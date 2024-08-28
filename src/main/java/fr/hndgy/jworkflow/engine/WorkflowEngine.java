package fr.hndgy.jworkflow.engine;

import fr.hndgy.jworkflow.JworkflowApplication;
import fr.hndgy.jworkflow.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkflowEngine {

    private final ApplicationContext applicationContext;

    public Workflow execute(
            WorkflowDefinition definition,
            Map<String, Object> parameters
    ) {
        return Workflow.from(
                applicationContext,
                definition.getActions(),
                parameters
        ).execute();
    }
}
