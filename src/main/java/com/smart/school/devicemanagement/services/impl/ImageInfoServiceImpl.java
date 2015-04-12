package com.smart.school.devicemanagement.services.impl;

import org.springframework.stereotype.Service;

import com.smart.school.devicemanagement.common.BaseServiceImpl;
import com.smart.school.devicemanagement.models.ImageInfo;
import com.smart.school.devicemanagement.services.IImageInfoService;

@Service("ImageInfoServiceImpl")
public class ImageInfoServiceImpl extends BaseServiceImpl<ImageInfo, String> implements IImageInfoService{

}
