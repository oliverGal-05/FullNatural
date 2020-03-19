package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.driver.ConnToDB;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoDB implements SupplierDao {

    private List<Supplier> data = new ArrayList<>();
    private static SupplierDaoDB instance = null;
    private Connection cursor = ConnToDB.getDb();


    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoDB() {
    }

    public static SupplierDaoDB getInstance() {
        if (instance == null) {
            instance = new SupplierDaoDB();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        try {
            PreparedStatement selectMaxID = cursor.prepareStatement("SELECT MAX(id) AS MaxId FROM supplier");
            selectMaxID.execute();
            int maxId = selectMaxID.getResultSet().getInt(1);
            PreparedStatement add = cursor.prepareStatement("INSERT INTO supplier(id, name, description) VALUES(?,?,?)");
            add.setInt(1, maxId + 1);
            add.setString(2, supplier.getName());
            add.setString(3, supplier.getDescription());
            add.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement delete = cursor.prepareStatement("DELETE FROM supplier WHERE id = ?");
            delete.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Supplier> getAll() {

        try {
            ArrayList<Supplier> result = new ArrayList<>();
            PreparedStatement find = cursor.prepareStatement("SELECT * FROM supplier");
            find.execute();
            ResultSet resultSet = find.getResultSet();
            while (resultSet.next()) {
                result.add(new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description")));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Never used
    @Override
    public Supplier find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }
}