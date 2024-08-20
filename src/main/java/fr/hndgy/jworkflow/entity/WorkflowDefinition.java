package fr.hndgy.jworkflow.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WorkflowDefinition {

    private String id;

    private String name;

    private List<ActionDefinition> actions;
}
