package cn.lastlysly.controller;

import cn.lastlysly.handler.MyCustomException;
import cn.lastlysly.myutils.CommonUtil;
import cn.lastlysly.myutils.UploadAndDelUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-07-25 19:31
 **/
@Controller
@RequestMapping("/messagedeal")
public class MessageController {

    /**
     * froalaEditor富文本编辑器里，图片上传
     * @param req
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @CrossOrigin
    @RequestMapping(value = "/uploadImgEditor",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> uploadImgEditor(MultipartHttpServletRequest req, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        MultipartFile pictureFile = req.getFile("file");
        Map<String, String> map = new HashMap<String,String>();
        if(pictureFile!=null && pictureFile.getOriginalFilename()!=null && pictureFile.getOriginalFilename().length()>0){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String nowTime = simpleDateFormat.format(new Date());

            String saveDirName2 = CommonUtil.getUploadFilePath() + "/" + "messageimg" + "/" + nowTime;

            File folder = new File(saveDirName2);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String filePath = "/uploadpic/messageimg/" + nowTime + "/";
            //上传文件原始名称
            String originalFilename = pictureFile.getOriginalFilename();
            //新的图片名称
            String newFileName = UUID.randomUUID() +originalFilename.substring(originalFilename.lastIndexOf("."));
            //新文件
//            System.out.println(filePath+newFileName);
            File file = new File(saveDirName2 + "/" + newFileName);
            //将内存中的文件写入磁盘
            pictureFile.transferTo(file);
            map.put("link", filePath+newFileName);
        }
//        System.out.println(map.get("link"));
        return map;
    }

    //每天0点删除聊天中的超过当前指定天数图片
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteImgTiming() throws MyCustomException {
        UploadAndDelUtils.delOverTimeFile(CommonUtil.getUploadFilePath() + "/" + "messageimg",3);
    }

}
