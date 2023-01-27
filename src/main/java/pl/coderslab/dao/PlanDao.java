package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.LatestPlan;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.*;

public class PlanDao {
    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name,description,created,admin_id) VALUES (?,?,?,?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan WHERE admin_id = ?;";
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, created = ? WHERE	id = ?;";
    private static final String COUNT_PLANS_FOR_ADMIN = "SELECT count(plan.id) count FROM plan WHERE admin_id = ?;";
    private static final String GET_LATEST_PLAN_FOR_ADMIN = "SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description\n" +
            "FROM `recipe_plan`\n" +
            "         JOIN day_name on day_name.id=day_name_id\n" +
            "         JOIN recipe on recipe.id=recipe_id WHERE\n" +
            "        recipe_plan.plan_id = (SELECT MAX(id) from plan WHERE admin_id = ?)\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String PLAN_NAME = "SELECT plan.name FROM plan WHERE plan.id = (SELECT MAX(id) from plan WHERE admin_id = ?);";
    private static final String DAY_OF_WEEK = "SELECT * FROM day_name ;";

    public Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY);
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;
    }

    public List<Plan> findAll(Integer id) {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_QUERY)) {
            statement.setString(1, Integer.toString(id));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setDescription(resultSet.getString("description"));
                planList.add(planToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }

    public void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();
            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Plan not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insStatement = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insStatement.setString(1, plan.getName());
            insStatement.setString(2, plan.getDescription());
            insStatement.setString(3, plan.getCreated());
            insStatement.setString(4, Integer.toString(plan.getAdminId()));
            int result = insStatement.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insStatement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setInt(4, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setString(3, plan.getCreated());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String countPlansForAdmin(Integer id) {
        String countPlans = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_PLANS_FOR_ADMIN)) {
            statement.setString(1, Integer.toString(id));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    countPlans = resultSet.getString("count");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countPlans;
    }

    public static List<LatestPlan> getLatestPlanForAdmin(Integer id) {
        List<LatestPlan> latestPlanInfo = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_LATEST_PLAN_FOR_ADMIN)) {
            statement.setString(1, Integer.toString(id));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    LatestPlan latestPlan = new LatestPlan();
                    latestPlan.setDayName(resultSet.getString("day_name"));
                    latestPlan.setMealName(resultSet.getString("meal_name"));
                    latestPlan.setRecipeName(resultSet.getString("recipe_name"));
                    latestPlan.setRecipeDescription(resultSet.getString("recipe_description"));
                    latestPlanInfo.add(latestPlan);
                    System.out.println(latestPlanInfo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return latestPlanInfo;
    }

    public String getPlanName(Integer id) {
        String planName = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(PLAN_NAME)) {
            statement.setString(1, Integer.toString(id));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                planName = resultSet.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planName;
    }
}

