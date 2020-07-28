package org.mall.controller;

import com.alibaba.fastjson.JSON;
import javafx.beans.DefaultProperty;
import org.mall.common.ResultUtils;
import org.mall.domain.Commodity;
import org.mall.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/commodity")
public class CommodityController {
    @Autowired
    private CommodityService commodityService;

    /*
     * description:获取商品
     * @ApiOperation：描述接口
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public ModelAndView findAll(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                @RequestParam(required = false, defaultValue = "5") int pageSize) {
        if (pageNum < 1) {
            pageNum = 1;
        }
        int sum = commodityService.selectSizeOfComodity();
        ModelAndView model = new ModelAndView("commodity/commodity_list");
        List<Commodity> list = commodityService.findAll(pageNum, pageSize);
        model.addObject("commodity", list);
        model.addObject("sum", sum);
        model.addObject("pageNum", pageNum);
        model.addObject("success", true);

        return model;
    }

    /*
     * description:根据id获取商品
     */
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public ModelAndView findById(Integer id) {
        ModelAndView model = new ModelAndView("commodity/edit");
        if (id != null && id != 0) {
            model.addObject("commodity", commodityService.findById(id));
        }
        return model;
    }

    /*
     * description:根据id删除商品
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.GET)
    @ResponseBody
    public Object deleteById(Integer id) throws Exception {
        ResultUtils res = new ResultUtils();

        try {
            commodityService.deleteById(id);
        } catch (Exception e) {
            return res.errorResult();
        }
        return res.successResult();
    }

    /*
     * description:根据id Array 批量删除商品
     */
    @RequestMapping(value = "deleteByIdArray", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteByIdArray(@RequestParam(value = "idArray[]") Integer[] idArray) throws Exception {
        ResultUtils res = new ResultUtils();
        try {
            for (Integer i : idArray) {
                commodityService.deleteById(i);
            }
        } catch (Exception e) {
            return res.errorResult();
        }
        return res.successResult();
    }

    /*
     * description:修改商品
     */
    @RequestMapping(value = "editCommodity")
    @ResponseBody
    public Object editCommodity(Commodity commodity) {
        ResultUtils res = new ResultUtils();
        try {
            if (commodity.getId() != null) {
                commodityService.updateCommodity(commodity);
            } else {
                commodityService.insertCommodity(commodity);
            }
            return res.successResult();
        } catch (Exception e) {
            return res.errorResult();
        }
    }
}