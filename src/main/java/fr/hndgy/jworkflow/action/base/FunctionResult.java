package fr.hndgy.jworkflow.action.base;

import fr.hndgy.jworkflow.entity.ActionResult;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FunctionResult extends ActionResult {

    private Object result;

    @Builder
    public FunctionResult(Status status, Object result) {
        super(status);

        this.result = result;
    }
}
