/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ai.diabdiet.es.data.daftarmenu;

/**
 *
 * @author Erdiansyah
 */
public class DetailMenu {
    private float first;
    private float second;
    private float third;

    public DetailMenu() {
    }

    public DetailMenu(float first, float second, float third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public float getFirst() {
        return first;
    }

    public void setFirst(float first) {
        this.first = first;
    }

    public float getSecond() {
        return second;
    }

    public void setSecond(float second) {
        this.second = second;
    }

    public float getThird() {
        return third;
    }

    public void setThird(float third) {
        this.third = third;
    }
    
    public float getTotalCalories() {
    	return first + second + third;
    }
}
