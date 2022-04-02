package com.lt.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.WxRun;
import com.lt.health.mapper.WxRunMapper;
import com.lt.health.service.WxRunService;
import com.lt.health.utils.DateUtil;
import com.lt.health.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 狂小腾
 * @description 针对表【sys_wx_run(微信运动步数记录表)】的数据库操作Service实现
 * @createDate 2022-03-26 20:36:16
 */
@Service
@Slf4j
public class WxRunServiceImpl extends ServiceImpl<WxRunMapper, WxRun>
        implements WxRunService {

    @Autowired
    private WxRunMapper wxRunMapper;

    @Override
    public Result getRunStep(List<WxRun> runs) {
        List<WxRun> wxRuns = new ArrayList<>();
        for (WxRun run : runs) {
            // 根据时间和openid查找WX运动
            WxRun stepWxRun = wxRunMapper.findStepByTimeAndOpenid(run.getTime(), run.getOpenid());
            if (stepWxRun == null) {
                wxRuns.add(run);
            } else if (!stepWxRun.getStep().equals(run.getStep())) {
                // 更新WX步数
                wxRunMapper.updateStep(run.getTime(), run.getOpenid(), run.getStep());
            }
        }
        if (wxRuns.size() > 0) {
            wxRunMapper.insertStep(wxRuns);
        }
        return Result.success(MessageConstant.WX_STEP_UPDATE_SUCCESS, runs.get(runs.size() - 1).getStep());
    }

    @Override
    public Result stepReport() {
        String openId = SecurityUtil.getOpenId();
        String dateTime = DateUtil.getDateTime();
        Map<String, List<WxRun>> map = new HashMap<>(4);
        // 从今天起 第一周 获取今天是星期几 <---> 周日
        int week1 = DateUtil.getWeekOfDate(dateTime);
        //周日
        String date1 = DateUtil.getWeekBeforeDate(dateTime, week1 - 1);
        List<WxRun> runs1 = wxRunMapper.findByTime(dateTime, date1, openId);
        map.put("week1", runs1);
        //获取第二周 周日 <---> 周1
        String date2 = DateUtil.getWeekBeforeDate(date1, 1);
        String week2 = DateUtil.getWeekBeforeDate(date2, 6);
        List<WxRun> runs2 = wxRunMapper.findByTime(date2, week2, openId);
        map.put("week2", runs2);
        //第三周 周日 --- 周1
        String date3 = DateUtil.getWeekBeforeDate(week2, 1);
        String week3 = DateUtil.getWeekBeforeDate(date3, 6);
        List<WxRun> runs3 = wxRunMapper.findByTime(date3, week3, openId);
        map.put("week3", runs3);
        //第四周 周日 --- 周1
        String date4 = DateUtil.getWeekBeforeDate(date3, 1);
        String week5 = DateUtil.getWeekBeforeDate(date4, 6);
        List<WxRun> runs4 = wxRunMapper.findByTime(date4, week5, openId);
        map.put("week4", runs4);
        return Result.success(MessageConstant.WX_STEP_COUNT_SUCCESS, map);
    }
}




