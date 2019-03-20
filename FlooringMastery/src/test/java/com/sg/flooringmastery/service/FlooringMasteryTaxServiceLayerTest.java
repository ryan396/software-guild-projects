/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author rianu
 */
public class FlooringMasteryTaxServiceLayerTest {
    
    private FlooringMasteryTaxServiceLayer taxService;
    
    public FlooringMasteryTaxServiceLayerTest() {
        /*
        FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxStubDaoImpl();
        FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoStubImpl();
        
        taxService = new FlooringMasteryTaxServiceLayerImpl(taxDao, auditDao);
        */
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        taxService = ctx.getBean("taxService", FlooringMasteryTaxServiceLayer.class);
    }
    
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
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        List<Tax> taxList = taxService.getAllTaxes();
        for (Tax tax : taxList) {
            taxService.removeTax(tax.getState());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllTaxes method, of class FlooringMasteryTaxServiceLayer.
     */
    @Test
    public void testGetAllTaxes() throws Exception {
        Tax tax1 = createTax1();
        taxService.addTax(tax1.getState(), tax1);
        Tax tax2 = createTax2();
        taxService.addTax(tax2.getState(), tax2);
        
        assertEquals(2, taxService.getAllTaxes().size());
    }

    /**
     * Test of addTax method, of class FlooringMasteryTaxServiceLayer.
     */
    @Test
    public void testGetAddTax() throws Exception {
        Tax tax1 = createTax1();
        taxService.addTax(tax1.getState(), tax1);
        Tax fromDao = taxService.getTax(tax1.getState());
        assertEquals(tax1, fromDao);
    }

    /**
     * Test of removeTax method, of class FlooringMasteryTaxServiceLayer.
     */
    @Test
    public void testRemoveTax() throws Exception {
        Tax tax1 = createTax1();
        taxService.addTax(tax1.getState(), tax1);
        Tax tax2 = createTax2();
        taxService.addTax(tax2.getState(), tax2);
        
        taxService.removeTax(tax1.getState());
        assertEquals(1, taxService.getAllTaxes().size());
        assertNull(taxService.getTax(tax1.getState()));
        
        taxService.removeTax(tax2.getState());
        assertEquals(0, taxService.getAllTaxes().size());
        assertNull(taxService.getTax(tax2.getState()));

    }

    /**
     * Test of editTax method, of class FlooringMasteryTaxServiceLayer.
     */
    @Test
    public void testEditTax() throws Exception {
        Tax tax1 = createTax1();
        taxService.addTax(tax1.getState(), tax1);
        
        Tax TaxEdit = new Tax("MN");
        TaxEdit.setTaxRate(new BigDecimal("10.00"));
        
        taxService.editTax(tax1, TaxEdit);
        
        assertTrue(TaxEdit.getState().equals("MN"));
        assertTrue(TaxEdit.getTaxRate().equals((new BigDecimal("10.00"))));
        
    }
    
    @Test
    public void testAddInvalidTax() throws Exception {
        Tax tax1 = createTax1();
        tax1.setTaxRate(new BigDecimal("-1"));
        
        try {
            taxService.addTax(tax1.getState(), tax1);
            fail("Expected FlooringMasteryDataValidationException was not thrown");
        } catch (FlooringMasteryTaxDataValidationException e) {
            return;
        }
    }

    
}
