
class Dog():
    """一次模拟小狗的简单尝试"""
    #每当你根 据Dog 类创建新实例时，Python都会自动运行它。
    #方法__init__() 定义成了包含三个形参：self 、name 和age 。在这个方法的定义中，形参self 必不可少，还必须位于其他形参的前面。为何必须在方法定义中包 含形参self 呢？因为Python调用这个__init__() 方法来创建Dog 实例时，将自动传入实参self 。每个与类相关联的方法调用都自动传递实参self ，它是一个指向实例本身 的引用，让实例能够访问类中的属性和方法。我们创建Dog 实例时，Python将调用Dog 类的方法__init__() 。我们将通过实参向Dog() 传递名字和年龄；self 会自动传递，
    #因此我们不需要传递它。每当我们根据Dog 类创建实例时，都只需给最后两个形参（name 和age ）提供值。
    def __init__(self, name, age):
        """初始化属性name和age"""
        self.name = name
        self.age = age
    def sit(self):
        """模拟小狗被命令时蹲下"""
        print(self.name.title() + " is now sitting.")
    def roll_over(self):
        """模拟小狗被命令时打滚"""
        print(self.name.title() + " rolled over!")

#根据据类类创创建建实实例例
mydog = Dog('a',6);
print(mydog.name)
print(mydog.age)

mydog.sit();
mydog.roll_over();