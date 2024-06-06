/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validacoes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class validacoes {
// validação da data para os dias atuais

    public static boolean validarData(String dataValidacao) {
        if (dataValidacao == null || dataValidacao.isEmpty()) {
            return false;
        }
        if (!dataValidacao.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false;
        }
        try {

            DateTimeFormatter estilo = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            estilo.withResolverStyle(ResolverStyle.STRICT);
            DateTimeFormatter estiloSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate diaAtual = LocalDate.now();
            LocalDate data = LocalDate.parse(dataValidacao, estilo);
            String dataSQL = data.format(estiloSQL);
            LocalDate diaInput = LocalDate.parse(dataSQL, estiloSQL);

            if (!diaInput.isBefore(diaAtual)) {
                return false;
            }
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }
    }
// converte a data para o formato SQL
    
    public static String converterSQL(String databr){
        
        String dia = databr.substring(0, 2);
        String mes = databr.substring(3, 5);
        String ano = databr.substring(6, 10);
        
        try{
            String dataSQL = ano+"-"+mes+"-"+dia;
            return dataSQL;
        }catch(Exception e){
            return null;
        }
        
        
        
    }

    // converter a data para o formato Brasil
    public static String converterDataBrasil(String dataSQL) {
        DateTimeFormatter estilo = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter estiloSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate dataBrasil = LocalDate.parse(dataSQL, estiloSQL);
            return dataBrasil.format(estilo);

        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
    
}
