package fr.hndgy.jworkflow.action.logical;

import fr.hndgy.jworkflow.entity.ActionDefinition;
import fr.hndgy.jworkflow.entity.ActionResult;
import fr.hndgy.jworkflow.entity.Context;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

public class IfAction extends ActionDefinition {

    private String field;

    private String value;

    private List<ActionDefinition> doActions;

    private List<ActionDefinition> elseActions;

    @Builder
    public IfAction(
            String id,
            String field,
            String value,
            List<ActionDefinition> doActions,
            List<ActionDefinition> elseActions
    ) {
        super(id, "if");

        this.field = field;
        this.value = value;
        this.doActions = doActions;
        this.elseActions = elseActions;
    }

    @Override
    public ActionResult apply(Context context) {
        var conditionRes =  Objects.equals(resolve(field, context), value);

        if (conditionRes) {
            List<ActionResult> results = doActions.stream().map(action -> action.execute(context)).toList();

            return IfResult.builder()
                    .conditionResult(conditionRes)
                    .results(results)
                    .build();
        }

        List<ActionResult> results = elseActions.stream().map(action -> action.execute(context)).toList();

        return IfResult.builder()
                .conditionResult(conditionRes)
                .results(results)
                .build();
    }

    @Override
    protected ActionResult onFail() {
        return IfResult.builder()
                .status(ActionResult.Status.FAILURE)
                .build();
    }
}
