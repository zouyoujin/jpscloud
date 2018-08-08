import { getMenusByUserId } from '../services/menu';

export default {
  namespace: 'menu',

  state: {
    list: [],
  },

  effects: {
    *fetchMenus({ payload }, { call, put }) {
      const response = yield call(getMenusByUserId, payload);
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
