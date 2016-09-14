/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.vue;

import alexandre.evalcomp.metier.modele.Formation;
import alexandre.evalcomp.metier.service.ServiceMetier;
import alexandre.evalcomp.metier.service.ServiceTechnique;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Alex-Laptop
 */
public class MainTest {
    
    public static void main(String[] args) throws Throwable
    {
        ServiceTechnique servT = new ServiceTechnique();
        ServiceMetier servM = new ServiceMetier();
        
        Formation f = servM.trouverFormationParId("8925");
        
        File fi = new File("C:\\Users\\alexa\\Desktop\\test\\test.xls");
        
        HSSFWorkbook wb = servT.exporterResultats(f);
        
        FileOutputStream fos = new FileOutputStream(fi);
        
        wb.write(fos);
        
        fos.flush();
        
        fos.close();
    }
}