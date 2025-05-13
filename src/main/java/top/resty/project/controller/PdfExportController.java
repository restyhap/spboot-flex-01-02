package top.resty.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.resty.project.dto.QcReportsDTO;
import top.resty.project.entity.Quotation;
import top.resty.project.service.PdfExportService;
import top.resty.project.service.QcReportsService;
import top.resty.project.service.QuotationService;

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
