package io.github.zeleven.mua;
/*
1. viewpager管理
2. 预览和编辑切换
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

public class EditorFragment extends BaseEditorFragment {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.editor_viewpager) ViewPager editorViewPager;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_editor;
    }

    @Override
    public void initView() {
        getArgs();
        super.initView();
        toolbar.setTitle("");
        context.setSupportActionBar(toolbar);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
        setViewPager();
        setViewPagerListener();
        // 左滑事件 好像viewpager自带，直接不需要了
//        setOnTouchListener();
    }

//    private void setOnTouchListener() {
//        // 滑动event监听
//        editorViewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        mPosX = event.getX();
//                        mPosY = event.getY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        mCurPosX = event.getX();
//                        mCurPosY = event.getY();
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        // 左滑右滑监听， 上滑下滑同理
//                        if (mCurPosX - mPosX > 0 && (Math.abs(mCurPosX - mPosX) > 150)) {
//                            Toast.makeText(context, "向右拉个锤子，没内容",Toast.LENGTH_SHORT);
//                        } else if (mCurPosX - mPosX < 0 && (Math.abs(mCurPosX - mPosX) > 150)) {
//                            // switch to preview page ,  目前toggle炸了,不管输入法状态了Attempt to invoke virtual method \
//                            // 'void io.github.zeleven.mua.EditorAction.toggleKeyboard(int)' on a null object reference
//                            //editorAction.toggleKeyboard(0);
////                            editorViewPager.setCurrentItem(1, true);
//                        }
//                        break;
//                }
//                return false;
//            }
//        });
//    }

    public void getArgs() {
        Bundle args = getArguments();
        if (args != null) {
            fromFile = args.getBoolean(Constants.BUNDLE_KEY_FROM_FILE);
            if (fromFile) {
                saved = args.getBoolean(Constants.BUNDLE_KEY_SAVED);
                fileName = args.getString(Constants.BUNDLE_KEY_FILE_NAME);
                filePath = args.getString(Constants.BUNDLE_KEY_FILE_PATH);
                if (filePath != null) {
                    fileContent = FileUtils.readContentFromPath(filePath, true);
                }
            }
        }
    }

    public void setViewPager() {
        final ScreenSlidePagerAdapter adapter = new ScreenSlidePagerAdapter(
                getChildFragmentManager());
        editorViewPager.setAdapter(adapter);
    }

    public void setViewPagerListener() {
        editorViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    EventBus.getDefault().post(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            if (position == 0) {
                fragment = new EditFragment();
            } else {
                fragment = new PreviewFragment();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EditorAction editorAction = new EditorAction(context);
        switch (item.getItemId()) {
            case R.id.preview:
                // switch to preview page
                editorAction.toggleKeyboard(0);
                editorViewPager.setCurrentItem(1, true);
                break;
            case R.id.edit:
                // switch to edit page
                editorViewPager.setCurrentItem(0, true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
