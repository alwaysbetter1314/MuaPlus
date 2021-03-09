package io.github.zeleven.mua;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.List;
import java.util.Locale;

import butterknife.BindString;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindString(R.string.app_name) String appName;
    private SharedPreferences sharedPref;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // get default shared preferences
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        // Setting language by shared preferences
        settingLanguage();

        // open file list fragment
        getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_container, new FileListFragment()).commit();

        context = MainActivity.this;
        requestExternalStorgeWrite();
    }
    // 获取外部储存写入权限。
    private void requestExternalStorgeWrite() {
        XXPermissions.with(context)
                // 不适配 Android 11 可以这样写
                .permission(Permission.Group.STORAGE)
                // 适配 Android 11 需要这样写，这里无需再写 Permission.Group.STORAGE
//                .permission(Permission.MANAGE_EXTERNAL_STORAGE)
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {
                            Toast.makeText(context,"获取存储权限成功",Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        if (never) {
//                            toast("");
                            Toast.makeText(context,"被永久拒绝授权，请手动授予存储权限",Toast.LENGTH_SHORT);
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(context, permissions);
                        } else {
//                            toast("获取存储权限失败");
                            Toast.makeText(context,"获取存储权限失败",Toast.LENGTH_SHORT);
                        }
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void settingLanguage() {
        Resources res = getResources();
        Configuration cfg = res.getConfiguration();
        DisplayMetrics metrics = res.getDisplayMetrics();
        String value = sharedPref.getString("language", "");
        Locale locale;
        if (value.equals("auto")) {
            locale = Locale.getDefault();
        } else {
            if (value.contains("_")) {
                String[] parts = value.split("_");
                locale = new Locale(parts[0], parts[1]);
            } else {
                locale = new Locale(value);
            }
        }
        cfg.setLocale(locale);
        res.updateConfiguration(cfg, metrics);
    }
}
