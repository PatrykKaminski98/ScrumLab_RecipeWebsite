package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class RecipeDao {
    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name,ingredients,description,created,updated,preparation_time,preparation,admin_id) VALUES (?,?,?,?,?,?,?,?);";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";
    private static final String FIND_ALL_RECIPES_QUERY = "SELECT * FROM recipe WHERE admin_id = ?;";
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE	recipe SET name = ? , ingredients = ?, description = ?, created = ?, updated = ?, preparation_time = ?, preparation = ?, admin_id = ? WHERE	id = ?;";
    private static final String GET_NUMBER_OF_RECIPES = "SELECT COUNT(id) AS NumberOfRecipes FROM recipe WHERE admin_id = ?";
    private static final String FORMAT_DATA_TIME = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private static final String ADD_RECIPE_TO_PLAN = "INSERT INTO recipe_plan(recipe_id,meal_name,display_order,day_name_id,plan_id) VALUES (?,?,?,?,?);";
    private static final String GET_RECIPE_ID = "SELECT id FROM recipe WHERE name = ?";
    private static final String DELETE_RECIPE_FROM_PLAN = "DELETE FROM recipe_plan WHERE recipe_id = ? AND plan_id = ? AND meal_name = ?";

    public void deleteRecipeFromPlan(int recipeId, int planId, String mealName) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_FROM_PLAN )) {
            statement.setInt(1, recipeId);
            statement.setInt(2, planId);
            statement.setString(3, mealName);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Recipe read(Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getString("created"));
                    recipe.setUpdated(resultSet.getString("updated"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public List<Recipe> findAll(Admin admin) {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_QUERY)) {
            statement.setInt(1, admin.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getInt("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setCreated(resultSet.getString("created"));
                recipe.setUpdated(resultSet.getString("updated"));
                recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                recipe.setPreparation(resultSet.getString("preparation"));
                recipe.setAdminId(resultSet.getInt("admin_id"));
                recipeList.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public Recipe create(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, recipe.getName());
            insertStm.setString(2, recipe.getIngredients());
            insertStm.setString(3, recipe.getDescription());
            insertStm.setString(4, FORMAT_DATA_TIME);
            insertStm.setString(5, FORMAT_DATA_TIME);
            insertStm.setInt(6, recipe.getPreparationTime());
            insertStm.setString(7, recipe.getPreparation());
            insertStm.setInt(8, recipe.getAdminId());
            int result = insertStm.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
            statement.setInt(1, recipeId);
            statement.executeUpdate();
            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Recipe not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {
            statement.setInt(9, recipe.getId());
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getIngredients());
            statement.setString(3, recipe.getDescription());
            statement.setString(4, recipe.getCreated());
            statement.setString(5, FORMAT_DATA_TIME);
            statement.setInt(6, recipe.getPreparationTime());
            statement.setString(7, recipe.getPreparation());
            statement.setInt(8, recipe.getAdminId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int numberOfRecipes(Admin admin){
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_NUMBER_OF_RECIPES)
        ) {
            statement.setInt(1, admin.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return resultSet.getInt("NumberOfRecipes");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Nie znaleziono wyników");
        return 0;
    }

    public void addRecipeToPlan(int recipe_id, String meal_name, int display_order, int day_name_id, int plan_id){
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(ADD_RECIPE_TO_PLAN,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setInt(1, recipe_id);
            insertStm.setString(2, meal_name);
            insertStm.setInt(3, display_order);
            insertStm.setInt(4, day_name_id);
            insertStm.setInt(5, plan_id);
            int result = insertStm.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    return;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getRecipeId(String name){
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_RECIPE_ID)
        ) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}