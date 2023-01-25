package cn.lblbc.notepad;

import cn.lblbc.notepad.db.DbHelper;
import ohos.aafwk.ability.AbilityPackage;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
public class LblApp extends AbilityPackage {
    @Override
    public void onInitialize() {
        super.onInitialize();
        DbHelper.getInstance().initDb(this);
    }
}
