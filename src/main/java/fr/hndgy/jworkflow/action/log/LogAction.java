package fr.hndgy.jworkflow.action.log;

import fr.hndgy.jworkflow.entity.ActionDefinition;
import fr.hndgy.jworkflow.entity.ActionResult;
import fr.hndgy.jworkflow.entity.Context;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogAction extends ActionDefinition {

    private final String message;

    @Builder
    public LogAction(String id, String name, String message) {
        super(id, "log");

        this.message = message;
    }

    @Override
    public ActionResult apply(Context context) {
        log.info(resolve(message, context));

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
