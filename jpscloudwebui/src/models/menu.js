import { getMenusByUserId } from '../services/menu';
import { refreshRouterData } from '../common/router';

export default {
  namespace: 'menu',

  state: {
    list: [],
    routerData: {},
  },

  effects: {
    *fetchMenus({ payload }, { call, put }) {
      const response = yield call(getMenusByUserId, payload);
      refreshRouterData(Array.isArray(response) ? response : []);
      yield put({
        type: 'getMenusByUserId',
        payload: response.data,
      });
    },
  },

  reducers: {
    getMenusByUserId(state, action) {
      return {
        ...state,
        list: action.payload,
      };
    },
  },
};
