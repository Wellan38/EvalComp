/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.metier.service;

import alexandre.evalcomp.metier.modele.Apprenant;
import alexandre.evalcomp.metier.modele.AutoEvaluation;
import alexandre.evalcomp.metier.modele.CompetenceG;
import alexandre.evalcomp.metier.modele.CompetenceS;
import alexandre.evalcomp.metier.modele.Formation;
import alexandre.evalcomp.metier.modele.Grade;
import alexandre.evalcomp.metier.modele.Score;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

/**
 *
 * @author Alex-Laptop
 */
public class ServiceTechnique
{
    public HSSFWorkbook exporterAutoevaluations(Apprenant a) throws FileNotFoundException, IOException, Throwable
    {        
        ServiceMetier servM = new ServiceMetier();
        
        HSSFWorkbook wb = new HSSFWorkbook();
        
        List<CompetenceG> compG = a.getFormation().getCompetences();
        
        for (CompetenceG cg : compG)
        {
            HSSFSheet sheet = wb.createSheet(cg.getLibelle());
            
            List<CompetenceS> compSpec = cg.getCompSpec();
            
            List<AutoEvaluation> evals = servM.listerAutoevaluationsParCompetenceG(a, cg);
            
            HSSFRow title = sheet.createRow(0);
            Cell title_cell = title.createCell(0);
            CellStyle style = wb.createCellStyle();
            Font font = wb.createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short)12);
            style.setFont(font);
            style.setAlignment(CellStyle.ALIGN_CENTER);
            style.setBorderTop(CellStyle.BORDER_MEDIUM);
            style.setBorderLeft(CellStyle.BORDER_MEDIUM);
            style.setBorderBottom(CellStyle.BORDER_MEDIUM);
            style.setBorderRight(CellStyle.BORDER_MEDIUM);
            
            title_cell.setCellStyle(style);          
            
            title_cell.setCellValue("Compétence spécifique");
            
            title_cell = title.createCell(1);
            title_cell.setCellStyle(style);
            title_cell.setCellValue("Valeur");
            
            CellStyle style_content = wb.createCellStyle();
            style_content.setBorderTop(CellStyle.BORDER_THIN);
            style_content.setBorderLeft(CellStyle.BORDER_THIN);
            style_content.setBorderBottom(CellStyle.BORDER_THIN);
            style_content.setBorderRight(CellStyle.BORDER_THIN);
            
