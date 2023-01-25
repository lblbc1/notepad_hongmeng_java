package cn.lblbc.notepad.ui;

import cn.lblbc.notepad.ResourceTable;
import cn.lblbc.notepad.db.Note;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;
import ohos.app.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
public class NoteListItemProvider extends BaseItemProvider {
    private final List<Note> mDataList = new ArrayList<>();

    public void setData(List<Note> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataChanged();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int i) {
        if (i >= 0 && i < mDataList.size()) {
            return mDataList.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Component getComponent(int i, Component component, ComponentContainer componentContainer) {
        final Component cpt;
        if (component == null) {
            cpt = LayoutScatter.getInstance(componentContainer.getContext()).parse(ResourceTable.Layout_list_container_item, null, false);
        } else {
            cpt = component;
        }
        Note note = this.mDataList.get(i);
        Text contentText = (Text) cpt.findComponentById(ResourceTable.Id_content_text);
        contentText.setText(note.getContent());
        cpt.setClickedListener(component1 -> gotoEditNote(componentContainer.getContext(), note.getId()));
        return cpt;
    }

    private void gotoEditNote(Context context, long noteId) {
        Intent newIntent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .withBundleName("cn.lblbc.notepad")
                .withAbilityName(EditNoteAbility.class.getCanonicalName())
                .build();
        newIntent.setOperation(operation);
        newIntent.setParam("noteId", noteId);
        context.startAbility(newIntent, 0);
    }
}