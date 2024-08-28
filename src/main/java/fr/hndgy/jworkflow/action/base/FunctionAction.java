package fr.hndgy.jworkflow.action.base;

import fr.hndgy.jworkflow.entity.ActionDefinition;
import fr.hndgy.jworkflow.entity.ActionResult;
import fr.hndgy.jworkflow.entity.Context;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.function.Function;

@Slf4j
public class FunctionAction extends ActionDefinition {

    private final Function<Context, ?> function;

    @Builder
    public FunctionAction(String id, Function<Context, ?> function) {
        super(id, "function");

        this.function = function;
    }

    @Override
    public ActionResult apply(Context context) {
        Object response = function.apply(context);

        return FunctionResult.builder()
                .result(response)
                .build();
    }

    @Override
    protected ActionResult onFail() {
        return FunctionResult.builder()
                .status(ActionResult.Status.FAILURE)
                .build();
    }
}
