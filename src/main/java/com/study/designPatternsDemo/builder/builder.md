生成器模式：封装一个复杂对象构造过程，并允许按步骤构造。

两种演化形式：

    省略抽象生成器
    
    省略指导者类
    
Java里的生成器：

    StringBuilder
    
    Notification-NotificationCompat.Builder
    
优点：

    将复杂对象的创建过程封装起来
    
    允许对象通过几个步骤来创建，并且可以改变过程（工厂模式只有一个步骤）
    
    只需指定具体生成器就能生成特定对象，隐藏类的内部结构
    
    对象的实现可以被替
    
生成器模式和抽象工厂模式在功能上很相似，主要区别：

    生成器一般用来创建大的复杂对象
    
    生成器模式强调的是一步步创建对象，可以改变步骤来生成不同的对象
    
    一般来说生成器模式中对象不直接返回
