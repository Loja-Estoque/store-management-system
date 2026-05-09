/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.time.LocalDateTime;

/**
 *
 * @author W10
 */
public class Util {
    private static LocalDateTime dataAtual = LocalDateTime.now();

    public static LocalDateTime getAgora() {
        return dataAtual;
    }

    public static void avancarDias(int dias) {
        dataAtual = dataAtual.plusDays(dias);
    }

    public static void setData(LocalDateTime novaData) {
        dataAtual = novaData;
    }
}
