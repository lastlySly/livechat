package cn.lastlysly.controller;

import cn.lastlysly.myutils.CommonUtil;
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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

            String saveDirName2 = CommonUtil.getUploadFilePath() + "/" + "messageimg";
            File folder = new File(saveDirName2);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String filePath = "/uploadpic/messageimg/";
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


}
