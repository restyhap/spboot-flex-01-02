package top.resty.project.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import top.resty.project.config.FilePathConfig;
import top.resty.project.entity.QcReports;
import top.resty.project.entity.Quotation;
import top.resty.project.dto.QcReportsDTO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class PdfExportService {

    @Value("${config.upload-path}")
    private String uploadPath;
    
    @Autowired
    private FilePathConfig filePathConfig;

    /**
     * Generate PDF for QC Report
     * @param qcReport QC Report data
     * @return PDF file as byte array
     */
    public byte[] generateQcReportPdf(QcReportsDTO qcReport) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        
        try {
            Paragraph title = new Paragraph("QC REPORT")
                    .setFontSize(20)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(title);
            
            try {
                String logoPath = uploadPath + "/logo/tc_logo.jpg";
                File logoFile = new File(logoPath);
                if (logoFile.exists()) {
                    Image logo = new Image(ImageDataFactory.create(logoPath));
                    logo.setWidth(100);
                    logo.setFixedPosition(document.getPdfDocument().getDefaultPageSize().getWidth() - 120, 
                                         document.getPdfDocument().getDefaultPageSize().getHeight() - 80);
                    document.add(logo);
                }
            } catch (Exception e) {
            }
            
            Table infoTable = new Table(UnitValue.createPercentArray(new float[]{30, 70}))
                    .setWidth(UnitValue.createPercentValue(100));
            
            addTableCell(infoTable, "Model Code:", qcReport.getModelCode(), true);
            addTableCell(infoTable, "Factory Code:", qcReport.getFactoryCode(), true);
            addTableCell(infoTable, "Supplier:", qcReport.getSupplier(), true);
            addTableCell(infoTable, "Client:", qcReport.getClient(), true);
            addTableCell(infoTable, "PO Number:", qcReport.getPoNumber(), true);
            addTableCell(infoTable, "Inspection Date:", qcReport.getInspectionDate(), true);
            addTableCell(infoTable, "Order Qty:", String.valueOf(qcReport.getOrderQty()), true);
            addTableCell(infoTable, "Report Date:", qcReport.getReportDate(), true);
            addTableCell(infoTable, "Inspect Qty:", String.valueOf(qcReport.getInspectQty()), true);
            addTableCell(infoTable, "QC Officer:", qcReport.getQcOfficer(), true);
            addTableCell(infoTable, "Pass/Fail:", qcReport.getPassFail(), true);
            addTableCell(infoTable, "Second QC Date:", qcReport.getSecondQCDate(), true);
            
            document.add(infoTable);
            
            Paragraph commentsTitle = new Paragraph("Comments")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(10);
            document.add(commentsTitle);
            
            Table commentsTable = new Table(1)
                    .setWidth(UnitValue.createPercentValue(100));
            Cell commentsCell = new Cell()
                    .add(new Paragraph(qcReport.getComments() != null ? qcReport.getComments() : ""))
                    .setMinHeight(60)
                    .setBorder(new SolidBorder(ColorConstants.BLACK, 1));
            commentsTable.addCell(commentsCell);
            document.add(commentsTable);
            
            Paragraph imagesTitle = new Paragraph("Product Images")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20);
            document.add(imagesTitle);
            
            Map<String, String> imageFields = new HashMap<>();
            imageFields.put("stocksInWarehouse", "Stocks In Warehouse");
            imageFields.put("samplingOfProductsQuantity", "Sampling Of Products Quantity");
            imageFields.put("shippingMarks", "Shipping Marks");
            imageFields.put("barcode", "Barcode");
            imageFields.put("packingOutside", "Packing Outside");
            imageFields.put("packingInside", "Packing Inside");
            
            Table imagesTable = new Table(UnitValue.createPercentArray(new float[]{50, 50}))
                    .setWidth(UnitValue.createPercentValue(100));
            
            addImagesFromFields(imagesTable, qcReport, imageFields);
            
            document.add(imagesTable);
            
            Paragraph chairComponentsTitle = new Paragraph("Chair Components")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20);
            document.add(chairComponentsTitle);
            
            Map<String, String> chairComponentFields = new HashMap<>();
            chairComponentFields.put("chairComponentsPacked", "Chair Components Packed");
            chairComponentFields.put("chairComponentsUnpacked", "Chair Components Unpacked");
            chairComponentFields.put("fittingPackPacked", "Fitting Pack Packed");
            chairComponentFields.put("fittingPackUnpacked", "Fitting Pack Unpacked");
            
            Table chairComponentsTable = new Table(UnitValue.createPercentArray(new float[]{50, 50}))
                    .setWidth(UnitValue.createPercentValue(100));
            
            addImagesFromFields(chairComponentsTable, qcReport, chairComponentFields);
            
            document.add(chairComponentsTable);
            
            Paragraph labelsTitle = new Paragraph("Labels and Instructions")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20);
            document.add(labelsTitle);
            
            Map<String, String> labelsFields = new HashMap<>();
            labelsFields.put("productionLabel", "Production Label");
            labelsFields.put("assemblyInstructions", "Assembly Instructions");
            
            Table labelsTable = new Table(UnitValue.createPercentArray(new float[]{50, 50}))
                    .setWidth(UnitValue.createPercentValue(100));
            
            addImagesFromFields(labelsTable, qcReport, labelsFields);
            
            document.add(labelsTable);
            
            Paragraph componentsTitle = new Paragraph("Components")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20);
            document.add(componentsTitle);
            
            Map<String, String> componentsFields = new HashMap<>();
            componentsFields.put("imageOfComponentsSeat", "Seat");
            componentsFields.put("imageOfComponentsBack", "Back");
            componentsFields.put("imageOfComponentsBase", "Base");
            componentsFields.put("imageOfComponentsCastors", "Castors");
            componentsFields.put("imageOfComponentsGasLiftCover", "Gas Lift Cover");
            componentsFields.put("imageOfComponentsGasLiftStamp", "Gas Lift Stamp");
            componentsFields.put("imageOfComponentsArmrest", "Armrest");
            componentsFields.put("imageOfComponentMechanism", "Mechanism");
            componentsFields.put("imageOfComponentsHeadrest", "Headrest");
            
            Table componentsTable = new Table(UnitValue.createPercentArray(new float[]{50, 50}))
                    .setWidth(UnitValue.createPercentValue(100));
            
            addImagesFromFields(componentsTable, qcReport, componentsFields);
            
            document.add(componentsTable);
            
            Paragraph builtTitle = new Paragraph("Product Built")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20);
            document.add(builtTitle);
            
            Map<String, String> builtFields = new HashMap<>();
            builtFields.put("imageOfProductBuiltFront", "Front View");
            builtFields.put("imageOfProductBuiltSide", "Side View");
            builtFields.put("imageOfProductBuiltBack", "Back View");
            builtFields.put("imageOfProductBuilt45Degree", "45 Degree View");
            builtFields.put("frontImageOfProductBuiltCompare1", "Compare View 1");
            builtFields.put("frontImageOfProductBuiltCompare2", "Compare View 2");
            
            Table builtTable = new Table(UnitValue.createPercentArray(new float[]{50, 50}))
                    .setWidth(UnitValue.createPercentValue(100));
            
            addImagesFromFields(builtTable, qcReport, builtFields);
            
            document.add(builtTable);
            
            Paragraph functionTitle = new Paragraph("Function Check")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20);
            document.add(functionTitle);
            
            Map<String, String> functionFields = new HashMap<>();
            functionFields.put("functionCheckSeatHeightExtension", "Seat Height Extension");
            functionFields.put("functionCheckMechanismAdjustment", "Mechanism Adjustment");
            functionFields.put("functionCheckArmrestAdjustment", "Armrest Adjustment");
            functionFields.put("functionCheckHeadrestAdjustment", "Headrest Adjustment");
            functionFields.put("functionCheckOther1", "Other Check 1");
            functionFields.put("functionCheckOther2", "Other Check 2");
            
            Table functionTable = new Table(UnitValue.createPercentArray(new float[]{50, 50}))
                    .setWidth(UnitValue.createPercentValue(100));
            
            addImagesFromFields(functionTable, qcReport, functionFields);
            
            document.add(functionTable);
            
            if (qcReport.getDefects() != null && !qcReport.getDefects().isEmpty()) {
                Paragraph defectsTitle = new Paragraph("Defects")
                        .setFontSize(14)
                        .setBold()
                        .setMarginTop(20);
                document.add(defectsTitle);
                
                for (QcReportsDTO.Defect defect : qcReport.getDefects()) {
                    Paragraph defectTitle = new Paragraph("Title: " + defect.getDefectTitle())
                            .setFontSize(12)
                            .setBold()
                            .setMarginTop(10);
                    document.add(defectTitle);
                    
                    if (defect.getImages() != null && !defect.getImages().isEmpty()) {
                        Table defectImagesTable = new Table(UnitValue.createPercentArray(new float[]{50, 50}))
                                .setWidth(UnitValue.createPercentValue(100));
                        
                        for (String imagePath : defect.getImages()) {
                            try {
                                if (imagePath != null && !imagePath.isEmpty()) {
                                    Cell imageCell = new Cell();
                                    imageCell.setBorder(new SolidBorder(ColorConstants.RED, 1));
                                    
                                    String fullImagePath = imagePath;
                                    if (!imagePath.startsWith("/") && !imagePath.contains("://")) {
                                        fullImagePath = uploadPath + "/" + imagePath;
                                    }
                                    
                                    File imageFile = new File(fullImagePath);
                                    if (imageFile.exists()) {
                                        Image image = new Image(ImageDataFactory.create(fullImagePath));
                                        image.setWidth(UnitValue.createPercentValue(90));
                                        image.setAutoScale(true);
                                        imageCell.add(image);
                                    } else {
                                        imageCell.add(new Paragraph("Image not found: " + imagePath));
                                    }
                                    
                                    defectImagesTable.addCell(imageCell);
                                }
                            } catch (Exception e) {
                                Cell errorCell = new Cell();
                                errorCell.add(new Paragraph("Error loading image: " + e.getMessage()));
                                defectImagesTable.addCell(errorCell);
                            }
                        }
                        
                        document.add(defectImagesTable);
                    }
                    
                    Paragraph descriptionTitle = new Paragraph("Description:")
                            .setFontSize(12)
                            .setBold()
                            .setMarginTop(5);
                    document.add(descriptionTitle);
                    
                    Cell descriptionCell = new Cell()
                            .add(new Paragraph(defect.getDefectDescription() != null ? defect.getDefectDescription() : ""))
                            .setMinHeight(60)
                            .setBorder(new SolidBorder(ColorConstants.BLUE, 1));
                    
                    Table descriptionTable = new Table(1)
                            .setWidth(UnitValue.createPercentValue(100));
                    descriptionTable.addCell(descriptionCell);
                    document.add(descriptionTable);
                    
                    Paragraph suggestionTitle = new Paragraph("Improvement Suggestion:")
                            .setFontSize(12)
                            .setBold()
                            .setMarginTop(5);
                    document.add(suggestionTitle);
                    
                    Cell suggestionCell = new Cell()
                            .add(new Paragraph(defect.getImprovementSuggestion() != null ? defect.getImprovementSuggestion() : ""))
                            .setMinHeight(60)
                            .setBorder(new SolidBorder(ColorConstants.BLUE, 1));
                    
                    Table suggestionTable = new Table(1)
                            .setWidth(UnitValue.createPercentValue(100));
                    suggestionTable.addCell(suggestionCell);
                    document.add(suggestionTable);
                }
            }
            
            int numberOfPages = pdf.getNumberOfPages();
            for (int i = 1; i <= numberOfPages; i++) {
                document.showTextAligned(new Paragraph(String.format("Page %s of %s", i, numberOfPages))
                        .setFontSize(10),
                        pdf.getPage(i).getPageSize().getWidth() / 2,
                        20, i, TextAlignment.CENTER, VerticalAlignment.MIDDLE, 0);
            }
            
        } finally {
            document.close();
        }
        
        return baos.toByteArray();
    }
    
    /**
     * Generate PDF for Quotation
     * @param quotation Quotation data
     * @return PDF file as byte array
     */
    public byte[] generateQuotationPdf(Quotation quotation) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        
        try {
            Paragraph title = new Paragraph("TC QUOTATION FORM")
                    .setFontSize(20)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(title);
            
            try {
                String logoPath = uploadPath + "/logo/tc_logo.jpg";
                File logoFile = new File(logoPath);
                if (logoFile.exists()) {
                    Image logo = new Image(ImageDataFactory.create(logoPath));
                    logo.setWidth(100);
                    logo.setFixedPosition(document.getPdfDocument().getDefaultPageSize().getWidth() - 120, 
                                         document.getPdfDocument().getDefaultPageSize().getHeight() - 80);
                    document.add(logo);
                }
            } catch (Exception e) {
            }
            
            Paragraph notice = new Paragraph("Notice: Please follow below TC product standards and fill all necessary information into the form.")
                    .setFontSize(14)
                    .setBackgroundColor(ColorConstants.RED)
                    .setFontColor(ColorConstants.WHITE)
                    .setPadding(5)
                    .setMarginTop(10);
            document.add(notice);
            
            Paragraph tcStandard = new Paragraph("TC STANDARD:")
                    .setFontSize(14)
                    .setBold()
                    .setFontColor(ColorConstants.RED)
                    .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                    .setPadding(5)
                    .setMarginTop(10);
            document.add(tcStandard);
            
            Table standardTable = new Table(UnitValue.createPercentArray(new float[]{33, 33, 34}))
                    .setWidth(UnitValue.createPercentValue(100))
                    .setBackgroundColor(ColorConstants.LIGHT_GRAY);
            
            addStandardCell(standardTable, "Fabric /Linings of fabric: UKFR - BS5852 Crib 5, Med Hazard");
            addStandardCell(standardTable, "Seat foam: UKFR - BS5852, 30kg/m3, 70LBS Non-powder sponge");
            addStandardCell(standardTable, "Star base: BIFMA category A");
            
            addStandardCell(standardTable, "Mesh for seat:UKFR - BS5852 Crib 5, Med Hazard");
            addStandardCell(standardTable, "Back foam: UKFR - BS5852, 24kg/m3,70LBS Non-powder sponge");
            addStandardCell(standardTable, "Gas lift: Class#4");
            
            addStandardCell(standardTable, "Mesh for back:California fire protection - CA117");
            addStandardCell(standardTable, "Armrest: BIFMA category A");
            addStandardCell(standardTable, "Carton: 250LBS or K=K cardboard");
            
            document.add(standardTable);
            
            Paragraph basicInfoTitle = new Paragraph("Basic Information")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20);
            document.add(basicInfoTitle);
            
            Table infoTable = new Table(UnitValue.createPercentArray(new float[]{30, 70}))
                    .setWidth(UnitValue.createPercentValue(100));
            
            addTableCell(infoTable, "Quote No:", quotation.getQuoteNo(), true);
            addTableCell(infoTable, "Supplier:", quotation.getSupplier(), true);
            addTableCell(infoTable, "Supplier Item Code:", quotation.getSupplierItemCode(), true);
            addTableCell(infoTable, "Quote Date:", quotation.getQuoteDate(), true);
            addTableCell(infoTable, "Specification Details:", quotation.getSpecificationDetails(), true);
            addTableCell(infoTable, "Sample Lead Time:", quotation.getSampleLeadTime(), true);
            
            document.add(infoTable);
            
            Paragraph dimensionsTitle = new Paragraph("Dimensions")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20);
            document.add(dimensionsTitle);
            
            Table dimensionsTable = new Table(UnitValue.createPercentArray(new float[]{30, 20, 20, 30}))
                    .setWidth(UnitValue.createPercentValue(100));
            
            Cell headerCell1 = new Cell(1, 3)
                    .add(new Paragraph("Overall Dimensions (cm)"))
                    .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            dimensionsTable.addHeaderCell(headerCell1);
            
            Cell headerCell2 = new Cell()
                    .add(new Paragraph(""))
                    .setBackgroundColor(ColorConstants.LIGHT_GRAY);
            dimensionsTable.addHeaderCell(headerCell2);
            
            dimensionsTable.addCell(new Cell().add(new Paragraph("Width")).setBold());
            dimensionsTable.addCell(new Cell().add(new Paragraph(String.valueOf(quotation.getOverallDimensionsWidth()))));
            dimensionsTable.addCell(new Cell().add(new Paragraph("cm")));
            dimensionsTable.addCell(new Cell().add(new Paragraph("")));
            
            dimensionsTable.addCell(new Cell().add(new Paragraph("Depth")).setBold());
            dimensionsTable.addCell(new Cell().add(new Paragraph(String.valueOf(quotation.getOverallDimensionsDepth()))));
            dimensionsTable.addCell(new Cell().add(new Paragraph("cm")));
            dimensionsTable.addCell(new Cell().add(new Paragraph("")));
            
            dimensionsTable.addCell(new Cell().add(new Paragraph("Height")).setBold());
            dimensionsTable.addCell(new Cell().add(new Paragraph(String.valueOf(quotation.getOverallDimensionsHeight()))));
            dimensionsTable.addCell(new Cell().add(new Paragraph("cm")));
            dimensionsTable.addCell(new Cell().add(new Paragraph("")));
            
            Cell boxHeaderCell = new Cell(1, 3)
                    .add(new Paragraph("Box Dimensions (cm)"))
                    .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            dimensionsTable.addCell(boxHeaderCell);
            dimensionsTable.addCell(new Cell().add(new Paragraph("")));
            
            dimensionsTable.addCell(new Cell().add(new Paragraph("Width")).setBold());
            dimensionsTable.addCell(new Cell().add(new Paragraph(String.valueOf(quotation.getBoxDimensionsWidth()))));
            dimensionsTable.addCell(new Cell().add(new Paragraph("cm")));
            dimensionsTable.addCell(new Cell().add(new Paragraph("")));
            
            dimensionsTable.addCell(new Cell().add(new Paragraph("Depth")).setBold());
            dimensionsTable.addCell(new Cell().add(new Paragraph(String.valueOf(quotation.getBoxDimensionsDepth()))));
            dimensionsTable.addCell(new Cell().add(new Paragraph("cm")));
            dimensionsTable.addCell(new Cell().add(new Paragraph("")));
            
            dimensionsTable.addCell(new Cell().add(new Paragraph("Height")).setBold());
            dimensionsTable.addCell(new Cell().add(new Paragraph(String.valueOf(quotation.getBoxDimensionsHeight()))));
            dimensionsTable.addCell(new Cell().add(new Paragraph("cm")));
            dimensionsTable.addCell(new Cell().add(new Paragraph("")));
            
            document.add(dimensionsTable);
            
            Paragraph weightTitle = new Paragraph("Weight and Volume")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20);
            document.add(weightTitle);
            
            Table weightTable = new Table(UnitValue.createPercentArray(new float[]{30, 70}))
                    .setWidth(UnitValue.createPercentValue(100));
            
            addTableCell(weightTable, "Net Weight:", String.valueOf(quotation.getBoxWeightNetWeighth()) + " kg", true);
            addTableCell(weightTable, "Gross Weight:", quotation.getNetWeightGrossWeight(), true);
            addTableCell(weightTable, "Effective Volume:", quotation.getEffectiveVol(), true);
            addTableCell(weightTable, "Loading Qty:", String.valueOf(quotation.getLoadingQty()), true);
            
            document.add(weightTable);
            
            Paragraph pricingTitle = new Paragraph("Pricing and Order Information")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20);
            document.add(pricingTitle);
            
            Table pricingTable = new Table(UnitValue.createPercentArray(new float[]{30, 70}))
                    .setWidth(UnitValue.createPercentValue(100));
            
            addTableCell(pricingTable, "MOQ:", quotation.getMoq(), true);
            addTableCell(pricingTable, "FOB Price:", String.valueOf(quotation.getFobPrice()), true);
            addTableCell(pricingTable, "Currency:", String.valueOf(quotation.getCurrency()), true);
            addTableCell(pricingTable, "Port:", quotation.getPort(), true);
            addTableCell(pricingTable, "Valid Period:", quotation.getValidPeriod(), true);
            
            document.add(pricingTable);
            
            Paragraph additionalTitle = new Paragraph("Additional Information")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20);
            document.add(additionalTitle);
            
            Table additionalTable = new Table(UnitValue.createPercentArray(new float[]{30, 70}))
                    .setWidth(UnitValue.createPercentValue(100));
            
            addTableCell(additionalTable, "BIFMA Tested:", quotation.getBifmaTested() == 1 ? "Yes" : "No", true);
            addTableCell(additionalTable, "CAD Block Available:", quotation.getCadBlockAvailable() == 1 ? "Yes" : "No", true);
            addTableCell(additionalTable, "Product Data Available:", quotation.getProductDataAvailable() == 1 ? "Yes" : "No", true);
            addTableCell(additionalTable, "Product Images Available:", quotation.getProductImagesAvailable() == 1 ? "Yes" : "No", true);
            addTableCell(additionalTable, "Sales Contacts:", quotation.getSalesContacts(), true);
            
            document.add(additionalTable);
            
            Paragraph remarksTitle = new Paragraph("Remarks")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20);
            document.add(remarksTitle);
            
            Table remarksTable = new Table(1)
                    .setWidth(UnitValue.createPercentValue(100));
            Cell remarksCell = new Cell()
                    .add(new Paragraph(quotation.getRemark() != null ? quotation.getRemark() : ""))
                    .setMinHeight(60)
                    .setBorder(new SolidBorder(ColorConstants.BLACK, 1));
            remarksTable.addCell(remarksCell);
            document.add(remarksTable);
            
            if (quotation.getImage() != null && !quotation.getImage().isEmpty()) {
                Paragraph imageTitle = new Paragraph("Product Image")
                        .setFontSize(14)
                        .setBold()
                        .setMarginTop(20);
                document.add(imageTitle);
                
                try {
                    String imagePath = quotation.getImage();
                    if (!imagePath.startsWith("/") && !imagePath.contains("://")) {
                        imagePath = filePathConfig.getUploadPath() + "/" + imagePath;
                    }
                    
                    File imageFile = new File(imagePath);
                    if (imageFile.exists()) {
                        Image productImage = new Image(ImageDataFactory.create(imagePath));
                        productImage.setWidth(UnitValue.createPercentValue(50));
                        productImage.setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER);
                        document.add(productImage);
                    }
                } catch (Exception e) {
                    document.add(new Paragraph("Error loading product image: " + e.getMessage()));
                }
            }
            
            int numberOfPages = pdf.getNumberOfPages();
            for (int i = 1; i <= numberOfPages; i++) {
                document.showTextAligned(new Paragraph(String.format("Page %s of %s", i, numberOfPages))
                        .setFontSize(10),
                        pdf.getPage(i).getPageSize().getWidth() / 2,
                        20, i, TextAlignment.CENTER, VerticalAlignment.MIDDLE, 0);
            }
            
        } finally {
            document.close();
        }
        
        return baos.toByteArray();
    }
    
    /**
     * Helper method to add a cell to a table with label and value
     */
    private void addTableCell(Table table, String label, String value, boolean addBorder) {
        Cell labelCell = new Cell()
                .add(new Paragraph(label))
                .setBold();
        
        Cell valueCell = new Cell()
                .add(new Paragraph(value != null ? value : ""));
        
        if (addBorder) {
            labelCell.setBorder(new SolidBorder(ColorConstants.BLACK, 1));
            valueCell.setBorder(new SolidBorder(ColorConstants.BLACK, 1));
        }
        
        table.addCell(labelCell);
        table.addCell(valueCell);
    }
    
    /**
     * Helper method to add a standard cell to the TC Standard table
     */
    private void addStandardCell(Table table, String text) {
        Cell cell = new Cell()
                .add(new Paragraph(text))
                .setFontSize(12)
                .setBorder(new SolidBorder(ColorConstants.BLACK, 1));
        table.addCell(cell);
    }
    
    /**
     * Helper method to add images from fields to a table
     */
    private void addImagesFromFields(Table table, QcReportsDTO qcReport, Map<String, String> imageFields) {
        for (Map.Entry<String, String> entry : imageFields.entrySet()) {
            String fieldName = entry.getKey();
            String label = entry.getValue();
            
            try {
                java.lang.reflect.Method getter = QcReportsDTO.class.getMethod("get" + 
                        fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                String imagePath = (String) getter.invoke(qcReport);
                
                Cell cell = new Cell();
                cell.setBorder(new SolidBorder(ColorConstants.BLACK, 1));
                
                Paragraph labelParagraph = new Paragraph(label)
                        .setBold()
                        .setFontSize(12);
                cell.add(labelParagraph);
                
                if (imagePath != null && !imagePath.isEmpty()) {
                    String fullImagePath = imagePath;
                    if (!imagePath.startsWith("/") && !imagePath.contains("://")) {
                        fullImagePath = filePathConfig.getUploadPath() + "/" + imagePath;
                    }
                    
                    File imageFile = new File(fullImagePath);
                    if (imageFile.exists()) {
                        Image image = new Image(ImageDataFactory.create(fullImagePath));
                        image.setWidth(UnitValue.createPercentValue(90));
                        image.setAutoScale(true);
                        cell.add(image);
                    } else {
                        cell.add(new Paragraph("Image not found: " + imagePath));
                    }
                } else {
                    cell.add(new Paragraph("No image available"));
                }
                
                table.addCell(cell);
            } catch (Exception e) {
                Cell errorCell = new Cell();
                errorCell.add(new Paragraph("Error: " + e.getMessage()));
                table.addCell(errorCell);
            }
        }
        
        if (imageFields.size() % 2 != 0) {
            table.addCell(new Cell().add(new Paragraph("")));
        }
    }
}
