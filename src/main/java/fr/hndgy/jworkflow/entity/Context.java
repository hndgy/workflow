package fr.hndgy.jworkflow.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
public class Context {

    private ApplicationContext applicationContext;

    private Map<String, Object> parameters;

    private Map<String, ActionResult> results;

    @Builder
    public Context(
            ApplicationContext applicationContext,
            Map<String, Object> parameters,
            Map<String, ActionResult> results
    ) {
        this.applicationContext = applicationContext;
        this.parameters = new HashMap<>(parameters);
        this.results = Optional.ofNullable(results)
                .orElseGet(HashMap::new);
    }

    public <T> T bean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
