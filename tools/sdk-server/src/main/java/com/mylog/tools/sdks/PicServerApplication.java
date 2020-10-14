package com.mylog.tools.sdks;

import com.mylog.tools.sdks.filesdk.QiNiuSdk;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;

/**
 * @author Dylan
 * @Date : Created in 14:57 2020/8/21
 * @Description :
 * @Function :
 */
public class PicServerApplication {

    public static void main(String[] args) {
        File file = new File("E:\\壁纸\\8k\\诗乃.jpg");
        String accesskey = "k48ML3ZoFFcGdxUcXdYXGgoSArc2yJ61xxIyANLk";
        String secretkey = "vlI9BWwW--uqWuNbuQLQL6DHm0Vqd9uWddcHslc_";
        String bucketname = "dylan-pic";
        Response response = null;
        try {
            response = QiNiuSdk.uploadToQiniu(file, accesskey, secretkey, bucketname);
            System.out.println(response.bodyString());
        }catch (QiniuException e){
            e.printStackTrace();
        }
    }
}
