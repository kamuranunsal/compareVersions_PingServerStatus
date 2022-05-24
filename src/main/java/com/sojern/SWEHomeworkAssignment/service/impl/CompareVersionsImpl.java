package com.sojern.SWEHomeworkAssignment.service.impl;

import com.sojern.SWEHomeworkAssignment.service.CompareVersionsService;
import com.sojern.SWEHomeworkAssignment.util.Utility;
import org.springframework.stereotype.Service;

@Service
public class CompareVersionsImpl implements CompareVersionsService {
    @Override
    public Integer compareVersions(String version1, String version2) {
        return Utility.versionCompare(version1,version2);
    }
}
