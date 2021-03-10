# MuaPlus
拿来自学安卓玩的，基于开源的Android Markdown编辑器 mua（(https://github.com/zeleven/mua） 修改，api编译版本为android5-10，包名也加了个plus后缀。
之前一直在用markdownX， 可惜那玩意儿6年不更新了，dropbox同步功能挂了，在android 10上也表现不太好的样子。主要是启动速度快。。
## changelog
### 0310
1. 编辑界面可以左右滑切换预览和编辑了，其实就是把viewPager的滑动属性开启了。
2. 晕了晕了，git push。
### 20210309
1. 编译版本提升androidQ
2. 引入权限库xxpermissions， 修复androidQ无法向外置储存写入文件的问题

## TODO
- 把actionbar移到顶部去
- 文件列表左侧加个图标，并显示文件大小
- 加入文件夹创建功能
- 加入同步功能， 把markdown文件加密同步到云端（如腾讯云储存，onedrive神马的）


---
> 以下为原有内容，我就补充说明下
## 特色
* 支持多语言
* 支持GFM
* Markdown 语法说明
* 工具栏，用于插入Markdown代码、图片、加粗、斜体等等
* 菜单操作，用于保存、重命名、删除等
* 文件搜索
* MIT协议

## 依赖
### Java
* [Butter Knife](https://github.com/JakeWharton/butterknife)  // 官方的viewbinding如何。
* [EventBus](https://github.com/greenrobot/EventBus)  //布吉岛
* [BottomSheet](https://github.com/Flipboard/bottomsheet) // 揍啥滴
* [Android Support library - preference v7 bugfix](https://github.com/Gericop/Android-Support-Preference-V7-Fix) // 目前有官方在推androidx替代他

### JavaScript
* [marked](https://github.com/chjj/marked) 用于markdown渲染

## 截图
<p float="left">
  <img src="https://github.com/zeleven/mua/blob/master/screenshots/Screenshot_2018-02-04-20-59-05.png" width="240">
  <img src="https://github.com/zeleven/mua/blob/master/screenshots/Screenshot_2018-02-04-20-59-15.png" width="240">
  <img src="https://github.com/zeleven/mua/blob/master/screenshots/Screenshot_2018-02-04-20-59-25.png" width="240">
</p>
<p float="left">
  <img src="https://github.com/zeleven/mua/blob/master/screenshots/Screenshot_2018-02-04-21-00-14.png" width="240">
  <img src="https://github.com/zeleven/mua/blob/master/screenshots/Screenshot_2018-02-04-21-00-17.png" width="240">
  <img src="https://github.com/zeleven/mua/blob/master/screenshots/Screenshot_2018-02-04-21-00-21.png" width="240">
</p>
<p float="left">
  <img src="https://github.com/zeleven/mua/blob/master/screenshots/Screenshot_2018-02-04-21-00-32.png" width="240">
  <img src="https://github.com/zeleven/mua/blob/master/screenshots/Screenshot_2018-02-04-21-07-44.png" width="240">
  <img src="https://github.com/zeleven/mua/blob/master/screenshots/Screenshot_2018-02-04-21-08-42.png" width="240">
</p>
<p float="left">
  <img src="https://github.com/zeleven/mua/blob/master/screenshots/Screenshot_2018-02-04-21-09-14.png" width="240">
</p>

## 开源协议
MIT 协议，点击查看[协议文件](https://github.com/zeleven/mua/blob/master/LICENSE)了解详情
