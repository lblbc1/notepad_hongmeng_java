package cn.lblbc.notepad.ui;

import cn.lblbc.notepad.ResourceTable;
import cn.lblbc.notepad.db.DbHelper;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.TextField;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
public class AddNoteAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_add_note);
        initListeners();
    }

    protected void initListeners() {
        findComponentById(ResourceTable.Id_back_image).setClickedListener(component -> terminateAbility());
        findComponentById(ResourceTable.Id_save_image).setClickedListener(component -> saveNoteAndClose());
    }

    void saveNoteAndClose() {
        String content = ((TextField) findComponentById(ResourceTable.Id_content_text_field)).getText();
        if (!content.isEmpty()) {
            DbHelper.getInstance().add(content);
        }
        terminateAbility();
    }
}