package fr.hndgy.jworkflow.action.http;

import fr.hndgy.jworkflow.entity.ActionDefinition;
import fr.hndgy.jworkflow.entity.ActionResult;
import fr.hndgy.jworkflow.entity.Context;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class HttpAction extends ActionDefinition {

    private final String link;

    @Builder
    public HttpAction(String id, String link) {
        super(id, "http");

        this.link = link;
    }

    @Override
    public ActionResult apply(Context context) {
        log.info("Http : {}", resolve(link, context));

        return HttpResult.builder()
                .body(Map.of("test", "ok"))
                .build();
    }

    @Override
    protected ActionResult onFail() {
        return HttpResult.builder()
                .status(ActionResult.Status.FAILURE)
                .build();
    }
}
