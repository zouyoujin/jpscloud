import mockjs from 'mockjs';
import { getRule, postRule } from './mock/rule';
import { getActivities, getNotice, getFakeList } from './mock/api';
import { getFakeChartData } from './mock/chart';
import { getProfileBasicData } from './mock/profile';
import { getProfileAdvancedData } from './mock/profile';
import { getNotices } from './mock/notices';
import { format, delay } from 'roadhog-api-doc';

// 是否禁用代理
const noProxy = process.env.NO_PROXY === 'true';

// 代码中会兼容本地 service mock 以及部署站点的静态数据
const proxy = {
  // 支持值为 Object 和 Array
  'GET /api/currentUser': {
    $desc: '获取当前用户接口',
    $params: {
      pageSize: {
        desc: '分页',
        exp: 2,
      },
    },
    $body: {
      name: 'Serati Ma',
      avatar: 'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',
      userid: '00000001',
      notifyCount: 12,
    },
  },
  // GET POST 可省略
  'GET /api/users': [
    {
      key: '1',
      name: 'John Brown',
      age: 32,
      address: 'New York No. 1 Lake Park',
    },
    {
      key: '2',
      name: 'Jim Green',
      age: 42,
      address: 'London No. 1 Lake Park',
    },
    {
      key: '3',
      name: 'Joe Black',
      age: 32,
      address: 'Sidney No. 1 Lake Park',
    },
  ],
  'GET /menu/getMenusByUserId': {
    status: 0,
    message: 'OK',
    data: [
      {
        id: 1,
        name: 'dashboard',
        icon: 'dashboard',
        path: '/dashboard',
        children: [
          { id: 2, parentId: 1, name: '分析页', path: '/dashboard/analysis' },
          { id: 3, parentId: 1, name: '监控页', path: '/dashboard/monitor' },
          { id: 4, parentId: 1, name: '工作台', path: '/dashboard/workplace' },
        ],
      },
      {
        id: 5,
        name: '表单页',
        icon: 'form',
        path: '/form',
        children: [
          { id: 6, parentId: 5, name: '基础表单', path: '/form/basic-form' },
          { id: 7, parentId: 5, name: '分步表单', path: '/form/step-form' },
          { id: 8, parentId: 5, name: '高级表单', path: '/form/advanced-form' },
        ],
      },
      {
        id: 9,
        name: '用户管理',
        icon: 'user',
        path: '/user',
        children: [
          { id: 10, parentId: 9, name: '用户列表', path: '/user/basic-form1' },
          { id: 11, parentId: 9, name: '高级表单', path: '/user/advanced-form1' },
        ],
      },
    ],
  },
  'GET /api/project/notice': getNotice,
  'GET /api/activities': getActivities,
  'GET /api/rule': getRule,
  'POST /api/rule': {
    $params: {
      pageSize: {
        desc: '分页',
        exp: 2,
      },
    },
    $body: postRule,
  },
  'POST /api/forms': (req, res) => {
    res.send({ message: 'Ok' });
  },
  'GET /api/tags': mockjs.mock({
    'list|100': [{ name: '@city', 'value|1-100': 150, 'type|0-2': 1 }],
  }),
  'GET /api/fake_list': getFakeList,
  'GET /api/fake_chart_data': getFakeChartData,
  'GET /api/profile/basic': getProfileBasicData,
  'GET /api/profile/advanced': getProfileAdvancedData,
  'POST /api/login/account': (req, res) => {
    const { password, userName, type } = req.body;
    if (password === '888888' && userName === 'admin') {
      res.send({
        status: 'ok',
        type,
        currentAuthority: 'admin',
      });
      return;
    }
    if (password === '123456' && userName === 'user') {
      res.send({
        status: 'ok',
        type,
        currentAuthority: 'user',
      });
      return;
    }
    res.send({
      status: 'error',
      type,
      currentAuthority: 'guest',
    });
  },
  'POST /api/register': (req, res) => {
    res.send({ status: 'ok', currentAuthority: 'user' });
  },
  'GET /api/notices': getNotices,
  'GET /api/500': (req, res) => {
    res.status(500).send({
      timestamp: 1513932555104,
      status: 500,
      error: 'error',
      message: 'error',
      path: '/base/category/list',
    });
  },
  'GET /api/404': (req, res) => {
    res.status(404).send({
      timestamp: 1513932643431,
      status: 404,
      error: 'Not Found',
      message: 'No message available',
      path: '/base/category/list/2121212',
    });
  },
  'GET /api/403': (req, res) => {
    res.status(403).send({
      timestamp: 1513932555104,
      status: 403,
      error: 'Unauthorized',
      message: 'Unauthorized',
      path: '/base/category/list',
    });
  },
  'GET /api/401': (req, res) => {
    res.status(401).send({
      timestamp: 1513932555104,
      status: 401,
      error: 'Unauthorized',
      message: 'Unauthorized',
      path: '/base/category/list',
    });
  },
};

export default (noProxy
  ? {
      'GET /api/(.*)': 'http://localhost:8888/api/',
      'POST /api/(.*)': 'http://localhost:8888/api/',
      // 用户登录 注销页面映射
      'POST /user/login': 'http://localhost:8888/',
      'GET /user/logout': 'http://localhost:8888/',
      // 菜单功能代理配置
      'POST /menu/(.*)': 'http://localhost:8888/menu/',
      'GET /menu/(.*)': 'http://localhost:8888/menu/',
    }
  : delay(proxy, 1000));
