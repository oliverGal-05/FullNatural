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

public class SupplierDaoMem implements SupplierDao {

    private List<Supplier> data = new ArrayList<>();
    private static SupplierDaoMem instance = null;
    private static Connection cursor = ConnToDB.getDb();

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem() {
    }

    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {

        try {
            PreparedStatement add = cursor.prepareStatement(
                    "INSERT INTO supplier (name, description) VALUES (?, ?)",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            add.setString(1, supplier.getName());
            add.setString(2,supplier.getDescription());
            add.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {
        try {
            PreparedStatement find = cursor.prepareStatement(
                    "SELECT * FROM supplier WHERE id = (?)");
            find.setInt(1, id);
            find.execute();
            ResultSet resultSet = find.getResultSet();
            resultSet.next();
            return new Supplier(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getString("description"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement remove = cursor.prepareStatement(
                    "DELETE FROM supplier WHERE id = (?)");
                remove.setInt(1, id);
                remove.executeUpdate();
        } catch (SQLException e){
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
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description")));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
