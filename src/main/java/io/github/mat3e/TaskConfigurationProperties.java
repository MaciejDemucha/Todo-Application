package io.github.mat3e;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("task")
class TaskConfigurationProperties {
    private boolean allowMultipleTasksFromTemplate;

    boolean isAllowMultipleTasksFromTemplate(){
        return allowMultipleTasksFromTemplate;
    }

    void setAllowMultipleTasksFromTemplate(final boolean allowMultipleTasksFromTemplate){
        this.allowMultipleTasksFromTemplate = allowMultipleTasksFromTemplate;
    }
}