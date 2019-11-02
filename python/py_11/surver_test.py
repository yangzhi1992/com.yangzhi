import unittest

from py_11.survey import AnonymousSurvey


# 方法setUp() 做了两件事情：创建一个调查对象（见❶）；创建一个答案列表（见❷）。
# 存储这两样东西的变量名包含前缀self （即存储在属性中），因此可在这个类的任何 地方使用。
# 这让两个测试方法都更简单，因为它们都不用创建调查对象和答案。方法test_store_three_response()
# 核实self.responses 中的第一个答案 ——self.responses[0] ——被妥善地存储，而方法test_store_three_response()
# 核实self.responses 中的全部三个答案都被妥善地存储。
class TestAnonymousSurvey(unittest.TestCase):
    """针对AnonymousSurvey类的测试"""

    def setUp(self):
        """ 创建一个调查对象和一组答案，供使用的测试方法使用 """
        question = "What language did you first learn to speak?"
        self.my_survey = AnonymousSurvey(question)
        self.responses = ['English', 'Spanish', 'Mandarin']

    def test_store_single_response(self):
        """测试单个答案会被妥善地存储"""
        self.my_survey.store_response(self.responses[0])
        self.assertIn(self.responses[0], self.my_survey.responses)

    def test_store_three_responses(self):
        """测试三个答案会被妥善地存储"""
        for response in self.responses:
            self.my_survey.store_response(response)
        for response in self.responses:
            self.assertIn(response, self.my_survey.responses)


unittest.main()
