package com.sire.goal.service;


import com.sire.goal.models.entities.Goal;
import com.sire.goal.models.entities.User;
import com.sire.goal.models.responses.GoalResponse;
import com.sire.goal.repository.GoalRepository;
import com.sire.goal.repository.UserRepository;
import com.sire.goal.security.service.JwtService;
import com.sire.goal.tools.utils.SpliteToken;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {
    private final UserRepository userRepository;
    private final GoalRepository goalRepository;
    private final JwtService jwtService;
    private final SpliteToken spliteToken;

    public GoalService(UserRepository userRepository, GoalRepository goalRepository, JwtService jwtService, SpliteToken spliteToken) {
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
        this.jwtService = jwtService;
        this.spliteToken = spliteToken;
    }

    public GoalResponse createGoal(String token, Goal request){
        if (token == null) {
            return new GoalResponse("You are not logged in");
        }
        if (request.getText() == null || request.getText().isEmpty()) {

            return new GoalResponse("Please enter Goal!");
        }

        Goal goal = new Goal();
        String filteredToken = spliteToken.split(token);
        String username = jwtService.extractUsername(filteredToken);

        User user = userRepository.findByUsername(username).orElseThrow();
        try {
            goal.setText(request.getText());
            goal.setUser(user);
            goalRepository.save(goal);

        }catch (Exception e){
            return new GoalResponse("Something went wrong");
        }

        return new GoalResponse(goal,"Card created successfully");
    }

    public GoalResponse getGoal(Integer goalId){
        if (goalId == null) {
            return new GoalResponse("Please enter goal ID");

        }
        Goal goal = goalRepository.findById(goalId).orElseThrow();
        if (goal == null) {
            return new GoalResponse("Goal not found! Please try again!");
        }

        return new GoalResponse(goal);
    }

    public List<Goal> getGoals(String token){
        String filteredToken = spliteToken.split(token);
        String username = jwtService.extractUsername(filteredToken);
        User user = userRepository.findByUsername(username).orElseThrow();
        Integer userId = user.getId();
        List<Goal> goals = goalRepository.findByUserId(userId);
        return goals;
    }

    public GoalResponse updateGoal(Integer goalId, Goal request){
        if(goalId == null){
            return new GoalResponse("Please enter Goal ID");
        }
        Goal goal = goalRepository.findById(goalId).orElseThrow();
        if (goal == null) {
            return new GoalResponse("Goal not found! Please try again! or enter valid goal ID");
        }
        try {
            goal.setText(request.getText());
            goalRepository.save(goal);
        } catch (Exception e){
            return new GoalResponse("Something went wrong!");
        }

        return new GoalResponse(goal,"Card updated successfully");
    }




    public GoalResponse deleteGoal(Integer goalId) {
        if(goalId == null){
            return new GoalResponse("Please enter Goal ID");
        }
        Goal goal = goalRepository.findById(goalId).orElseThrow();
        if(goalId == null){
            return new GoalResponse("Goal not found! Please try again!");
        }
        try {
            goalRepository.deleteById(goalId);
        }catch (Exception e){
            return new GoalResponse("Something went wrong!");
        }

        return new GoalResponse(goal, "Goal deleted successfully!");
    }
}
