package fr.hndgy.jworkflow.entity;

import com.github.jknack.handlebars.Handlebars;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@Data
public abstract class ActionDefinition {

    protected String id;

    protected String name;

    protected int retry = 0;

    public ActionDefinition(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract ActionResult apply(Context context);

    protected abstract ActionResult onFail();

    public ActionResult execute(Context context) {
        try {
            return apply(context);
        } catch (Exception e) {
            log.error("Action {} failed", id, e);
            return onFail();
        }
    }

    protected String resolve(
            String value,
            Context context
    ) {
        try {
            return new Handlebars().compileInline(value).apply(context);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
