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
public class EachResult extends ActionResult {

    private List<ActionResult> results;

    @Builder
    public EachResult(
            Status status,
            List<ActionResult> results
    ) {
        super(status);

        this.results = results;
    }
}
