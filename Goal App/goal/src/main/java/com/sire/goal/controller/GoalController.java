package com.sire.goal.controller;


import com.sire.goal.models.entities.Goal;
import com.sire.goal.models.responses.GoalResponse;
import com.sire.goal.service.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Goal", description = "Goal Setting APIs")
@RestController
@RequestMapping("/goal")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @Operation(
            summary = "Add Goal",
            description = "Add Goal by Populating all fiels",
            tags = {"Goals","post"}
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Goal.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @SecurityRequirement(name="Bearer Authentication")
    @PostMapping("/create")
    public ResponseEntity<GoalResponse> createGoal(
            @RequestHeader(name="Authorization") String token,
            @RequestBody Goal goal
    ){
        return new ResponseEntity<>(goalService.createGoal(token, goal), HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get list of goals",
            description = "Get a list of all user goals by checking token and find all goals in database",
            tags = { "Goals", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Goal.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @SecurityRequirement(name="Bearer Authentication")
    @GetMapping("/all")
    public ResponseEntity<List<Goal>> getAllCards(
            @RequestHeader(name="Authorization") String token
    ) {
        return ResponseEntity.ok(goalService.getGoals(token));
    }

    @Operation(
            summary = "Get goal by id",
            description = "Get goal by specifying id",
            tags = { "Goals", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Goal.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @SecurityRequirement(name="Bearer Authentication")
    @GetMapping("/{id}")
    public ResponseEntity<GoalResponse> getCardById(
            @PathVariable("id") Integer id
    ){
        return ResponseEntity.ok(goalService.getGoal(id));
    }

    @Operation(
            summary = "Edit goal",
            description = "Edit goal by specifying id and entering all fields",
            tags = { "Goals", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Goal.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @SecurityRequirement(name="Bearer Authentication")
    @PutMapping("/{id}")
    public ResponseEntity<GoalResponse> updateGoal(
            @PathVariable("id") Integer id,
            @RequestBody Goal goal
    ){
        return ResponseEntity.ok(goalService.updateGoal(id, goal));
    }

    @Operation(
            summary = "Delete goal",
            description = "Delete goal by using specified goal id",
            tags = { "Goals", "delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Goal.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @SecurityRequirement(name="Bearer Authentication")
    @DeleteMapping("/{id}")
    public ResponseEntity<GoalResponse> deleteCard(
            @PathVariable Integer id
    ){
        return ResponseEntity.ok(goalService.deleteGoal(id));
    }


}
