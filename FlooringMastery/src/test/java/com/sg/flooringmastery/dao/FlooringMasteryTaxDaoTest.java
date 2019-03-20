/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author rianu
 */
public class FlooringMasteryTaxDaoTest {
    
    private FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxDaoFileImpl();
    
    public Tax createTax1() {
        Tax tax = new Tax("OH");
        tax.setTaxRate(new BigDecimal("6.00"));
        return tax;
    }
    
    public Tax createTax2() {
        Tax tax = new Tax("MN");
        tax.setTaxRate(new BigDecimal("10.00"));
        return tax;
    }
    
    public FlooringMasteryTaxDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
         List<Tax> taxList = taxDao.getAllTaxes();
        for (Tax tax : taxList) {
            taxDao.removeTax(tax.getState());
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetAllTaxs() throws Exception {
        Tax tax1 = createTax1();
        taxDao.addTax(tax1.getState(), tax1);
        Tax tax2 = createTax2();
        taxDao.addTax(tax2.getState(), tax2);
        
        assertEquals(2, taxDao.getAllTaxes().size());
        
    }

    /**
     * Test of addItem method, of class VendingMachineDao.
     */
    @Test
    public void testGetAddTax() throws Exception {
        Tax tax1 = createTax1();
        taxDao.addTax(tax1.getState(), tax1);
        Tax fromDao = taxDao.getTax(tax1.getState());
        assertTrue(fromDao.getState().equals("OH"));
        assertTrue(fromDao.getTaxRate().equals((new BigDecimal("6.00"))));
       
    }

    /**
     * Test of removeItem method, of class VendingMachineDao.
     */
    @Test
    public void testRemoveTax() throws Exception {
        Tax tax1 = createTax1();
        taxDao.addTax(tax1.getState(), tax1);
        Tax Tax2 = createTax2();
        taxDao.addTax(Tax2.getState(), Tax2);
        
        taxDao.removeTax(tax1.getState());
        assertEquals(1, taxDao.getAllTaxes().size());
        assertNull(taxDao.getTax(tax1.getState()));
        
        taxDao.removeTax(Tax2.getState());
        assertEquals(0, taxDao.getAllTaxes().size());
        assertNull(taxDao.getTax(Tax2.getState()));
        
    }

    /**
     * Test of editItem method, of class VendingMachineDao.
     */
    @Test
    public void testEditTax() throws Exception {
        Tax tax1 = createTax1();
        taxDao.addTax(tax1.getState(), tax1);
        
        Tax TaxEdit = new Tax("MN");
        TaxEdit.setTaxRate(new BigDecimal("10.00"));
        
        taxDao.editTax(tax1, TaxEdit);
        
        assertTrue(TaxEdit.getState().equals("MN"));
        assertTrue(TaxEdit.getTaxRate().equals((new BigDecimal("10.00"))));
    }

  
}
