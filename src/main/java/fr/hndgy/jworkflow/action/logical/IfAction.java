package fr.hndgy.jworkflow.action.logical;

import fr.hndgy.jworkflow.entity.ActionDefinition;
import fr.hndgy.jworkflow.entity.ActionResult;
import fr.hndgy.jworkflow.entity.Context;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class IfAction extends ActionDefinition {

    private String value1;

    private String value2;

    private boolean negate;

    private List<ActionDefinition> doActions;

    private List<ActionDefinition> elseActions;

    @Builder
    public IfAction(
            String id,
            String value1,
            String value2,
            boolean negate,
            List<ActionDefinition> doActions,
            List<ActionDefinition> elseActions
    ) {
        super(id, "if");

        this.value1 = value1;
        this.value2 = value2;
        this.negate = negate;
        this.doActions = doActions;
        this.elseActions = Optional.ofNullable(elseActions)
                .orElseGet(ArrayList::new);
    }

    @Override
    public ActionResult apply(Context context) {
        var conditionRes = Objects.equals(resolve(value1, context), resolve(value2, context)) && !negate;

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
