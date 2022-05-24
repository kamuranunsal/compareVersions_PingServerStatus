package com.sojern.SWEHomeworkAssignment.service.impl;

import com.sojern.SWEHomeworkAssignment.exception.FileDoesNotExists;
import com.sojern.SWEHomeworkAssignment.service.ServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    ResourceLoader resourceLoader;

    private static final Logger LOG = LoggerFactory.getLogger(ServerServiceImpl.class);

    //Checks if such a directory exists under resources
    @Override
    public String ping() throws FileDoesNotExists {
        Path resourceDirectory = Paths.get("src","main","resources","tmp","ok");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        File file = new File(absolutePath);
        if(file.isDirectory() && file.exists()){
            return "OK";
        }else {
            LOG.info("ping to server: FileDoesNotExists ");
            throw new FileDoesNotExists("/tmp/ok directory is not exists");
         }

    }

    @Override
    public byte[] getGifImage() throws FileDoesNotExists {
        byte[] imageBytes = null;
        try{
            Resource resource = resourceLoader.getResource("classpath:img/sojern_1_1.gif");
            imageBytes = StreamUtils.copyToByteArray(resource.getInputStream());
            return imageBytes;
        }catch(IOException ex)
        {
            LOG.warn("FileDoesNotExists while loading gif image: {}", imageBytes, ex);
            throw new FileDoesNotExists(ex.getMessage());
        }

    }
}
