/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.advice;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.service.FlooringMasteryAuditDao;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author rianu
 */
public class LoggingAdvice {
    
    FlooringMasteryAuditDao auditDao;
    
    public LoggingAdvice(FlooringMasteryAuditDao auditDao) {
        this.auditDao = auditDao;
    }
    
     public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringMasteryPersistenceException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
    public void logException (Exception ex) throws Throwable {
        auditDao.writeAuditEntry(ex.toString());
    }
}
