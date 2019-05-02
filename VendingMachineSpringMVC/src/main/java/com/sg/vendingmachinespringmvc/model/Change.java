/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

/**
 *
 * @author rianu
 */
public class Change {
    
    int penny;
    int nickel;
    int dime;
    int quarter;

    public int getPenny() {
        return penny;
    }

    public void setPenny(int penny) {
        this.penny = penny;
    }

    public int getNickel() {
        return nickel;
    }

    public void setNickel(int nickel) {
        this.nickel = nickel;
    }

    public int getDime() {
        return dime;
    }

    public void setDime(int dime) {
        this.dime = dime;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.penny;
        hash = 59 * hash + this.nickel;
        hash = 59 * hash + this.dime;
        hash = 59 * hash + this.quarter;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Change other = (Change) obj;
        if (this.penny != other.penny) {
            return false;
        }
        if (this.nickel != other.nickel) {
            return false;
        }
        if (this.dime != other.dime) {
            return false;
        }
        if (this.quarter != other.quarter) {
            return false;
        }
        return true;
    }
    
    
}
