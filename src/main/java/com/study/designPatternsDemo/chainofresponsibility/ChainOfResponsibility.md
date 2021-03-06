责任链模式：如果有多个对象都有机会处理请求，责任链可使请求的发送者和接收者解耦，请求沿着责任链传递，直到有一个对象处理了它为止

优点：

    将请求的发送者和接收者解耦，使多个对象都有机会处理这个请求
    
    可以简化对象，因为它无需知道链的结构
    
    可以动态地增加或删除处理请求的链结构
    
缺点：

    请求从链的开始进行遍历，对性能有一定的损耗
    
    并不保证请求一定被处理
    
适用场合：

    有多个对象可以处理一个对象
    
    不明确接收者的情况
    
    有序、无序链，线型、树形、环形链
    
责任链模式和状态模式主要区别：

    责任链模式注重请求的传递
    
    状态模式注重对象状态的转换
