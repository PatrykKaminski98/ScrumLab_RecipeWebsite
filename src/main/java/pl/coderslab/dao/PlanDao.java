package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanDetails;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {

    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan (name, description, created, admin_id) VALUES (?,?,?,?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String FIND_ALL_PLAN_QUERY = "SELECT * FROM plan WHERE admin_id = ? ORDER BY created DESC;";
    private static final String READ_PLAN_QUERY = "SELECT * FROM plan where id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE plan set name = ?, description = ?, created = ?, admin_id = ? WHERE id = ?;";
    private static final String GET_NUMBER_OF_PLANS = "SELECT COUNT(id) AS NumberOfPlanes FROM plan WHERE admin_id = ?";
    private static final String GET_PLAN_DETAILS = "SELECT plan.name as name, recipe.id, day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description FROM `recipe_plan` JOIN plan on plan.id=recipe_plan.plan_id JOIN day_name on day_name.id=day_name_id JOIN recipe on recipe.id=recipe_id WHERE recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE plan_id = ?)ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String GET_LAST_PLAN = "SELECT plan.name as name, recipe.id, day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description FROM `recipe_plan` JOIN plan on plan.id=recipe_plan.plan_id JOIN day_name on day_name.id=day_name_id JOIN recipe on recipe.id=recipe_id WHERE recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String FORMAT_DATA_TIME = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

  /**
     *
     * Get plan by id
     *
     * @param planId
     * @return
     */
    public Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    plan.setAdmin_id(resultSet.getInt("admin_id"));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;

    }

    /**
     * Return all plans
     *
     * @return
     */
    public List<Plan> findAll(Admin admin) {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLAN_QUERY);
             ) {
            statement.setInt(1, admin.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setAdmin_id(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }

    /**
     * Create plan
     *
     * @param plan
     * @return
     */
    public Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, plan.getName());
            insertStm.setString(2, plan.getDescription());
            insertStm.setString(3, FORMAT_DATA_TIME);
            insertStm.setInt(4, plan.getAdmin_id());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
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


    /**
     * Remove plan by id
     *
     * @param planId
     */
    public void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update plan
     *
     * @param plan
     */
    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setInt(5, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setString(3, plan.getCreated());
            statement.setInt(   4, plan.getAdmin_id());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int numberOfPlans(Admin admin){
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_NUMBER_OF_PLANS)
        ) {
            statement.setInt(1, admin.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return resultSet.getInt("NumberOfPlanes");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Nie znaleziono wyników");
        return 0;
    }

    public List<PlanDetails> getLastPlan(Admin admin){
        List<PlanDetails> planDetailsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_LAST_PLAN)
        ) {
            statement.setInt(1, admin.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    PlanDetails planDetails = new PlanDetails();
                    planDetails.setDayName(resultSet.getString("day_name"));
                    planDetails.setMealName(resultSet.getString("meal_name"));
                    planDetails.setRecipeName(resultSet.getString("recipe_name"));
                    planDetails.setRecipeDescription(resultSet.getString("recipe_description"));
                    planDetails.setPlanName(resultSet.getString("name"));
                    planDetails.setRecipeId(resultSet.getString("id"));
                    planDetailsList.add(planDetails);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planDetailsList;
    }

    public List<PlanDetails> getPlanDetails(Integer planId){
        List<PlanDetails> planDetailsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PLAN_DETAILS)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    PlanDetails planDetails = new PlanDetails();
                    planDetails.setDayName(resultSet.getString("day_name"));
                    planDetails.setMealName(resultSet.getString("meal_name"));
                    planDetails.setRecipeName(resultSet.getString("recipe_name"));
                    planDetails.setRecipeDescription(resultSet.getString("recipe_description"));
                    planDetails.setPlanName(resultSet.getString("name"));
                    planDetails.setRecipeId(resultSet.getString("id"));
                    planDetailsList.add(planDetails);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planDetailsList;
    }

}