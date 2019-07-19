package com.dengtao.travel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.FetchRet;
import org.apache.commons.io.IOUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;

/**
 * @author Lanxiaowei(736031305 @qq.com)
 * @ClassName: QiniuUtils
 * @Description: 七牛操作工具类
 * @date 2017年6月6日 上午10:56:32
 */

public class QiniuUtils {
    private static final String ACCESS_KEY = "9Z2RnhG5TeABQOchlShvXj1apSeXpJnNZSm7IEp";
    private static final String SECRET_KEY = "M_M3WEMWUybEoOTEPGeef2keYzL8KVvN_KorAhN";
 /* 默认上传空间 */
 private static final String BUCKET_NAME = "defaultpic";// 默认到其他类中
 /* 空间默认域名 */

    private static final String BUCKET_HOST_NAME = "";
    private static UploadManager uploadManager = new UploadManager(new Configuration());

    private static int LIMIT_SIZE = 1000;

    /**
     * @param @return
     * @param @throws QiniuException
     * @return String[]
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: listBucket
     * @Description: 返回七牛帐号的所有空间
     */
    public static String[] listBucket() throws QiniuException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth,new Configuration());
        return bucketManager.buckets();
    }

    /**
     * @param bucketName 空间名称
     * @param prefix     文件名前缀
     * @param limit      每次迭代的长度限制，最大1000，推荐值 100[即一个批次从七牛拉多少条]
     * @param @return
     * @return List<FileInfo>
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: listFileOfBucket
     * @Description: 获取指定空间下的文件列表
     */
    public static List<FileInfo> listFileOfBucket(String bucketName,
                                                  String prefix, int limit) {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth,new Configuration());
        BucketManager.FileListIterator it = bucketManager
                .createFileListIterator(bucketName, prefix, limit, null);
        List<FileInfo> list = new ArrayList<FileInfo>();
        while (it.hasNext()) {
            FileInfo[] items = it.next();
            if (null != items && items.length > 0) {
                list.addAll(Arrays.asList(items));
            }
        }
        return list;
    }

    /**
     * @param @param  inputStream 待上传文件输入流
     * @param @param  bucketName 空间名称
     * @param @param  key 空间内文件的key
     * @param @param  mimeType 文件的MIME类型，可选参数，不传入会自动判断
     * @param @return
     * @param @throws IOException
     * @return String
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: uploadFile
     * @Description: 七牛图片上传
     */
    public static String uploadFile(InputStream inputStream, String bucketName,
                                    String key, String mimeType) throws IOException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String token = auth.uploadToken(bucketName);
        byte[] byteData = IOUtils.toByteArray(inputStream);
        Response response = uploadManager.put(byteData, key, token, null,
                mimeType, false);
        inputStream.close();
        return response.bodyString();
    }

    /**
     * @param @param  inputStream 待上传文件输入流
     * @param @param  bucketName 空间名称
     * @param @param  key 空间内文件的key
     * @param @return
     * @param @throws IOException
     * @return String
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: uploadFile
     * @Description: 七牛图片上传
     */
    public static String uploadFile(InputStream inputStream, String bucketName,
                                    String key) throws IOException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String token = auth.uploadToken(bucketName);
        byte[] byteData = IOUtils.toByteArray(inputStream);
        Response response = uploadManager.put(byteData, key, token, null, null,
                false);
        inputStream.close();
        return response.bodyString();
    }

    /**
     * @param filePath   待上传文件的硬盘路径
     * @param fileName   待上传文件的文件名
     * @param bucketName 空间名称
     * @param key        空间内文件的key
     * @param @return
     * @param @throws    IOException
     * @return String
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: uploadFile
     * @Description: 七牛图片上传
     */
    public static String uploadFile(String filePath, String fileName,
                                    String bucketName, String key) throws IOException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String token = auth.uploadToken(bucketName);
        InputStream is = new FileInputStream(new File(filePath + fileName));
        byte[] byteData = IOUtils.toByteArray(is);
        Response response = uploadManager.put(byteData,
                (key == null || "".equals(key)) ? fileName : key, token);
        is.close();
        return response.bodyString();
    }

    /**
     * @param filePath   待上传文件的硬盘路径
     * @param fileName   待上传文件的文件名
     * @param bucketName 空间名称
     * @param @return
     * @param @throws    IOException
     * @return String
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: uploadFile
     * @Description: 七牛图片上传[若没有指定文件的key, 则默认将fileName参数作为文件的key]
     */
    public static String uploadFile(String filePath, String fileName,
                                    String bucketName) throws IOException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String token = auth.uploadToken(bucketName);
        InputStream is = new FileInputStream(new File(filePath + fileName));
        byte[] byteData = IOUtils.toByteArray(is);
        Response response = uploadManager.put(byteData, fileName, token);
        is.close();
        return response.bodyString();
    }

    /**
     * @param url        网络上一个资源文件的URL
     * @param bucketName 空间名称
     * @param key        空间内文件的key[唯一的]
     * @param @return
     * @return String
     * @throws QiniuException
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: fetchToBucket
     * @Description: 提取网络资源并上传到七牛空间里
     */
    public static String fetchToBucket(String url, String bucketName, String key)
            throws QiniuException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth,new Configuration());
        FetchRet fetch = bucketManager.fetch(url, bucketName, key);
