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
        productDataStore.add(new Product("Scitec 100% Whey Protein Professional 920g", 6800, "HUF", "This product's combination of Whey Concentrate and Whey Isolate provides you with a balanced protein blend, with added digestive enzymes and Amino Acids, to improve...", protein, scitec));
        productDataStore.add(new Product("Biotech USA Iso Whey Zero 2.27Kg", 18000, "HUF", "Premium quality sugar-free*, Lactose-free* and Gluten-free whey protein isolate with native whey isolate basic ingredient, added BCAA and Glutamine amino acids.", protein, biotech));
        productDataStore.add(new Product("MHP Super Premium Whey Protein 920g", 7300, "HUF", "Super Premium Whey Protein + is an ultra premium formula driven by a superior absorbing hydrolyzed whey protein and anti-catabolic branched-chain keto acids (BCKAs)", protein, mhp));

        productDataStore.add(new Product("Scitec Micronized Mega Creatine 150 capsules", 5500,"HUF", "yesyes guuud", creatine, scitec));
        productDataStore.add(new Product("Biotech 100% Micronized Creatine Monohydrate 300g", 3500,"HUF", "even guud eeeerrr", creatine, biotech));
        productDataStore.add(new Product("MHP Pure Micronized Creatine Monohydrate 300g", 3700, "HUF", "is it gud enough?", creatine, biotech));

        productDataStore.add(new Product("Scitec Big Bang Pre-Workout Stimulant", 5800, "HUF", "flavor: mango", preWorkout, scitec));
        productDataStore.add(new Product("Biotech USA Concentrated Pre-Workout Formula", 5400, "HUF", "flavor: droid", preWorkout, biotech));
        productDataStore.add(new Product("MHP Max Pre-Workout 300g alazat", 5500, "HUF", "flavor: valami, Energize, Pump, Perform", preWorkout, mhp));
    }
}
