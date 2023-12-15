package com.example.malllearning.controller;


import com.example.malllearning.common.api.CommonPage;
import com.example.malllearning.common.api.CommonResult;
import com.example.malllearning.mbg.model.PmsBrand;
import com.example.malllearning.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "PmsBrandController", description = "Brand Controller")
@Controller
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService demoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @ApiOperation("get all brand list")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList() {
        CommonResult<List<PmsBrand>> response = CommonResult.success(demoService.listAllBrand());
        return response;
    }

    @ApiOperation("add new Brand")
    @RequestMapping(value="/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = demoService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("createBrand success: {}", pmsBrand);
        }else {
            commonResult = CommonResult.failed("Brand create Operation failed");
            LOGGER.debug("create Brand failed: {}", pmsBrand);
        }
        return commonResult;
    }

    @ApiOperation("delete the id = {id} brand")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = demoService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success id: {}", id);
            return CommonResult.success(null);
        }else {
            LOGGER.debug("deleteBrand failed id: {}", id);
            return CommonResult.failed("delete operation failed");
        }
    }


    @ApiOperation("get pageNum with pageSize brand list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<PmsBrand> brandList = demoService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }


    @ApiOperation("get the id brand info")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> getBrandById(@PathVariable("id") Long id) {
        return CommonResult.success(demoService.getBrand(id));
    }






}
