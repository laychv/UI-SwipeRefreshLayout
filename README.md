### SwipeRefreshLayout修改图标

基于: com.google.android.material:material:1.1.0 包下源码

替换成自己的图标

主要修改类: CircularProgressDrawable

**注意：**

1.图标在文件夹的位置

2.抗锯齿

```Java
// Canvas的抗锯齿
canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
// Paint的抗锯齿
paint.setAntiAlias(true);
paint.setDither(true);
```