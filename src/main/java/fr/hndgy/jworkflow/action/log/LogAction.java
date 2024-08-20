package fr.hndgy.jworkflow.action;

import fr.hndgy.jworkflow.entity.ActionDefinition;
import fr.hndgy.jworkflow.entity.ActionResult;
import fr.hndgy.jworkflow.entity.Context;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
public class LogAction extends ActionDefinition {

    private final String message;

    @Override
    protected ActionResult execute(Context context) {
        return log.info();
    }
}
