/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.time.LocalDate;
import java.time.LocalDateTime;

import DAO.PedidoDAO;

/**
 *
 * @author W10
 */
public class Util {
    private static LocalDateTime dataAtual = LocalDateTime.now();
    
    PedidoDAO pedidoDao = new PedidoDAO();


    public static LocalDateTime getAgora() {
        return dataAtual;
    }
    

    public static void avancarDias(int dias) {
        dataAtual = dataAtual.plusDays(dias);
    }

    public static void setData(LocalDateTime novaData) {
        dataAtual = novaData;
    }
    
    public static boolean isMesmoDia(LocalDateTime data1, LocalDateTime data2) {
        return data1.toLocalDate().isEqual(data2.toLocalDate());
    }

    public static boolean isMesmoMes(LocalDateTime data1, LocalDateTime data2) {
        return data1.getYear() == data2.getYear() && data1.getMonth() == data2.getMonth();
    }

    public static boolean isMesmoAno(LocalDateTime data1, LocalDateTime data2) {
        return data1.getYear() == data2.getYear();
    }
}
