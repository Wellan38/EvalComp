/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.metier.modele;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Alex-Laptop
 */
public interface Calcul extends Serializable
{
    public abstract Double calcul(Object arg);
}
