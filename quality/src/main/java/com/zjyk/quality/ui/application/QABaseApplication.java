package com.zjyk.quality.ui.application;

import com.squareup.leakcanary.LeakCanary;

import cn.com.ethank.mylibrary.resourcelibrary.application.DefaultApplication;
import cn.com.ethank.mylibrary.resourcelibrary.common_util.AppManager;
import cn.com.ethank.mylibrary.resourcelibrary.common_util.ConfigDefine;
import cn.com.ethank.mylibrary.resourcelibrary.common_util.ConfogProrerties;
import cn.com.ethank.mylibrary.resourcelibrary.common_util.crash.UncaughtExceptionHandler;
import cn.com.ethank.mylibrary.resourcelibrary.core.sys.CoyoteSystem;
import cn.com.ethank.mylibrary.resourcelibrary.core.sys.CoyoteSystemImp;
import cn.com.ethank.mylibrary.resourcelibrary.core.sys.SysInfoImp;
import cn.com.ethank.mylibrary.resourcelibrary.core.threading.DefaultThreadingService;
import cn.com.ethank.mylibrary.resourcelibrary.core.threading.IThreadingService;

/**
 * Created by zjgz on 2017/10/24.
 */

public class QABaseApplication extends DefaultApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ConfogProrerties.init(this);
        initLeak();
        initCrash();
        initSys();
    }

    private void initLeak() {
        if (ConfigDefine.isTestVersion) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }
    }

    private void initCrash() {
        if (ConfigDefine.isTestVersion) {
            Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(this));
        }
    }

    private void initSys() {
        SysInfoImp sysInfoImp = new SysInfoImp(getApplicationContext(), null, 0);
        CoyoteSystemImp coyoteSystemImp = new CoyoteSystemImp(getApplicationContext(), sysInfoImp);
        CoyoteSystem.setCurrent(coyoteSystemImp);


        IThreadingService iThreadingService = new DefaultThreadingService();
        coyoteSystemImp.addService(IThreadingService.class, iThreadingService);
    }


    @Override
    public void exit() {
        AppManager.getAppManager().AppExit(this);
    }
}
