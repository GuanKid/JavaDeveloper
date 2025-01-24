package com.sire.goal.models.responses;

import com.sire.goal.models.entities.Goal;

public class GoalResponse {
    private Goal goal;
    private String response;

    public Goal getGoal(){
        return goal;
    }

    public GoalResponse(Goal goal, String response) {
        this.goal = goal;
        this.response = response;
    }
    public GoalResponse(Goal goal){
        this.goal = goal;

    }

    public GoalResponse(String response){
        this.response = response;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
