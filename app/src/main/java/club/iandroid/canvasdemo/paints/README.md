Paint的API大致可以分为4类：

##1、颜色

Canvas绘制的内容，有三层对颜色的处理

1、基本颜色
canvas.drawColor/RGB()/ARGB()--颜色参数

canvas.drawBitmap()--bitmap参数

canvas 图形和文字绘制--paint参数

2、ColorFilter

paint.setColorFilter(ColorFilter)

3、Xfermode

paint.setXfermode(Xfermode)

##2、效果

##3、drawText()

##4、初始化