package com.example.java.model;

import com.example.java.constant.SqlConstant;
import com.example.java.entity.CategoryStatus;
import com.example.java.util.ConnectionHelper;
import org.jboss.weld.environment.logging.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MySqlCategory implements CategoryModel {


    @Override
    public boolean save(Category category) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.CATEGORY_INSERT);
            preparedStatement.setString(1,category.getName());
            preparedStatement.setString(2,category.getCreatedAt().toString());
            preparedStatement.setString(3,category.getUpdatedAt().toString());
            preparedStatement.setInt(4,category.getCreatedBy());
            preparedStatement.setInt(5,category.getUpdatedBy());
            preparedStatement.setInt(6,category.getStatus().getValue());
            return preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(int id, Category category) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.CATEGORY_UPDATE);
            preparedStatement.setString(1,category.getName());
            preparedStatement.setString(2,category.getCreatedAt().toString());
            preparedStatement.setString(3,category.getUpdatedAt().toString());
            preparedStatement.setInt(4,category.getCreatedBy());
            preparedStatement.setInt(5,category.getUpdatedBy());
            preparedStatement.setInt(6,category.getStatus().getValue());
            preparedStatement.setInt(7,id);
            return preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.CATEGORY_DELETE);
            preparedStatement.setInt(1, CategoryStatus.DELETED.getValue());
            return preparedStatement.executeUpdate() > 0;

        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public List<Category> findByAll() {
        List<Category> categories = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.CATEGORY_FIND_ALL);
            preparedStatement.setInt(1, CategoryStatus.ACTIVE.getValue());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = Integer.parseInt(rs.getString(SqlConstant.CATEGORY_FIELD_ID));
                String name = rs.getString(SqlConstant.CATEGORY_FIELD_NAME);
                LocalDateTime createdAt = rs.getTimestamp(SqlConstant.CATEGORY_FIELD_CREATED_AT).toLocalDateTime();
                LocalDateTime updatedAt = rs.getTimestamp(SqlConstant.CATEGORY_FIELD_UPDATED_AT).toLocalDateTime();
                LocalDateTime deletedAt = null;
                if(rs.getTimestamp(SqlConstant.CATEGORY_FIELD_DELETED_AT) != null) {
                    deletedAt = rs.getTimestamp(SqlConstant.CATEGORY_FIELD_DELETED_AT).toLocalDateTime();
                }
                int createdBy = rs.getInt(SqlConstant.CATEGORY_FIELD_CREATED_BY);
                int updatedBy = rs.getInt(SqlConstant.CATEGORY_FIELD_UPDATED_BY);
                int deletedBy = rs.getInt(SqlConstant.CATEGORY_FIELD_DELETED_BY);
                CategoryStatus status = CategoryStatus.of(rs.getInt("status"));
                Category category = new Category(id, name, createdAt, updatedAt, deletedAt, createdBy, updatedBy, deletedBy, status);
                return categories;
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return categories;
    }

    @Override
    public Category findById(int id) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.CATEGORY_FIND_BY_ID);
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2, CategoryStatus.ACTIVE.getValue());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();
                LocalDateTime updatedAt = rs.getTimestamp("updatedAt").toLocalDateTime();
                LocalDateTime deletedAt = null;
                if (rs.getTimestamp("deletedAt") != null) {
                    deletedAt = rs.getTimestamp("deletedAt").toLocalDateTime();
                }
                int createdBy = rs.getInt("createdBy");
                int updatedBy = rs.getInt("updatedBy");
                int deletedBy = rs.getInt("deletedBy");
                CategoryStatus categoryStatus = CategoryStatus.values()[rs.getInt("status")];
                Category category = new Category(id ,name,createdAt,updatedAt,deletedAt,createdBy,updatedBy,deletedBy,categoryStatus);
                return category;
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
}
