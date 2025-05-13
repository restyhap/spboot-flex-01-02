package top.resty.spboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.resty.spboot.dto.QcReportsDTO;
import top.resty.spboot.entity.Quotation;
import top.resty.spboot.service.PdfExportService;
import top.resty.spboot.service.QcReportsService;
import top.resty.spboot.service.QuotationService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * PDF Export Controller for QC Reports and Quotations
 *
 * @author devin-ai
 * @since 2025-05-13
 */
@RestController
@RequestMapping("/pdf")
public class PdfExportController {

    @Autowired
    private PdfExportService pdfExportService;

    @Autowired
    private QcReportsService qcReportsService;

    @Autowired
    private QuotationService quotationService;

    /**
     * Export QC Report as PDF
     *
     * @param id QC Report ID
     * @return PDF file as response
     */
    @GetMapping("/qcreport/{id}")
    public ResponseEntity<byte[]> exportQcReportPdf(@PathVariable String id) {
        try {
            QcReportsDTO qcReportDTO = qcReportsService.getQcReportDTOById(id);
            if (qcReportDTO == null) {
                return ResponseEntity.notFound().build();
            }

            byte[] pdfBytes = pdfExportService.generateQcReportPdf(qcReportDTO);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            
            String filename = "QC_Report";
            if (qcReportDTO.getQcReports() != null && qcReportDTO.getQcReports().getModelCode() != null) {
                filename += "_" + qcReportDTO.getQcReports().getModelCode();
            }
            filename += "_" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".pdf";
            
            headers.setContentDispositionFormData("attachment", filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Export Quotation as PDF
     *
     * @param id Quotation ID
     * @return PDF file as response
     */
    @GetMapping("/quotation/{id}")
    public ResponseEntity<byte[]> exportQuotationPdf(@PathVariable String id) {
        try {
            Quotation quotation = quotationService.getById(id);
            if (quotation == null) {
                return ResponseEntity.notFound().build();
            }

            byte[] pdfBytes = pdfExportService.generateQuotationPdf(quotation);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            
            String filename = "Quotation";
            if (quotation.getSupplierItemCode() != null) {
                filename += "_" + quotation.getSupplierItemCode();
            }
            filename += "_" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".pdf";
            
            headers.setContentDispositionFormData("attachment", filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
