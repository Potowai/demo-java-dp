package fr.sdv.composite;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestComposite {

    @Test
    public void testHierarchie() {
        Employe e1 = new Employe("Alice", 3000);
        Employe e2 = new Employe("Bob", 2500);
        Employe e3 = new Employe("Charlie", 4000);
        Employe e4 = new Employe("David", 2000);
        Employe e5 = new Employe("Eve", 3500);
        Employe e6 = new Employe("Fred", 2800);

        Service support = new Service("Support");
        support.addElement(e1);
        support.addElement(e2);

        Service dev = new Service("Dev");
        dev.addElement(e3);
        dev.addElement(e4);

        Service direction = new Service("Direction");
        direction.addElement(e5);
        direction.addElement(e6);
        direction.addElement(support);
        direction.addElement(dev);

        assertEquals(5500.0, support.calculerSalaire(), 0.0001);
        assertEquals(6000.0, dev.calculerSalaire(), 0.0001);
        assertEquals(17800.0, direction.calculerSalaire(), 0.0001);
    }

    @Test
    public void testEmployeSeul() {
        Employe e = new Employe("Alice", 3000);
        assertEquals(3000.0, e.calculerSalaire(), 0.0001);
    }

    @Test
    public void testServiceVide() {
        Service s = new Service("Vide");
        assertEquals(0.0, s.calculerSalaire(), 0.0001);
    }
}
