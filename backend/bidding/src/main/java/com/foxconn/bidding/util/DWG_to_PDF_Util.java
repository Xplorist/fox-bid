package com.foxconn.bidding.util;

import com.aspose.cad.Color;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.aspose.cad.imageoptions.PdfOptions;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * DWG文件轉PDF文件工具類
 */
public class DWG_to_PDF_Util {
    // DWG文件轉換為PDF
    public static OutputStream convert_DWG_to_PDF(InputStream is, OutputStream os) {
        com.aspose.cad.Image objImage = com.aspose.cad.Image.load(is);

        CadRasterizationOptions rasterizationOptions = new CadRasterizationOptions();
        rasterizationOptions.setBackgroundColor(Color.getWhite());
        rasterizationOptions.setPageWidth(210);
        rasterizationOptions.setPageHeight(297);

        // Create an instance of PdfOptions
        PdfOptions pdfOptions = new PdfOptions();
        // Set the VectorRasterizationOptions property
        pdfOptions.setVectorRasterizationOptions(rasterizationOptions);

        // Export the DWG to PDF
        objImage.save(os, pdfOptions);
        //ExEnd:ConvertDWGFileToPDF

        return os;
    }
}
