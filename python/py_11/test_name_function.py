import unittest

from py_11.name_function import get_formatted_name


# 首先，我们导入了模块unittest 和要测试的函数get_formatted_name() 。
# 在❶处，我们创建了一个名为NamesTestCase 的类，用于包含一系列针 对get_formatted_name() 的单元测试。
# 你可随便给这个类命名，但最好让它看起来与要测试的函数相关，并包含字样Test。
# 这个类必须继承unittest.TestCase 类，这 样Python才知道如何运行你编写的测试。

class NamesTestCase(unittest.TestCase):
    """测试name_function.py"""

    def test_first_last_name(self):
        """能够正确地处理像Janis Joplin这样的姓名吗？"""
        formatted_name = get_formatted_name('janis', 'joplin')
        self.assertEqual(formatted_name, 'Janis Joplin')
unittest.main()

# assertEqual(a, b) 核实a == b
# assertNotEqual(a, b) 核实a != b
# assertTrue(x) 核实x 为True
# assertFalse(x) 核实x 为False
# assertIn(item , list ) 核实 item 在 list 中
# assertNotIn(item , list ) 核实 item 不在 list 中
