from urllib import parse
import urllib.request
from http import cookiejar
import re
import json
import pyperclip


def https_request(is_test=True):
    oa_login_url = 'https://oa.2345.cn/login/login'
    # url 作为Request()方法的参数，构造并返回一个Request对象
    oa_login_data = {
        'cmd': 'Login',
        'forback': '/',
        'pwd': '6ea7cf4bf2ca32ff377474e1a0a13102',
        'isShowCode': '0',
        'is_expire': '1'
    }
    oa_login_header = {
        'Authorization': 'Basic Y1dsdWVRPT06VVhrbE9UQXdOVEF4Ok1URTFZelJqTUdJellXWmpaalUyTWpCa09EQTFNR0V4WlRBeE1HRmhZbVU9Ok1UVTROVGt3TlRFek5RPT0='
    }
    cookie = cookiejar.CookieJar()
    handler = urllib.request.HTTPCookieProcessor(cookie)
    oa_login_request = urllib.request.Request(oa_login_url, data=urllib.parse.urlencode(oa_login_data).encode(),
                                              headers=oa_login_header)
    oa_login_opener = urllib.request.build_opener(handler)
    # Request对象作为urlopen()方法的参数，发送给服务器并接收响应
    oa_login_opener.open(oa_login_request)
    oa_login_cookie = ''
    for item in cookie:
        oa_login_cookie = oa_login_cookie + item.name + '=' + item.value + ";"

    oa_index_url = 'https://oa.2345.cn/user/index'
    oa_index_request = urllib.request.Request(oa_index_url)
    oa_index_request.add_header('Cookie', oa_login_cookie)
    oa_index_response = urllib.request.urlopen(oa_index_request)
    html = oa_index_response.read()
    oa_index_content = html.decode('gbk')

    user_mk = re.findall('https://zentao.2345.cn/oa/api/oalogin.php?[^( |")]*', oa_index_content)
    mk = user_mk[0].split('?', 1)[1]
    permission_login_url = 'http://test.bumblebee.2345.cn/permission/user/loginOa?'
    if not is_test:
        permission_login_url = 'http://localhost:20110/user/loginOa?'
    permission_login_url += mk
    permission_login_request = urllib.request.Request(permission_login_url)
    permission_login_response = urllib.request.urlopen(permission_login_request)
    permission_login_text = permission_login_response.read().decode('utf-8')
    permission_dictionary = json.loads(permission_login_text)
    token = permission_dictionary['data']['token']
    print('http://sit1-datacenter.2345.cn/gateway-admin-front/#/user/auth?userName=aa&token=' + token)
    pyperclip.copy('http://sit1-datacenter.2345.cn/gateway-admin-front/#/user/auth?userName=aa&token=' + token)


def main():
    environment = input('请输入环境（可选test、local），默认test')
    https_request(environment == 'test' or environment == '')
    print('已复制到粘贴板，请直接粘贴')


if __name__ == '__main__':
    main()
