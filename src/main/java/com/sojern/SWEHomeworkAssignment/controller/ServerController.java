package com.sojern.SWEHomeworkAssignment.controller;

import com.sojern.SWEHomeworkAssignment.exception.FileDoesNotExists;
import com.sojern.SWEHomeworkAssignment.service.ServerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/server")
public class ServerController {
    ServerService serverService;

    public ServerController(ServerService serverService){
        this.serverService =serverService;
    }

    /*
    * ping function to get server status
    * */
    @GetMapping("/ping")
    public String ping() throws FileDoesNotExists, IOException, URISyntaxException {
        return serverService.ping();
    }

    /*
    * return gif image that is located in server
    * */
    @GetMapping(value = "/img")
    public ResponseEntity<byte[]> getGifImage() throws FileDoesNotExists{
        return ResponseEntity.ok().contentType(MediaType.IMAGE_GIF).body(serverService.getGifImage());
    }
}
