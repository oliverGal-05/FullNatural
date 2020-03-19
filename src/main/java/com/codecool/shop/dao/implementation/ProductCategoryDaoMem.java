package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.driver.ConnToDB;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoMem implements ProductCategoryDao {

    private List<ProductCategory> data = new ArrayList<>();
    private static ProductCategoryDaoMem instance = null;
    private static Connection cursor = ConnToDB.getDb();

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoMem() {
    }

    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        try {
            PreparedStatement add = cursor.prepareStatement(
                    "INSERT INTO product_category(name,department,description) VALUES (?,?,?)",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            add.setString(1, category.getName());
            add.setString(2, category.getDepartment());
            add.setString(3, category.getDescription());
            add.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int id) {
        try {
            PreparedStatement find = cursor.prepareStatement(
                    "SELECT * FROM product_category WHERE id = (?)");
            find.setInt(1, id);
            find.execute();
            ResultSet resultSet = find.getResultSet();
            resultSet.next();
            return new ProductCategory(resultSet.getString("name"), resultSet.getString("department"), resultSet.getString("description"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement remove = cursor.prepareStatement(
                    "DELETE FROM product_category WHERE id = (?)");
            remove.setInt(1, id);
            remove.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        try {
            ArrayList<ProductCategory> result = new ArrayList<>();
            PreparedStatement find = cursor.prepareStatement("SELECT * FROM product_category");
            find.execute();
            ResultSet resultSet = find.getResultSet();
            while (resultSet.next()) {
                result.add(new ProductCategory(
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




}
