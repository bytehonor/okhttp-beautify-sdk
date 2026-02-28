package com.bytehonor.sdk.toolkit.http.client;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.toolkit.http.exception.HttpToolkitException;

public class OkHttpBeautifyClientTest {

    private static final Logger LOG = LoggerFactory.getLogger(OkHttpBeautifyClientTest.class);

//    @Test
    public void testGetString2() {
        boolean isOk = true;
        try {
            // 测测header是否是浏览器的
            String html = OkHttpBeautifyClient.get("https://www.bytehonor.com");
            LOG.info("html:{}", html);
        } catch (HttpToolkitException e) {
            LOG.error("xxxx", e);
            isOk = false;
        }

        assertTrue("*testStartThread*", isOk);
    }

    // @Test
    public void test2() {
        ExecutorService service = Executors.newFixedThreadPool(64);
        int size = 256;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        AtomicInteger ai = new AtomicInteger(0);
        for (int i = 0; i < size; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        OkHttpBeautifyClient.get("https://www.baidu.com");
                        ai.incrementAndGet();
                        countDownLatch.countDown();
                    } catch (HttpToolkitException e) {
                        LOG.error("xxxx error:{}", e.getMessage());
                    }
                }
            });
        }
        try {
            countDownLatch.await();
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            LOG.info("sleep", e);
        }
        int success = ai.intValue();
        LOG.info("success:{}", success);
    }

//    @Test
    public void testUpload() {
        String url = "xx";

        boolean isOk = true;
        File file = null;
        try {
            file = new File("/xxx/1.jpg");
            Map<String, String> paramsMap = new HashMap<String, String>();
            paramsMap.put("status", "xx");
            paramsMap.put("access_token", "xx");

            String res = OkHttpBeautifyClient.uploadPic(url, paramsMap, file);
            LOG.info("res:{}", res);
        } catch (Exception e) {
            isOk = false;
            LOG.error("testUpload", e);
        }
        assertTrue("testUpload", isOk);
    }

    // @Test
    public void testDownload() {
        String url = "http://www.szse.cn/api/report/ShowReport?SHOWTYPE=xlsx&CATALOGID=1110&TABKEY=tab1&random=0.9754524179109694";
        String filePath = "D:/test/A股列表.xlsx";
        boolean isOk = true;
        try {
            OkHttpBeautifyClient.download(url, filePath);
        } catch (Exception e) {
            isOk = false;
            LOG.error("testUpload", e);
        }
        assertTrue("testDownload", isOk);
    }
    
    @Test
    public void testDownload2() {
        String url = "https://wx4.sinaimg.cn/orj480/9d6d01f9ly1gdmj3ac93vj20u0140u0x.jpg";
        String path = "/Users/lijianqiang/data/testDownloadWeibo2.jpg";
        boolean isOk = true;
        try {
            // Referer: https://m.weibo.cn/detail/4854157586215881
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Referer", "https://m.weibo.cn/detail/4854157586215881");
            OkHttpBeautifyClient.download(url, path, headers);
            File file = new File(path);
            isOk = file.exists();
            LOG.info("isOk:{}", isOk);
        } catch (Exception e) {
            isOk = false;
            LOG.error("testDownload", e);
        }
        assertTrue("testDownload", isOk);
    }
    
    // @Test
    public void testPostPlain() {
        boolean isOk = true;
        try {
            // 测测header是否是浏览器的
            String url = "https://proxy.bytehonor.com/body/test";
            String text = "helloworld1";
            String res = OkHttpBeautifyClient.postPlain(url, text);
            LOG.info("res:{}", res);
        } catch (HttpToolkitException e) {
            LOG.error("xxxx", e);
            isOk = false;
        }

        assertTrue("*testStartThread*", isOk);
    }
}
