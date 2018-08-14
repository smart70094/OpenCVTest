package com.opencv.utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class DetectFaceUtils {
	VideoCapture webCam;
	 MatOfRect faceDetections;
	 Mat webCam_image;
	 CascadeClassifier faceDetector;
	
	 public DetectFaceUtils() throws InterruptedException{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		faceDetector = new CascadeClassifier("lbpcascade_frontalface.xml");
		webCam=new VideoCapture(0);
		Thread.sleep(3000);
		 if(!webCam.isOpened()) System.out.println("Did not connect to cammer!");
		 else System.out.println("found webcam:"+webCam.toString());
		 faceDetections = new MatOfRect();
		 webCam_image=new Mat();
		 webCam.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, 600);
		 webCam.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, 800);
		 
	}
	
	public void close() {
		webCam.release();
	}
	
	public BufferedImage detectFace(){
		 webCam.read(webCam_image);
//		Imgproc.cvtColor(webCam_image, webCam_image, Imgproc.COLOR_BGR2GRAY);
		 faceDetector.detectMultiScale(webCam_image, faceDetections);
		 if(!faceDetections.empty()){
			 for (Rect rect : faceDetections.toArray()) 
				 Core.rectangle(webCam_image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
//			 takePicture(webCam_image);
		 }
		
		 return Mat2BufferedImage(webCam_image);
	}
	public void takePicture(Mat image){
		try{
			Date date=new Date();
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmss");
			String strDate = sdFormat.format(date)+".jpg";
			System.out.println(strDate);
			Highgui.imwrite("C:\\Users\\hp\\Desktop\\OpenCVPicture\\"+strDate, image);
		}catch(Exception e){
			e.getMessage();
		}
		//Highgui.imwrite("a.jpg", webCam_image);
	}
	
	private BufferedImage Mat2BufferedImage(Mat m){
		// source: http://answers.opencv.org/question/10344/opencv-java-load-image-to-gui/
		// Fastest code
		// The output can be assigned either to a BufferedImage or to an Image

		    int type = BufferedImage.TYPE_BYTE_GRAY;
		    if ( m.channels() > 1 ) {
		        type = BufferedImage.TYPE_3BYTE_BGR;
		    }
		    int bufferSize = m.channels()*m.cols()*m.rows();
		    byte [] b = new byte[bufferSize];
		    m.get(0,0,b); // get all the pixels
		    BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
		    final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		    System.arraycopy(b, 0, targetPixels, 0, b.length);  
		    return image;
		}
}
