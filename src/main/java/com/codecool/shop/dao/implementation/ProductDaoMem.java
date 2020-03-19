package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.driver.ConnToDB;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProductDaoMem implements ProductDao {

    private List<Product> data = new ArrayList<>();
    private static ProductDaoMem instance = null;
    private static Connection cursor = ConnToDB.getDb();
    private ProductCategoryDaoMem productCategoryDaoMem = ProductCategoryDaoMem.getInstance();
    private SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem() {
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }


    @Override
    public void add(Product product) {
        try {
            PreparedStatement prepAdd = cursor.prepareStatement("INSERT INTO product" +
                            "(name, product_category_id, supplier_id, default_price, currency_string, description) VALUES  (?,?,?,?,?,?)",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setString(1, product.getName());
            prepAdd.setInt(2, product.getProductCategory().getId());
            prepAdd.setInt(3, product.getSupplier().getId());
            prepAdd.setFloat(4, product.getDefaultPrice());
            prepAdd.setString(5, product.getDefaultCurrency());
            prepAdd.setString(6, product.getDescription());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        try{
            PreparedStatement find = cursor.prepareStatement("SELECT * FROM product" +
                    " WHERE id = (?)");
            find.setInt(1, id);
            find.execute();
            ResultSet resultSet = find.getResultSet();
            return new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("name"), resultSet.getInt("default_price"),
                    resultSet.getString("currency_string"),
                    resultSet.getString("description"),
                    productCategoryDaoMem.find(resultSet.getInt("product_category_id")),
                    supplierDaoMem.find(resultSet.getInt("supplier_id")));

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try{
            PreparedStatement remove = cursor.prepareStatement(
                    "DELETE FROM product WHERE id = (?)");
            remove.setInt(1, id);
            remove.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        try {
            ArrayList<Product> result = new ArrayList<>();
            PreparedStatement find = cursor.prepareStatement("SELECT * FROM product");
            find.execute();
            ResultSet resultSet = find.getResultSet();
            while (resultSet.next()) {
                result.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("description"),
                        resultSet.getString("currency_string"),
                        productCategoryDaoMem.find(resultSet.getInt("product_category_id")),
                        supplierDaoMem.find(resultSet.getInt("supplier_id"))));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        try {
            ArrayList<Product> result = new ArrayList<>();
            PreparedStatement getBySupp = cursor.prepareStatement("SELECT * FROM product WHERE supplier_id = (?)");
            getBySupp.setInt(1,supplier.getId());
            ResultSet resultSet = getBySupp.getResultSet();
            while (resultSet.next()) {
                result.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("description"),
                        resultSet.getString("currency_string"),
                        productCategoryDaoMem.find(resultSet.getInt("product_category_id")),
                        supplierDaoMem.find(resultSet.getInt("supplier_id"))));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        try {
            ArrayList<Product> result = new ArrayList<>();
            PreparedStatement getByCat = cursor.prepareStatement("SELECT * FROM product WHERE product_category_id = (?)");
            getByCat.setInt(1,productCategory.getId());
            ResultSet resultSet = getByCat.getResultSet();
            while (resultSet.next()) {
                result.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("description"),
                        resultSet.getString("currency_string"),
                        productCategoryDaoMem.find(resultSet.getInt("product_category_id")),
                        supplierDaoMem.find(resultSet.getInt("supplier_id"))));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
