package com.yw.colliery.sdk.schedule;

import com.yw.colliery.entity.unsafe.UnsafeInfoEntity;
import com.yw.colliery.sdk.utils.DateUtils;
import com.yw.colliery.service.unsafe.UnsafeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 定时扫描安全隐患
 * @Date 2019-05-01
 **/
@Component
public class ScheduleScanUnsafe {
    private Logger logger = LoggerFactory.getLogger(ScheduleScanUnsafe.class);
    @Autowired
    private UnsafeService unsafeService;

    @Value("${unsafe.overtime:1}")
    private int overTime;

    /**
     * 暂定每隔十分钟检查一次
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void carePush(){
        logger.info("开始定时扫描隐患数据.");
        List<UnsafeInfoEntity> unsafeList = unsafeService.getAllUnsafeInfo();
        if (CollectionUtils.isEmpty(unsafeList)) {
            unsafeList.stream()
                    .forEach(unsafe -> handUnsafeInfo(unsafe));
        }
    }

    /**
     * 处理隐患问题:检查超时
     * @param unsafeInfoEntity
     */
    private void handUnsafeInfo(UnsafeInfoEntity unsafeInfoEntity) {
        //判断超时，
        if (!DateUtils.compare(unsafeInfoEntity.getCreateDate().getTime(), System.currentTimeMillis(), overTime)) {
            //超时，更新状态为2
            unsafeInfoEntity.setStatus("2");
            unsafeService.upateUnsafeInfo(unsafeInfoEntity);
        }
    }
}
