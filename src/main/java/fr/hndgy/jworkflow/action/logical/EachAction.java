package fr.hndgy.jworkflow.action.logical;

import fr.hndgy.jworkflow.entity.ActionDefinition;
import fr.hndgy.jworkflow.entity.ActionResult;
import fr.hndgy.jworkflow.entity.Context;
import lombok.Builder;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EachAction extends ActionDefinition {

    private List<Object> values;

    private ActionDefinition doActions;

    @Builder
    public EachAction(
            String id,
            List<Object> values,
            ActionDefinition doAction
    ) {
        super(id, "each");

        this.values = values;
        this.doActions = doAction;
    }

    @Override
    public ActionResult apply(Context context) {
        var index = new AtomicInteger();

        List<ActionResult> results = values.stream()
                .map(value -> {
                    context.getParameters()
                            .put(id + "_item" , value);
                    context.getParameters()
                            .put(id + "_index" , index.getAndIncrement());
                    return doActions.execute(context);
                }).toList();

        context.getParameters().remove(id + "_item");
        context.getParameters().remove(id + "_index");

        return EachResult.builder()
                .results(results)
                .build();
    }

    @Override
    protected ActionResult onFail() {
        return EachResult.builder()
                .status(ActionResult.Status.FAILURE)
                .build();
    }
}
