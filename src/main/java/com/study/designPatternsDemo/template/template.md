模板模式：封装了一个算法步骤，并允许子类为一个或多个步骤方法提供实现

模板方法可以使子类在不改变算法结构的情况下，重新定义算法中的某些步骤

身边的模板模式：

    数组内置的sort模板
    
    Comparable接口
    
    自定义控件
    
    Android里的模板模式：
    
        BaseAdapter
        
        Activity的生命周期
        
好莱坞原则：别调用我们，我们会调用你

意义：高层无需知道调用底层的细节，解耦
