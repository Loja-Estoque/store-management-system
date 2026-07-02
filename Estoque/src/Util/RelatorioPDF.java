/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

import DAO.PedidoDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.Pedido;
import model.Usuario;

/**
 *
 * @author rafaella
 */
public class RelatorioPDF {
    

     public void gerarRelatorioPedidos(PedidoDAO pedidoDAO)
            throws DocumentException, IOException {

        Document document = new Document();
        System.out.println("Diretório atual:");
        System.out.println(System.getProperty("user.dir"));

        PdfWriter.getInstance(document,
                new FileOutputStream("reports/RelatorioPedidos.pdf"));

        document.open();

        document.add(new Paragraph("RELATORIO DE PEDIDOS"));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("----------------------------------------"));
        document.add(new Paragraph(" "));

        for (Pedido p : pedidoDAO.getLista()) {

            document.add(new Paragraph("Pedido: " + p.getId()));
            document.add(new Paragraph("Cliente: "
                    + p.getUsuario().getPessoa().getNome()));
            document.add(new Paragraph("Status: " + p.getStatus()));
            document.add(new Paragraph("Valor Total: R$ "
                    + p.getValor_total()));
            document.add(new Paragraph("Forma de Pagamento: "
                    + p.getForma_pagamento()));

            document.add(new Paragraph("----------------------------------------"));
        }

        document.close();
    }
     
    public void gerarRelatorioFaturamento(PedidoDAO pedidoDAO)
        throws DocumentException, IOException {

        Document document = new Document();

        PdfWriter.getInstance(document,
                new FileOutputStream("reports/RelatorioFaturamento.pdf"));

        document.open();

        document.add(new Paragraph("RELATÓRIO DE FATURAMENTO"));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("----------------------------------------"));
        document.add(new Paragraph(" "));

        LocalDateTime agora = Util.getAgora();

        double faturamentoD = pedidoDAO.calcularFaturamentoDiario(agora.toLocalDate());
        double faturamentoM = pedidoDAO.calcularFaturamentoMensal(agora.toLocalDate());
        double faturamentoA = pedidoDAO.calcularFaturamentoAnual(agora.toLocalDate());
        double faturamento = pedidoDAO.calcularFaturamentoTotal();

        document.add(new Paragraph("Data da Consulta: " + agora));
        document.add(new Paragraph(" "));

        document.add(new Paragraph(
                String.format("Faturamento do Dia: R$ %.2f", faturamentoD)));

        document.add(new Paragraph(
                String.format("Faturamento do Mês: R$ %.2f", faturamentoM)));

        document.add(new Paragraph(
                String.format("Faturamento do Ano: R$ %.2f", faturamentoA)));

        document.add(new Paragraph(
                String.format("Faturamento Total: R$ %.2f", faturamento)));

        document.add(new Paragraph(" "));
        document.add(new Paragraph("----------------------------------------"));

        document.close();
    }
    
    public void gerarRelatorioPedidosStatus(PedidoDAO pedidoDAO,String status)
        throws DocumentException, IOException {

        Document document = new Document();

        PdfWriter.getInstance(document,
                new FileOutputStream(
                "reports/Pedidos_" + status + ".pdf"));

        document.open();

        document.add(new Paragraph("RELATÓRIO DE PEDIDOS"));
        document.add(new Paragraph("Status: " + status));
        document.add(new Paragraph(" "));

        for (Pedido p : pedidoDAO.buscarPorStatus(status)) {

            document.add(new Paragraph(
                    "Pedido: " + p.getId()));

            document.add(new Paragraph(
                    "Cliente: "
                    + p.getUsuario().getPessoa().getNome()));

            document.add(new Paragraph(
                    "Valor: R$ "
                    + String.format("%.2f",
                            p.getValor_total())));

            document.add(new Paragraph(
                    "Forma Pagamento: "
                    + p.getForma_pagamento()));

            document.add(new Paragraph("---------------------------"));
        }

        document.close();
    }
    
    public void gerarRelatorioPedidosUsuario( PedidoDAO pedidoDAO, Usuario usuario)
        throws DocumentException, IOException {

        Document document = new Document();

        PdfWriter.getInstance(document,
                new FileOutputStream(
                "reports/MeusPedidosCliente.pdf"));

        document.open();

        document.add(new Paragraph("MEUS PEDIDOS"));
        document.add(new Paragraph("Cliente: "
                + usuario.getPessoa().getNome()));

        document.add(new Paragraph(" "));

        for (Pedido p : pedidoDAO.buscarPorUsuario(usuario)) {

            document.add(new Paragraph(
                    "Pedido: " + p.getId()));

            document.add(new Paragraph(
                    "Status: " + p.getStatus()));

            document.add(new Paragraph(
                    "Valor: R$ "
                    + String.format("%.2f",
                            p.getValor_total())));

            document.add(new Paragraph("-----------------------"));
        }

        document.close();
    }
    
    public void gerarRelatorioVendasPeriodo( PedidoDAO pedidoDAO, LocalDate inicio, LocalDate fim)
        throws DocumentException, IOException {

        Document document = new Document();

        PdfWriter.getInstance(document,
                new FileOutputStream(
                "reports/VendasPeriodo.pdf"));

        document.open();

        document.add(new Paragraph("RELATÓRIO DE VENDAS"));
        document.add(new Paragraph("Período"));
        document.add(new Paragraph(
                inicio + " até " + fim));

        document.add(new Paragraph(" "));

        double total = 0;

        for (Pedido p : pedidoDAO.buscarPorPeriodo(inicio, fim)) {

            document.add(new Paragraph(
                    "Pedido: " + p.getId()));

            document.add(new Paragraph(
                    "Cliente: "
                    + p.getUsuario().getPessoa().getNome()));

            document.add(new Paragraph(
                    "Status: "
                    + p.getStatus()));

            document.add(new Paragraph(
                    "Valor: R$ "
                    + String.format("%.2f",
                            p.getValor_total())));

            document.add(new Paragraph("------------------------"));

            total += p.getValor_total();
        }

        document.add(new Paragraph(" "));
        document.add(new Paragraph(
                "TOTAL DAS VENDAS: R$ "
                + String.format("%.2f", total)));

        document.close();
    }
}
