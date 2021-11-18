package com.foxconn.bidding.util;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * PDF水印工具類
 */
public class PDF_WaterMark_Util {
    // 添加水印
    public static OutputStream addWaterMark(InputStream is, OutputStream os, String waterMarkText) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(is), new PdfWriter(os));
        Document doc = new Document(pdfDoc);
        int n = pdfDoc.getNumberOfPages();
        PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
        Paragraph p = new Paragraph(waterMarkText).setFont(font).setFontSize(30);

        // transparency
        PdfExtGState gs1 = new PdfExtGState();
        gs1.setFillOpacity(0.1f);
        // properties
        PdfCanvas over;
        Rectangle pagesize;
        float x, y;

        for (int i = 1; i <= n; i++) {
            pagesize = pdfDoc.getPage(i).getPageSize();
            x = (pagesize.getLeft() + pagesize.getRight()) / 2;
            y = (pagesize.getTop() + pagesize.getBottom()) / 2;
            over = new PdfCanvas(pdfDoc.getPage(i));
            over.saveState();
            over.setExtGState(gs1);

            doc.showTextAligned(p, x, y, i, TextAlignment.CENTER, VerticalAlignment.TOP, 0);
        }
        doc.close();

        return os;
    }
}
