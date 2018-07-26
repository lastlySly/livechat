package cn.lastlysly.myutils;

import cn.lastlysly.handler.MyCustomException;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-07-26 10:56
 **/
public class UploadAndDelUtils {

    /**
     * 删除超过指定天数的文件
     * @param filePath 文件夹路径
     * @param day 天数
     * @return
     * @throws MyCustomException
     */
    public static boolean delOverTimeFile(String filePath,int day) throws MyCustomException {

        File messageFileRoot = new File(filePath);
        File[] fileDirs = messageFileRoot.listFiles();
        //三天
        long timeInterval = day * 24 * 60 * 60 * 1000;
        for(File fileDir : fileDirs){
            if(fileDir.isDirectory()){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date dirDate = simpleDateFormat.parse(fileDir.getName());
                    //文件夹日期时间戳
                    long dirTimestamp = dirDate.getTime();
                    //计算当前时间戳
                    Date nowDate = new Date();
                    long nowTimestamp = nowDate.getTime();
                    //超过时间间隔
                    if((nowTimestamp - timeInterval) > dirTimestamp){
                        delAllFileAndDirectory(fileDir);
                    }
                } catch (ParseException e) {
                    throw new MyCustomException("文件定时删除失败");
                }

            }//if

        }//for
        return true;
    }

    /**
     * 删除指定目录,包括其下所有文件和文件夹
     * @param directory
     */
    public static void delAllFileAndDirectory(File directory){
        //遍历目录下所有文件或目录
        File[] files = directory.listFiles();
        if(files.length > 0){
            for(File file : files){
                if(file.isDirectory()){
                    delAllFileAndDirectory(file);
                    file.delete();
                }else{
                    file.delete();
                }
            }
        }
        directory.delete();

    }

}
