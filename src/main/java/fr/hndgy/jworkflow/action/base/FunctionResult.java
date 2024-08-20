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
public class HttpResult extends ActionResult {

    private Map<String, Object> body;

    @Builder
    public HttpResult(Status status, Map<String, Object> body) {
        super(status);

        this.body = body;
    }
}
