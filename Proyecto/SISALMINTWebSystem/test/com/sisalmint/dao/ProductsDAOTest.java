/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisalmint.dao;

import com.sisalmint.entity.Products;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Convert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class ProductsDAOTest {

    public ProductsDAOTest() {
    }

    @Test
    public void TestAddProduct() {
        //INSTANCE OF CLASSes
        ProductsDAO pDAO = new ProductsDAO();
        Products miProduct = new Products();

        //SET PROPERTIES
        String newid = "prueba" + String.valueOf(pDAO.getAllProducts().size());
        miProduct.setProductID(newid);
        miProduct.setCategoryID(1);
        miProduct.setProductName("PruebaDonIan");
        miProduct.setRegDate(Date.valueOf("1999-01-01"));
        miProduct.setStatus("INA");//PA Q NO SE LISTE
        miProduct.setUnitPrice(BigDecimal.valueOf(12.20));
        miProduct.setUnitsInStock(5);

        //ADDPRODUCT
        pDAO.addProduct(miProduct);

        //GET THE LAST PRODUCT(IN SHORT WORDS: GET "PruebaDonIan")
        Products DonIan = pDAO.getAllProductsByID(newid);

        //Expected and Result
        String Expected = DonIan.getProductID();
        String Result = newid;

        //assertEquals
        assertEquals(Expected, Result);

    }

    @Test
    public void TestEditProduct() {
        //INSTANCE OF CLASSes
        ProductsDAO pDAO = new ProductsDAO();
        Products miProduct = new Products();

        //GET the last PRODUCT
        int index = pDAO.getAllProducts().size() - 1;
        miProduct = pDAO.getAllProducts().get(index);
        String Result = miProduct.getProductName();

        //Edit Product:"PruebaDonIan"
        miProduct.setProductName("DonLusho");
        pDAO.editProduct(miProduct);
        

        //Refresh object and get Expected
        miProduct=new Products();
        miProduct = pDAO.getAllProducts().get(index);
        String Expected = miProduct.getProductName();

        //assertNotEquals
        assertNotEquals(Expected, Result);
    }

    @Test
    public void TestDeleteProduct() {
        //INSTANCE OF CLASSes
        ProductsDAO pDAO = new ProductsDAO();
        Products miProduct = new Products();
        
        //GET AND REMOVE the FIRST PRODUCT, I declare the var size for allocation the size of the list of products
        int size= pDAO.getProducts().size();
        miProduct = pDAO.getProducts().get(0);
        pDAO.deleteProduct(miProduct);
        
        //Expected and Result
        int Expected=size;
        int Result=pDAO.getProducts().size();
        
        //assertNotEquals
        assertNotEquals(Expected, Result);
    }

    @Test
    public void TestGetProductById() {
        //INSTANCE OF CLASSes
        ProductsDAO pDAO = new ProductsDAO();
        Products miProduct = new Products();
        
        //GET THE ELEMENT PRODUCTID="P1"
        String ID="P1";
        miProduct = pDAO.getProductById(ID);
        
        //Expected and Result
        String Expected=ID;
        String Result=miProduct.getProductID();
        
        //assertEquals
        assertEquals(Expected, Result);
    }

    @Test
    public void TestGetAllProducts() {
        //INSTANCE OF CLASSes
        ProductsDAO pDAO = new ProductsDAO();
        Products miProduct = new Products();
        
        //GET THE SIZE OF THE LIST
        int size=pDAO.getProducts().size();
        
        //EXPECTED AND RESULT 
        int Expected=0;
        int Result=size;
        
        //assertNotEquals
        assertNotEquals(Expected, Result);
    }
}