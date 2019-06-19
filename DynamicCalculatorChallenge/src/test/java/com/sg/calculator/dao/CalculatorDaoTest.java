/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.calculator.dao;

import com.sg.calculator.dto.Calculation;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author rianu
 */
public class CalculatorDaoTest {

    private CalculatorDao dao;

    public CalculatorDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("calculatorDao", CalculatorDao.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addCalc method, of class CalculatorDao.
     */
    @Test
    public void testAddGetDeleteCalc() {
        Calculation c = new Calculation();
        c.setDate(LocalDate.now());
        c.setResult("5+4=9");
        dao.addCalc(c);
        Calculation fromDao = dao.getCalc(c.getCalculationId());
        assertEquals(c, fromDao);
        dao.deleteCalc(c.getCalculationId());
        assertNull(dao.getCalc(c.getCalculationId()));
    }

    /**
     * Test of getAllCalcs method, of class CalculatorDao.
     */
    @Test
    public void testGetAllCalcs() {
        Calculation c = new Calculation();
        c.setDate(LocalDate.now());
        c.setResult("5+4=9");
        dao.addCalc(c);
        assertEquals(1, dao.getAllCalcs().size());
        
        
    }

    /**
     * Test of calculate method, of class CalculatorDao.
     */
    @Test
    public void testCalculate() {
        String result0 = dao.calculate(4, 5, "+");
        assertTrue(result0.equals("4+5=9.0") == true);
        
        String result1 = dao.calculate(4, 5, "-");
        assertTrue(result1.equals("4-5=-1.0") == true);
        
        String result2 = dao.calculate(4, 5, "/");
        assertTrue(result2.equals("4/5=0.8") == true);
        
        String result3 = dao.calculate(4, 5, "*");
        assertTrue(result3.equals("4*5=20.0") == true);
    }

}
