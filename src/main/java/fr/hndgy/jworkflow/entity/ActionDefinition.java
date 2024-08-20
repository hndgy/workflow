package fr.hndgy.jworkflow.entity;

public abstract class ActionDefinition {

    abstract ActionResult execute(Context context);
}