            for (int i = 0; i < compSpec.size(); i++)
            {
                CompetenceS cs = compSpec.get(i);
                AutoEvaluation e = null;
                
                for (AutoEvaluation ev : evals)
                {
                    if (ev.getCompetence().equals(cs))
                    {
                        e = ev;
                        break;
                    }
                }
                
                HSSFRow row = sheet.createRow(i+1);
                
                Cell cell = row.createCell(0);
                cell.setCellStyle(style_content);
                cell.setCellValue(cs.getLibelle());
                
                cell = row.createCell(1);
                cell.setCellStyle(style_content);
                
                if (e != null)
                {
                    cell.setCellValue(e.getValeur());
                }
            }
            
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
        }
        
        return wb;
    }
    
    public HSSFWorkbook exporterResultats(Apprenant a) throws FileNotFoundException, IOException, Throwable
    {        
        ServiceMetier servM = new ServiceMetier();
        
        HSSFWorkbook wb = new HSSFWorkbook();
        
        List<CompetenceG> compG = a.getFormation().getCompetences();
        
        HSSFSheet sheet = wb.createSheet("Scores");
        
        int rowCount = 0;
        
        for (int i = 0; i < compG.size(); i++)
        {
            CompetenceG cg = compG.get(i);
            
            if (i > 0)
            {
                sheet.createRow(rowCount++);
            }
            
            CellStyle cs_comp_gen = wb.createCellStyle();
            Font font_comp_gen = wb.createFont();
            font_comp_gen.setBold(true);
            font_comp_gen.setFontHeightInPoints((short)12);
            cs_comp_gen.setFont(font_comp_gen);
            
            HSSFRow comp_gen = sheet.createRow(rowCount++);
            Cell comp_cell = comp_gen.createCell(0);
            comp_cell.setCellValue(cg.getLibelle() + " :");
            comp_cell.setCellStyle(cs_comp_gen);
            
            sheet.createRow(rowCount++);
            
            List<CompetenceS> compSpec = cg.getCompSpec();
            
            List<Score> scores = servM.listerScoresParCompetenceG(a, cg);
            
            HSSFRow title = sheet.createRow(rowCount++);
            Cell title_cell = title.createCell(0);
            CellStyle style = wb.createCellStyle();
            Font font = wb.createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short)12);
            style.setFont(font);
            style.setAlignment(CellStyle.ALIGN_CENTER);
            style.setBorderTop(CellStyle.BORDER_MEDIUM);
            style.setBorderLeft(CellStyle.BORDER_MEDIUM);
            style.setBorderBottom(CellStyle.BORDER_MEDIUM);
            style.setBorderRight(CellStyle.BORDER_MEDIUM);
            
            title_cell.setCellStyle(style);          
            
            title_cell.setCellValue("Compétence spécifique");
            
            title_cell = title.createCell(1);
            title_cell.setCellStyle(style);
            title_cell.setCellValue("Score");
            
            CellStyle style_content = wb.createCellStyle();
            style_content.setBorderTop(CellStyle.BORDER_THIN);
            style_content.setBorderLeft(CellStyle.BORDER_THIN);
            style_content.setBorderBottom(CellStyle.BORDER_THIN);
            style_content.setBorderRight(CellStyle.BORDER_THIN);
            
            for (int j = 0; j < compSpec.size(); j++)
            {
                CompetenceS cs = compSpec.get(j);
                Score s = null;
                
                for (Score sc : scores)
                {
                    if (sc.getCompetence().equals(cs))
                    {
                        s = sc;
                        break;
                    }
                }
                
                HSSFRow row = sheet.createRow(rowCount++);
                
                Cell cell = row.createCell(0);
                cell.setCellStyle(style_content);
                cell.setCellValue(cs.getLibelle());
                
                cell = row.createCell(1);
                cell.setCellStyle(style_content);
                
                if (s != null)
                {
                    cell.setCellValue(s.getScore());
                }
            }
            
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
        }
        
        sheet = wb.createSheet("Grades");
        rowCount = 0;
        
        List<Grade> grades = servM.listerGradesParApprenant(a);
        
        HSSFRow title = sheet.createRow(rowCount++);
        Cell title_cell = title.createCell(0);
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short)12);
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setBorderTop(CellStyle.BORDER_MEDIUM);
        style.setBorderLeft(CellStyle.BORDER_MEDIUM);
        style.setBorderBottom(CellStyle.BORDER_MEDIUM);
        style.setBorderRight(CellStyle.BORDER_MEDIUM);

        title_cell.setCellStyle(style);          

        title_cell.setCellValue("Compétence générale");

        title_cell = title.createCell(1);
        title_cell.setCellStyle(style);
        title_cell.setCellValue("Grade");
        
        for (CompetenceG cg : compG)
        {
            
            
            CellStyle style_content = wb.createCellStyle();
            style_content.setBorderTop(CellStyle.BORDER_THIN);
            style_content.setBorderLeft(CellStyle.BORDER_THIN);
            style_content.setBorderBottom(CellStyle.BORDER_THIN);
            style_content.setBorderRight(CellStyle.BORDER_THIN);
            
            Grade g = null;
            
            for (Grade gr : grades)
            {
                if (gr.getCompetence().equals(cg))
                {
                    g = gr;
                    break;
                }
            }
            
            HSSFRow row = sheet.createRow(rowCount++);

            Cell cell = row.createCell(0);
            cell.setCellStyle(style_content);
            cell.setCellValue(cg.getLibelle());

            cell = row.createCell(1);
            cell.setCellStyle(style_content);

            cell = row.createCell(1);
            cell.setCellStyle(style_content);

            if (g != null)
            {
                cell.setCellValue(g.getGrade().toString());
            }
            
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
        }
        
        return wb;
    }
    
    public HSSFWorkbook exporterResultats(CompetenceG cg) throws Throwable
    {
        ServiceMetier servM = new ServiceMetier();
        
        HSSFWorkbook wb = new HSSFWorkbook();
        
        HSSFSheet sheet = wb.createSheet("Scores par apprenant");
        
        int rowCount = 0;
        
        List<CompetenceS> compSpec = cg.getCompSpec();
        
        List<Formation> formations = servM.listerFormations();
        
        Formation f = null;
        
        for (Formation fo : formations)
        {
            List<CompetenceG> compg = fo.getCompetences();
            
            for (CompetenceG comp_gen : compg)
            {
                if (comp_gen.equals(cg))
                {
                    f = fo;
                    break;
                }
            }
        }
        
        CellStyle cs_comp_gen = wb.createCellStyle();
        Font font_comp_gen = wb.createFont();
        font_comp_gen.setBold(true);
        font_comp_gen.setFontHeightInPoints((short)12);
        cs_comp_gen.setFont(font_comp_gen);
        
        List<Apprenant> apprenants = servM.listerApprenantsParFormation(f);
        
        for (int i = 0; i < apprenants.size(); i++)
        {
            Apprenant a = apprenants.get(i);
            
            if (i > 0)
            {
                sheet.createRow(rowCount++);
            }
            
            HSSFRow comp_gen = sheet.createRow(rowCount++);
            Cell comp_cell = comp_gen.createCell(0);
            comp_cell.setCellValue(a.getNom() + " :");
            comp_cell.setCellStyle(cs_comp_gen);
            
            sheet.createRow(rowCount++);
            
            HSSFRow title = sheet.createRow(rowCount++);
            Cell title_cell = title.createCell(0);
            CellStyle style = wb.createCellStyle();
            Font font = wb.createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short)12);
            style.setFont(font);
            style.setAlignment(CellStyle.ALIGN_CENTER);
            style.setBorderTop(CellStyle.BORDER_MEDIUM);
            style.setBorderLeft(CellStyle.BORDER_MEDIUM);
            style.setBorderBottom(CellStyle.BORDER_MEDIUM);
            style.setBorderRight(CellStyle.BORDER_MEDIUM);
            
            title_cell.setCellStyle(style);          
            
            title_cell.setCellValue("Compétence spécifique");
            
            title_cell = title.createCell(1);
            title_cell.setCellStyle(style);
            title_cell.setCellValue("Score");
            
            CellStyle style_content = wb.createCellStyle();
            style_content.setBorderTop(CellStyle.BORDER_THIN);
            style_content.setBorderLeft(CellStyle.BORDER_THIN);
            style_content.setBorderBottom(CellStyle.BORDER_THIN);
            style_content.setBorderRight(CellStyle.BORDER_THIN);
            
            List<Score> scores = servM.historiqueScoresParCompetenceG(a, cg);
            
            for (CompetenceS cs : compSpec)
            {
                Score s = null;
                
                for (Score sc : scores)
                {
                    if (sc.getCompetence().equals(cs))
                    {
                        s = sc;
                        break;
                    }
                }
                
                HSSFRow row = sheet.createRow(rowCount++);
                
                Cell cell = row.createCell(0);
                cell.setCellStyle(style_content);
                cell.setCellValue(cs.getLibelle());
                
                cell = row.createCell(1);
                cell.setCellStyle(style_content);
                
                if (s != null)
                {
                    cell.setCellValue(s.getScore());
                }
            }
        }
        
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        
        sheet = wb.createSheet("Scores par compétence spécifique");
        
        rowCount = 0;
        
        for (int i = 0; i < compSpec.size(); i++)
        {
            CompetenceS cs = compSpec.get(i);
            
            if (i > 0)
            {
                sheet.createRow(rowCount++);
            }
            
            HSSFRow comp_gen = sheet.createRow(rowCount++);
            Cell comp_cell = comp_gen.createCell(0);
            comp_cell.setCellValue(cs.getLibelle() + " :");
            comp_cell.setCellStyle(cs_comp_gen);
            
            sheet.createRow(rowCount++);
            
            HSSFRow title = sheet.createRow(rowCount++);
            Cell title_cell = title.createCell(0);
            CellStyle style = wb.createCellStyle();
            Font font = wb.createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short)12);
            style.setFont(font);
            style.setAlignment(CellStyle.ALIGN_CENTER);
            style.setBorderTop(CellStyle.BORDER_MEDIUM);
            style.setBorderLeft(CellStyle.BORDER_MEDIUM);
            style.setBorderBottom(CellStyle.BORDER_MEDIUM);
            style.setBorderRight(CellStyle.BORDER_MEDIUM);
            
            title_cell.setCellStyle(style);          
            
            title_cell.setCellValue("Apprenant");
            
            title_cell = title.createCell(1);
            title_cell.setCellStyle(style);
            title_cell.setCellValue("Score");
            
            CellStyle style_content = wb.createCellStyle();
            style_content.setBorderTop(CellStyle.BORDER_THIN);
            style_content.setBorderLeft(CellStyle.BORDER_THIN);
            style_content.setBorderBottom(CellStyle.BORDER_THIN);
            style_content.setBorderRight(CellStyle.BORDER_THIN);
            
            for (Apprenant a : apprenants)
            {
                List<Score> scores = servM.listerScoresParCompetenceG(a, cg);
                
                Score s = null;
                
                for (Score sc : scores)
                {
                    if (sc.getCompetence().equals(cs))
                    {
                        s = sc;
                        break;
                    }
                }
                
                HSSFRow row = sheet.createRow(rowCount++);
                
                Cell cell = row.createCell(0);
                cell.setCellStyle(style_content);
                cell.setCellValue(a.getNom());
                
                cell = row.createCell(1);
                cell.setCellStyle(style_content);
                
                if (s != null)
                {
                    cell.setCellValue(s.getScore());
                }
            }
        }
        
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        
        return wb;
    }
    
    public HSSFWorkbook exporterResultats(Formation f) throws Throwable
    {
        ServiceMetier servM = new ServiceMetier();
        
        HSSFWorkbook wb = new HSSFWorkbook();
        
        List<CompetenceG> compG = f.getCompetences();
        
        List<Apprenant> apprenants = servM.listerApprenantsParFormation(f);
        
        CellStyle cs_comp_gen = wb.createCellStyle();
        Font font_comp_gen = wb.createFont();
        font_comp_gen.setBold(true);
        font_comp_gen.setFontHeightInPoints((short)12);
        cs_comp_gen.setFont(font_comp_gen);
        
        for (CompetenceG cg : compG)
        {
            HSSFSheet sheet = wb.createSheet(cg.getLibelle());

            int rowCount = 0;
            
            List<CompetenceS> compSpec = cg.getCompSpec();
            
            for (int i = 0; i < compSpec.size(); i++)
            {
                CompetenceS cs = compSpec.get(i);

                if (i > 0)
                {
                    sheet.createRow(rowCount++);
                }

                HSSFRow comp_gen = sheet.createRow(rowCount++);
                Cell comp_cell = comp_gen.createCell(0);
                comp_cell.setCellValue(cs.getLibelle() + " :");
                comp_cell.setCellStyle(cs_comp_gen);

                sheet.createRow(rowCount++);

                HSSFRow title = sheet.createRow(rowCount++);
                Cell title_cell = title.createCell(0);
                CellStyle style = wb.createCellStyle();
                Font font = wb.createFont();
                font.setBold(true);
                font.setFontHeightInPoints((short)12);
                style.setFont(font);
                style.setAlignment(CellStyle.ALIGN_CENTER);
                style.setBorderTop(CellStyle.BORDER_MEDIUM);
                style.setBorderLeft(CellStyle.BORDER_MEDIUM);
                style.setBorderBottom(CellStyle.BORDER_MEDIUM);
                style.setBorderRight(CellStyle.BORDER_MEDIUM);

                title_cell.setCellStyle(style);          

                title_cell.setCellValue("Apprenant");

                title_cell = title.createCell(1);
                title_cell.setCellStyle(style);
                title_cell.setCellValue("Score");

                CellStyle style_content = wb.createCellStyle();
                style_content.setBorderTop(CellStyle.BORDER_THIN);
                style_content.setBorderLeft(CellStyle.BORDER_THIN);
                style_content.setBorderBottom(CellStyle.BORDER_THIN);
                style_content.setBorderRight(CellStyle.BORDER_THIN);

                for (Apprenant a : apprenants)
                {
                    List<Score> scores = servM.listerScoresParCompetenceG(a, cg);

                    Score s = null;

                    for (Score sc : scores)
                    {
                        if (sc.getCompetence().equals(cs))
                        {
                            s = sc;
                            break;
                        }
                    }

                    HSSFRow row = sheet.createRow(rowCount++);

                    Cell cell = row.createCell(0);
                    cell.setCellStyle(style_content);
                    cell.setCellValue(a.getNom());

                    cell = row.createCell(1);
                    cell.setCellStyle(style_content);

                    if (s != null)
                    {
                        cell.setCellValue(s.getScore());
                    }
                }
            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
        }
        
        return wb;
    }
}
