//package thread;
//
//import org.apache.commons.io.FileUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//
//
//public class TestThread2 extends Thread{
//    private String url;
//    private String filepath;
//
//    public TestThread2(String url, String filepath){
//        this.url = url;
//        this.filepath = filepath;
//    }
//
//    @Override
//    public void run() {
//        WebDownloader webDownloader = new WebDownloader();
//        webDownloader.downloader(url, filepath);
//        System.out.println("下载了文件名为："+filepath);
//    }
//
//    public static void main(String[] args) {
//        TestThread2 t1 = new TestThread2("https://i0.hdslb.com/bfs/videoshot/132935220.jpg", "1.jpg");
//        TestThread2 t2 = new TestThread2("https://i0.hdslb.com/bfs/videoshot/132935220.jpg", "2.jpg");
//        TestThread2 t3 = new TestThread2("https://i0.hdslb.com/bfs/videoshot/132935220.jpg", "3.jpg");
//
//        t1.start();
//        t2.start();
//        t3.start();
//
//    }
//}
//
//// 下载器
//class WebDownloader{
//    public void downloader(String url, String filepath){
//        try {
//            FileUtils.copyURLToFile(new URL(url), new File(filepath));
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("IO异常，downloader方法出现问题");
//        }
//    }
//
//}
