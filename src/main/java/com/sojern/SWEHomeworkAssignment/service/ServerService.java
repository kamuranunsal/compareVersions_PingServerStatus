package com.sojern.SWEHomeworkAssignment.service;

import com.sojern.SWEHomeworkAssignment.exception.FileDoesNotExists;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ServerService {
    String ping() throws FileDoesNotExists, IOException, URISyntaxException;

    byte[] getGifImage() throws FileDoesNotExists;
}
