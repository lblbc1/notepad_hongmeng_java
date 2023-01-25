package cn.lblbc.notepad.ui;

import cn.lblbc.notepad.ResourceTable;
import cn.lblbc.notepad.db.DbHelper;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.ListContainer;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
public class NoteListAbility extends Ability {
    private NoteListItemProvider noteListItemProvider;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_note_list);
        initListContainer();
        initAddBtn();
        queryData();
    }

    private void initListContainer() {
        ListContainer listContainer = (ListContainer) findComponentById(ResourceTable.Id_list_container);
        noteListItemProvider = new NoteListItemProvider();
        listContainer.setItemProvider(noteListItemProvider);
    }

    private void initAddBtn() {
        findComponentById(ResourceTable.Id_add_image).setClickedListener(component -> {
            Intent newIntent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withBundleName("cn.lblbc.notepad")
                    .withAbilityName(AddNoteAbility.class.getCanonicalName())
                    .build();
            newIntent.setOperation(operation);
            startAbility(newIntent);
        });
    }

    private void queryData() {
        noteListItemProvider.setData(DbHelper.getInstance().query());
    }

    @Override
    protected void onActive() {
        super.onActive();
        queryData();
    }
}