//        DefaultPutRet putret = bucketManager.fetch(url, bucketName, key);
        return fetch.key;
    }

    /**
     * @param url
     * @param bucketName
     * @param @return
     * @param @throws    QiniuException
     * @return String
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: fetchToBucket
     * @Description: 提取网络资源并上传到七牛空间里, 不指定key，则默认使用url作为文件的key
     */
    public static String fetchToBucket(String url, String bucketName)
            throws QiniuException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth,new Configuration());
        FetchRet putret = bucketManager.fetch(url, bucketName);
        return putret.key;
    }

    /**
     * @param bucket       源空间名称
     * @param key          源空间里文件的key(唯一的)
     * @param targetBucket 目标空间
     * @param targetKey    目标空间里文件的key(唯一的)
     * @return void
     * @throws QiniuException
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: copyFile
     * @Description: 七牛空间内文件复制
     */
    public static void copyFile(String bucket, String key, String targetBucket,
                                String targetKey) throws QiniuException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth,new Configuration());
        bucketManager.copy(bucket, key, targetBucket, targetKey);
    }

    /**
     * @param bucket       源空间名称
     * @param key          源空间里文件的key(唯一的)
     * @param targetBucket 目标空间
     * @param targetKey    目标空间里文件的key(唯一的)
     * @param @throws      QiniuException
     * @return void
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: moveFile
     * @Description: 七牛空间内文件剪切
     */
    public static void moveFile(String bucket, String key, String targetBucket,
                                String targetKey) throws QiniuException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth,new Configuration());
        bucketManager.move(bucket, key, targetBucket, targetKey);
    }

    /**
     * @param bucket
     * @param key
     * @param targetKey
     * @param @throws   QiniuException
     * @return void
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: renameFile
     * @Description: 七牛空间内文件重命名
     */
    public static void renameFile(String bucket, String key, String targetKey)
            throws QiniuException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth,new Configuration());
        bucketManager.rename(bucket, key, targetKey);
    }

    /**
     * @param bucket  空间名称
     * @param key     空间内文件的key[唯一的]
     * @param @throws QiniuException
     * @return void
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: deleteFile
     * @Description: 七牛空间内文件删除
     */
    public static void deleteFile(String bucket, String key)
            throws QiniuException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth,new Configuration());
        bucketManager.delete(bucket, key);
    }

    /**
     * @param @param  bucketName 空间名称
     * @param @param  prefix 文件key的前缀
     * @param @param  limit 批量提取的最大数目
     * @param @return
     * @param @throws QiniuException
     * @return FileInfo[]
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: findFiles
     * @Description: 返回指定空间下的所有文件信息
     */
    public static FileInfo[] findFiles(String bucketName, String prefix,int limit) throws QiniuException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth,new Configuration());
        FileListing listing = bucketManager.listFiles(bucketName, prefix, null,
                limit, null);
        if (listing == null || listing.items == null
                || listing.items.length <= 0) {
            return null;
        }
        return listing.items;
    }

    /**
     * @param @param  bucketName 空间名称
     * @param @param  prefix 文件key的前缀
     * @param @return
     * @param @throws QiniuException
     * @return FileInfo[]
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: findFiles
     * @Description: 返回指定空间下的所有文件信息
     */
    public static FileInfo[] findFiles(String bucketName, String prefix)
            throws QiniuException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth,new Configuration());
        FileListing listing = bucketManager.listFiles(bucketName, prefix, null,
                LIMIT_SIZE, null);
        if (listing == null || listing.items == null
                || listing.items.length <= 0) {
            return null;
        }
        return listing.items;
    }

    /**
     * @param @param  bucketName
     * @param @param  key
     * @param @return
     * @param @throws QiniuException
     * @return FileInfo[]
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: findFiles
     * @Description: 返回指定空间下的所有文件信息
     */
    public static FileInfo[] findFiles(String bucketName) throws QiniuException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth,new Configuration());
        FileListing listing = bucketManager.listFiles(bucketName, null, null,
                LIMIT_SIZE, null);
        if (listing == null || listing.items == null
                || listing.items.length <= 0) {
            return null;
        }
        return listing.items;
    }

    /**
     * @param @param  bucketName
     * @param @param  key
     * @param @param  limit
     * @param @return
     * @param @throws QiniuException
     * @return FileInfo
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: findOneFile
     * @Description: 返回指定空间下的某个文件
     */
    public static FileInfo findOneFile(String bucketName, String key, int limit)
            throws QiniuException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth,new Configuration());
        FileListing listing = bucketManager.listFiles(bucketName, key, null,
                limit, null);
        if (listing == null || listing.items == null
                || listing.items.length <= 0) {
            return null;
        }
        return (listing.items)[0];
    }

    /**
     * @param @param  bucketName
     * @param @param  key
     * @param @return
     * @param @throws QiniuException
     * @return FileInfo
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: findOneFile
     * @Description: 返回指定空间下的某个文件(重载)
     */
    public static FileInfo findOneFile(String bucketName, String key)
            throws QiniuException {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth,new Configuration());
        FileListing listing = bucketManager.listFiles(bucketName, key, null,
                LIMIT_SIZE, null);
        if (listing == null || listing.items == null
                || listing.items.length <= 0) {
            return null;
        }
        return (listing.items)[0];
    }

    /**
     * @param @param  key
     * @param @return
     * @param @throws QiniuException
     * @return String
     * @throws
     * @Author: Lanxiaowei(736031305 @ qq.com)
     * @Title: getFileAccessUrl
     * @Description: 返回七牛空间内指定文件的访问URL
     */
    public static String getFileAccessUrl(String key) throws QiniuException {
        return BUCKET_HOST_NAME + "/" + key;
    }

    public static void main(String[] args) throws IOException {
        // uploadFile("C:/test.jpg");

        System.out.println(listBucket());
//         * String[] buckets = listBucket(); for(String bucket : buckets) {
//         * System.out.println(bucket); }
//
//
//
//         * List<FileInfo> list = listFileOfBucket(BUCKET_NAME, null, 1000);
//         * for(FileInfo fileInfo : list) { System.out.println("key：" +
//         * fileInfo.key); System.out.println("hash：" + fileInfo.hash);
//         * System.out.println("................"); }


        // copyFile(BUCKET_NAME, "images-test", BUCKET_NAME,
        // "images-test-1111");

        // renameFile(BUCKET_NAME, "images-test-1111", "images-test-2222.jpg");

        // deleteFile(BUCKET_NAME, "images-test-2222.jpg");

        // fetchToBucket("http://www.nanrenwo.net/uploads/allimg/121026/14-1210261JJD03.jpg",
        // BUCKET_NAME,"1111111111111111.jpg");

       /* FileInfo[] fileInfos = findFiles(BUCKET_NAME, "10", LIMIT_SIZE);
        for (FileInfo fileInfo : fileInfos) {
            System.out.println(fileInfo.key);
            System.out.println(fileInfo.hash);
            System.out.println("..............");
        }*/
    }

    /**
     * 删除七牛上的文件
     *
     * @param path  数据库中文件的路径
     * @param space 所存空间名称
     */
    public boolean deleteImageFromQiNiu(String path, String space) {
        if (!path.isEmpty()) {
            String[] split = path.split(".com/");
            if (split.length >= 2) {
                FileInfo findOneFile = null;
                try {//查询云端是否有该文件
                    findOneFile = QiniuUtils.findOneFile(space, split[1]);
                } catch (QiniuException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if (findOneFile != null) {//删除云端文件
                    try {
                        //.deleteFile(空间名, 文件名);
                        QiniuUtils.deleteFile(space, split[1]);
                        return true;
                        //结束返回成功
                    } catch (QiniuException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        //出现异常返回fase
                        return false;
                    }
                } else {
                    //如果七牛中没有该图片则返回成功
                    return true;
                }
            } else {
                //如果截取的数组小于2则是无效数据返回成功
                return true;
            }
        } else {
            //如果数据库中存储的问空则直接返回成功
            return true;
        }
    }

}
