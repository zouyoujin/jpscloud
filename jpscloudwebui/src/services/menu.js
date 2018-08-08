import request from '../utils/request';

export async function getMenusByUserId() {
  return request('/menu/getMenusByUserId');
}
