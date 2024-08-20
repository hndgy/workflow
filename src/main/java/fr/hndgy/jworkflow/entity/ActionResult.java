package fr.hndgy.jworkflow.entity;

import lombok.Data;

import java.util.Optional;

@Data
public abstract class ActionResult {

    protected Status status;

    public ActionResult(Status status) {
        this.status = Optional.ofNullable(status).orElse(Status.SUCCESS);
    }

    public enum Status {
        SUCCESS,
        FAILURE
    }
}
