package fr.hndgy.jworkflow.action.log;

import com.fasterxml.jackson.annotation.JsonCreator;
import fr.hndgy.jworkflow.engine.WorkflowEngine;
import fr.hndgy.jworkflow.entity.ActionDefinition;
import fr.hndgy.jworkflow.entity.ActionResult;
import fr.hndgy.jworkflow.entity.Context;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogAction extends ActionDefinition {

    private final String message;

    @Jacksonized
    @JsonCreator
    @Builder
    public LogAction(String id, String message) {
        super(id, "log");

        this.message = message;
    }

    @Override
    public ActionResult apply(Context context) {
        log.info(resolve(message, context));

        log.info("{}", context.bean(WorkflowEngine.class));

        return LogResult.builder()
                .build();
    }

    @Override
    protected ActionResult onFail() {
        return LogResult.builder()
                .status(ActionResult.Status.FAILURE)
                .build();
    }
}
