package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier scitec = new Supplier("Scitec Nutrition", "Nutritional Supplements");
        supplierDataStore.add(scitec);
        Supplier biotech = new Supplier("Biotech USA", "Nutritional Supplements");
        supplierDataStore.add(biotech);
        Supplier mhp = new Supplier("MHP", "Nutritional Supplement");
        supplierDataStore.add(mhp);

        //setting up a new product category
        ProductCategory protein = new ProductCategory("Protein powder", "Supplement", "yes");
        productCategoryDataStore.add(protein);
        ProductCategory creatine = new ProductCategory("Creatine", "Supplement", "no");
        productCategoryDataStore.add(creatine);
        ProductCategory preWorkout = new ProductCategory("Pre-Workout", "Supplement", "yeah");
        productCategoryDataStore.add(preWorkout);

        //setting up products and printing it
        productDataStore.add(new Product("Scitec 100% Whey Protein Professional 920g", 6800, "HUF", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", protein, scitec));
        productDataStore.add(new Product("Biotech USA Iso Whey Zero 1000g", 7000, "HUF", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", protein, biotech));
        productDataStore.add(new Product("MHP Super Premium Whey Protein 920g", 7300, "HUF", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", protein, mhp));

        productDataStore.add(new Product("Scitec Micronized Mega Creatine 150 capsules", 5500,"HUF", "yesyes guuud", creatine, scitec));
        productDataStore.add(new Product("Biotech 100% Micronized Creatine Monohydrate 300g", 3500,"HUF", "even guud eeeerrr", creatine, biotech));
        productDataStore.add(new Product("MHP Pure Micronized Creatine Monohydrate 300g", 3700, "HUF", "is it gud enough?", creatine, biotech));

        productDataStore.add(new Product("Scitec Big Bang Pre-Workout Stimulant", 5800, "HUF", "flavor: mango", preWorkout, scitec));
        productDataStore.add(new Product("Biotech USA Concentrated Pre-Workout Formula", 5400, "HUF", "flavor: droid", preWorkout, biotech));
        productDataStore.add(new Product("MHP Max Pre-Workout 300g alazat", 5500, "HUF", "flavor: valami, Energize, Pump, Perform", preWorkout, mhp));
    }
}
