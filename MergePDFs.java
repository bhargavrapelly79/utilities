package com.citi.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;


public class MergePDFs {
	static void mergePdfFiles(List<InputStream> inputPdfList,
	        OutputStream outputStream) throws Exception{
	    //Create document and pdfReader objects.
	    Document document = new Document();
	    List<PdfReader> readers = 
	            new ArrayList<PdfReader>();
	    int totalPages = 0;

	    //Create pdf Iterator object using inputPdfList.
	    Iterator<InputStream> pdfIterator = 
	            inputPdfList.iterator();

	    // Create reader list for the input pdf files.
	    while (pdfIterator.hasNext()) {
	            InputStream pdf = pdfIterator.next();
	            PdfReader pdfReader = new PdfReader(pdf);
	            readers.add(pdfReader);
	            totalPages = totalPages + pdfReader.getNumberOfPages();
	    }

	    // Create writer for the outputStream
	    PdfWriter writer = PdfWriter.getInstance(document, outputStream);

	    //Open document.
	    document.open();

	    //Contain the pdf data.
	    PdfContentByte pageContentByte = writer.getDirectContent();

	    PdfImportedPage pdfImportedPage;
	    int currentPdfReaderPage = 1;
	    Iterator<PdfReader> iteratorPDFReader = readers.iterator();

	    // Iterate and process the reader list.
	    while (iteratorPDFReader.hasNext()) {
	            PdfReader pdfReader = iteratorPDFReader.next();
	            //Create page and add content.
	            while (currentPdfReaderPage <= pdfReader.getNumberOfPages()) {
	                  document.newPage();
	                  pdfImportedPage = writer.getImportedPage(
	                          pdfReader,currentPdfReaderPage);
	                  pageContentByte.addTemplate(pdfImportedPage, 0, 0);
	                  currentPdfReaderPage++;
	            }
	            currentPdfReaderPage = 1;
	    }

	    //Close document and outputStream.
	    outputStream.flush();
	    document.close();
	    outputStream.close();

	    System.out.println("Pdf files merged successfully.");
	}

	public static void main(String args[]){
	    try {
	        //Prepare input pdf file list as list of input stream.
	        List<InputStream> inputPdfList = new ArrayList<InputStream>();
//	        String myDir="C:\\Users\\bharg\\OneDrive\\Documents\\Canada\\Proof of Business Establishment in Canada\\";
//	        File dir = new File(myDir);
//	        File[] directoryListing = dir.listFiles();
//	        if (directoryListing != null) {
//	          for (File child : directoryListing) {
//	            System.out.println(child);
//	            inputPdfList.add(new FileInputStream(child));
//	          }
//	        } 
	        inputPdfList.add(new FileInputStream("C:\\Users\\bharg\\OneDrive\\Documents\\Canada\\Medicals\\Vaishnavi\\TO WHOMSOEVER IT MAY CONCERN_Vaishnavi.pdf"));
	        inputPdfList.add(new FileInputStream("C:\\Users\\bharg\\OneDrive\\Documents\\Canada\\Medicals\\Vaishnavi\\Arrieval_Stamp.pdf"));
	        inputPdfList.add(new FileInputStream("C:\\Users\\bharg\\OneDrive\\Documents\\Canada\\Medicals\\Vaishnavi\\I797.pdf"));
//	        inputPdfList.add(new FileInputStream("C:\\Users\\bharg\\OneDrive\\Documents\\Canada\\Medicals\\Bhargav\\VISA.pdf"));
//	        inputPdfList.add(new FileInputStream("C:\\Users\\bhargav_rapelli\\Downloads\\pay_statements_2\\Pay Date 2019-05-10.pdf"));
//	        inputPdfList.add(new FileInputStream("C:\\Users\\bhargav_rapelli\\Downloads\\pay_statements_2\\Pay Date 2019-04-26.pdf"));
//	        inputPdfList.add(new FileInputStream("C:\\Users\\bhargav_rapelli\\Downloads\\pay_statements_2\\Pay Date 2019-04-12.pdf"));

	        
//	        inputPdfList.add(new FileInputStream("..\\pdf\\pdf_4.pdf"));


	        //Prepare output stream for merged pdf file.
	        OutputStream outputStream = 
	                new FileOutputStream("C:\\Users\\bharg\\OneDrive\\Documents\\Canada\\Medicals\\Sukanya\\MedicalException_VaishnaviRapelly"
	                		+ ".pdf");

	        //call method to merge pdf files.
	        mergePdfFiles(inputPdfList, outputStream);     
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    }
	}
