package team.javaSpirit.teachingAssistantPlatform.firstcheck;

import java.io.IOException;

import javax.swing.JFrame;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;

public class FirstInvoke {
	public static void firstInvoke() throws Exception, InterruptedException, IOException {
		OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
		grabber.setImageHeight(720);
		grabber.setImageWidth(720);
		grabber.start();
		boolean c = true;
		// 开始获取摄像头数据
		CanvasFrame canvas = new CanvasFrame("正在签到");// 新建一个窗口
		canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("获取摄像头成功");
		System.out.println("检测人脸");
		while (true) {
			if (!canvas.isDisplayable()) {
				grabber.stop();// 停止抓取
			}
			Frame frame = grabber.grab();
			ToIplImage ti = new ToIplImage();
			Mat src = ti.convertToMat(frame);
			FirstDetection.faceDetection(src);
			canvas.showImage(frame);
			if((FirstDetection.faceDetection(src)==0)&&c) {
				c=false;
				FirstRecord.recordCamera(grabber,canvas);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				canvas.dispose();
			}
			canvas.setAlwaysOnTop(true);
			Thread.sleep(50);// 50毫秒刷新一次图像
		}
	}
}
