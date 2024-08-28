package fr.hndgy.jworkflow.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Data
@Builder
public class Workflow {

    private List<ActionDefinition> actions;

    private Context context;

    public static Workflow from(
            ApplicationContext applicationContext,
            List<ActionDefinition> actions,
            Map<String, Object> parameters
    ) {
        return Workflow.builder()
                .context(
                        Context.builder()
                                .applicationContext(applicationContext)
                                .parameters(parameters)
                                .build()
                )
                .actions(actions)
                .build();
    }

    public Workflow execute() {
        actions.forEach(action -> {
                    var result = action.execute(context);

                    if (result.getStatus() == ActionResult.Status.FAILURE) {
                        log.error("Action {} failed. Stop workflow.", action.getId());
                        return;
                    }

                    context.getResults().put(action.getId(), result);
                });

        return this;
    }
}
