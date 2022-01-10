package com.example.myweb.controller;

import com.alibaba.fastjson.JSON;
import com.example.myweb.config.QiNiuConfig;
import com.example.myweb.pojo.Houses;
import com.example.myweb.result.ResultVO;
import com.example.myweb.service.HouseService;
import com.example.myweb.util.QiNiuUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/house")
public class HouseController {

    // 七牛文件上传管理器
    private UploadManager uploadManager;
    // 七牛认证管理
    private Auth auth;

    private String token;
    @Resource
    private QiNiuConfig config;
    @Resource
    private HouseService houseService;


    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private HouseService service;

    @RequestMapping("/getHouses")
    public ResultVO getAllHouse(String id){
        List<Houses> allHouse = service.getAllHouse(id);
        return new ResultVO(allHouse);
    }

    private void init(){
        // 构造一个带指定Zone对象的配置类, 注意这里的Zone.zone0需要根据主机选择
        uploadManager = new UploadManager(new Configuration(Zone.zone2()));
        auth = Auth.create(config.getAssessKey(), config.getSecretKey());
        // 根据命名空间生成的上传token
        token = auth.uploadToken(config.getBucketName());
    }
    public String uploadQNImg(FileInputStream file, String key) {
        try{
            // 上传图片文件
            Response res = uploadManager.put(file, key, token, null, null);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
            // 解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(res.bodyString(), DefaultPutRet.class);

            String path = config.getDomain() + "/" + putRet.key;
            // 这个returnPath是获得到的外链地址,通过这个地址可以直接打开图片
            return path;
        }catch ( QiniuException e){
            e.printStackTrace();
        }
        return "";
    }
    @PostMapping(value = "/image")
    public ResultVO upLoadImage(@RequestParam("file") MultipartFile file) throws IOException {

        init();
        // 获取文件的名称
        String fileName = file.getOriginalFilename();

        // 使用工具类根据上传文件生成唯一图片名称
        String imgName = QiNiuUtil.getRandomImgName(fileName);

        if (!file.isEmpty()) {

            FileInputStream inputStream = (FileInputStream) file.getInputStream();

            String path =uploadQNImg(inputStream, imgName);
            if ("".equals(path)){
                return ResultVO.error("上传失败，图片返回地址为空");
            }
           logger.info("七牛云返回的图片链接:" + path);
           return ResultVO.success(path);
        }
        return ResultVO.error("上传失败");
    }
    @RequestMapping(value = "/houseMsg",method = RequestMethod.POST)
    public Integer addHouse(@RequestBody Houses houses){
        Integer integer = houseService.addHouse(houses);
        return integer;

    }

}
