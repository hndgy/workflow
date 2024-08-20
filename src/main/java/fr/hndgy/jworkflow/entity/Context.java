package fr.hndgy.jworkflow.entity;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
public class Context {

    private Map<String, Object> parameters;

    private Map<String, ActionResult> results;

    @Builder
    public Context(
            Map<String, Object> parameters,
            Map<String, ActionResult> results
    ) {
        this.parameters = new HashMap<>(parameters);
        this.results = Optional.ofNullable(results)
                .orElseGet(HashMap::new);
    }
}
