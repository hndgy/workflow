package fr.hndgy.jworkflow.action.logical;

import fr.hndgy.jworkflow.entity.ActionResult;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class IfResult extends ActionResult {

    private boolean conditionResult;

    private List<ActionResult> results;

    @Builder
    public IfResult(Status status, boolean conditionResult, List<ActionResult> results) {
        super(status);
        this.conditionResult = conditionResult;
        this.results = results;
    }
}
