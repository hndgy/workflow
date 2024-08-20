package fr.hndgy.jworkflow.action.log;

import fr.hndgy.jworkflow.entity.ActionResult;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LogResult extends ActionResult {

    @Builder
    public LogResult(Status status) {
        super(status);
    }
}
